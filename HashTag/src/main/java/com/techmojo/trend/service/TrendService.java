package com.techmojo.trend.service;

import java.util.List;

import com.techmojo.trend.domain.Tag;

public interface TrendService {
	public boolean parseString(String text);
	public List<Tag> getTrend();
	
}
