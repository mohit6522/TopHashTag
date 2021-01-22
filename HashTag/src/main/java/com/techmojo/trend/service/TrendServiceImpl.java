package com.techmojo.trend.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techmojo.trend.domain.Cache;
import com.techmojo.trend.domain.Tag;


@Service
public class TrendServiceImpl implements TrendService {
	
	@Autowired
	Cache cache;

	@Override
	public boolean parseString(String text) {
		try {
			Pattern pattern = Pattern.compile("#(\\w*[0-9a-zA-Z]+\\w*[0-9a-zA-Z])");
			Matcher matcher=pattern.matcher(text);
			while(matcher.find()) {
				cache.put(matcher.group().substring(1), 1);
			}
			return true;
		}
		catch(Exception e) {
			return false;
		}
		
	}

	@Override
	public List<Tag> getTrend() {
		// TODO Auto-generated method stub
		List<Tag> trendList = new ArrayList<Tag>();
		PriorityQueue<Tag> queue = cache.getQueue();
		Iterator<Tag> itr = queue.iterator();
		while(itr.hasNext())
			trendList.add(itr.next());
		return trendList;
	}

}
