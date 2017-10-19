package com.giridhari.preachingassistant.service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.giridhari.preachingassistant.db.model.Yatra;
import com.giridhari.preachingassistant.model.YatraType;

@Service
public interface YatraService {
	
	public List<Yatra> list();
	
	public Yatra getById(long yatraId);
	
	public void create(Yatra yatra);
	
	public void update(Yatra yatra);
	
	public void delete(long yatraId);
	
}
