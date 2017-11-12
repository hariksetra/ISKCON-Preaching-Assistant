package com.giridhari.preachingassistant.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.giridhari.preachingassistant.db.model.Yatra;
import com.querydsl.jpa.impl.JPAQuery;

public class CollectionUtils {
	
	public static <E> Collection<E> makeCollection(Iterable<E> iter) {
	    Collection<E> list = new ArrayList<E>();
	    for (E item : iter) {
	        list.add(item);
	    }
	    return list;
	}
	
	public static String strJoin(String[] aArr, String sSep) {
	    StringBuilder sbStr = new StringBuilder();
	    for (int i = 0, il = aArr.length; i < il; i++) {
	        if (i > 0)
	            sbStr.append(sSep);
	        sbStr.append(aArr[i]);
	    }
	    return sbStr.toString();
	}
	
	public static <T> Page<T> getPage(JPAQuery<T> query, Pageable pageable)
	{
		List<T> list = query.fetch();
		int start = pageable.getOffset();
		int end = (start + pageable.getPageSize()) > list.size() ? list.size() : (start + pageable.getPageSize());
		
		Page<T> page = new PageImpl<T>(list.subList(start, end), pageable, list.size());
		return page;
	}
	
	public static <T> List<T> getListForPage(JPAQuery<T> query, Pageable pageable)
	{
		List<T> list = query.fetch();
		int start = pageable.getOffset();
		int end = (start + pageable.getPageSize()) > list.size() ? list.size() : (start + pageable.getPageSize());
		
		return list.subList(start, end);
	}

}
