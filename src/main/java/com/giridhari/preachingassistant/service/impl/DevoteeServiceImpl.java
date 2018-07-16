package com.giridhari.preachingassistant.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.management.openmbean.TabularType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.giridhari.preachingassistant.db.model.Devotee;
import com.giridhari.preachingassistant.db.model.Program;
import com.giridhari.preachingassistant.db.model.Type;
import com.giridhari.preachingassistant.db.model.UserAccount;
import com.giridhari.preachingassistant.db.model.Yatra;
import com.giridhari.preachingassistant.db.repo.DevoteeRepo;
import com.giridhari.preachingassistant.db.repo.ProgramRepo;
import com.giridhari.preachingassistant.db.repo.YatraRepo;
import com.giridhari.preachingassistant.service.DevoteeService;
import com.giridhari.preachingassistant.util.CollectionUtils;

@Service
public class DevoteeServiceImpl implements DevoteeService {
	
	@Resource
	private DevoteeRepo devoteeRepo;

	@Resource
	private YatraRepo yatraRepo;

	@Resource
	private ProgramRepo programRepo;

	@Override
	public List<Devotee> list() {
		return (List<Devotee>) CollectionUtils.makeCollection(devoteeRepo.findAll());
	}
	
	@Override
	public Page<Devotee> list(Pageable pageable) {
		return devoteeRepo.findAll(pageable);
	}

	@Override
	public Devotee get(long devoteeId) {
		return devoteeRepo.findOne(devoteeId);
	}

	@Override
	public void create(Devotee devotee) {
		devoteeRepo.save(devotee);
	}

	@Override
	public void update(Devotee devotee) {
		devoteeRepo.save(devotee);
	}

	@Override
	public void delete(long devoteeId) {
		devoteeRepo.delete(devoteeId);
	}

	@Override
	public Page<Devotee> searchDevotees(String query, Pageable pageable) {
		return devoteeRepo.findByQuery(query, pageable);
	}
	
	@Override
	public String getDisplayName(Devotee devotee) {
		if (devotee.getInitiatedName() != null && devotee.getInitiatedName() != "") {
			return devotee.getInitiatedName();
		} else {
			return devotee.getLegalName();
		}
	}

	@Override
	public Devotee findByEmail(String email) {
		return devoteeRepo.findByEmail(email);
	}
	
	@Override
	public Devotee findBySmsPhone(String smsPhone) {
		return devoteeRepo.findBySmsPhone(smsPhone);
	}

	@Override
	public Page<Devotee> globalDevoteeSearchTypeAhead(String typeText, Pageable pageable) {
		return devoteeRepo.findAllByLegalNameContainingOrInitiatedNameContainingOrSmsPhoneContainingOrEmailContaining(typeText, typeText, typeText, typeText, pageable);
	}

	public List<Long> getDevoteeAssociatedYatras(long devoteeId, Type role) {
		List<Yatra> yatras = new ArrayList<Yatra>();
		if (role.equals(Type.DEVOTEE)) {
			// we don't do anything for a devotee, no associated yatras returned for
			// him. Ideally yatras he is a volunteer/participant of should be returned
		} else if (role.equals(Type.MENTOR)) {
			yatras = yatraRepo.findYatrasForMentor(devoteeId);
		} else if (role.equals(Type.YATRA_ADMIN)) {
			yatras = yatraRepo.findYatrasForYatraAdmin(devoteeId);
			List<Yatra> mentorshipYatras = yatraRepo.findYatrasForMentor(devoteeId);
			for (Yatra y: mentorshipYatras) {
				yatras.add(y);
			}
		}

		List<Long> yatraIds = new ArrayList<>();
		for (Yatra y : yatras) {
			yatraIds.add(y.getId());
		}

		return yatraIds;
	}

	/**
	 * Search for devotee according to both context and permissions of the requester
	 * Context is provided by programId and yatraId. Only of them is expected to be present. Controller passes
	 * the other value as 0. If either of them is present, then search is done based on only that yatra
	 *
	 */
	public Page<Devotee> devoteeSearch(String q, long programId, long yatraId, long devoteeId, Type role, Pageable pageable) {
		List<Long> associatedYatraIds = getDevoteeAssociatedYatras(devoteeId, role);
		// get the context yatra id
		List<Long> searchYatraIds = new ArrayList<>();

		// From the given programId and yatraId, only one can be considered for
		// the context of search.
		// Priority is given to yatraId
		if (yatraId > 0) {
			searchYatraIds.add(yatraId);
		} else if (programId > 0) {
			// find yatra from program
			Program program = programRepo.findById(programId);
			if (program != null) {
				searchYatraIds.add(program.getParentYatra().getId());
			}
		}
		// System.out.println("Input search yatra ids");
		// System.out.println(searchYatraIds);

		// System.out.println("Associated yatra ids");
		// System.out.println(associatedYatraIds);
		// if no proper input search yatra then take the associated yatras
		// (becomes sort of a global search within all yatras user is associated with)
		searchYatraIds = searchYatraIds.isEmpty() ? associatedYatraIds : searchYatraIds;

		// System.out.println("Final search yatra ids");
		// System.out.println(searchYatraIds);

		if (searchYatraIds.isEmpty()) {
			// if role is super admin then we do a global search
			if (role.equals(Type.ADMIN)) {
				// System.out.println("Doing search for admin");
				return devoteeRepo.findByQuery(q, pageable);
			} else {
				// else we do search only in captured list
				// System.out.println("Searching in user captured list");
				return devoteeRepo.findDevoteesInUserCapturedList(q, devoteeId, pageable);
			}
		}

                // we do a normal search over yatra contacts and captured list
		// System.out.println("Doing general search");
		return devoteeRepo.findDevoteesForUserSearch(q, searchYatraIds, devoteeId, pageable);
	}
}
