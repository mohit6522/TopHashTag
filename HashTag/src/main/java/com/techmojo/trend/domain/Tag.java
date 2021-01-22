package com.techmojo.trend.domain;

public class Tag implements Comparable<Tag>{
	private String tag;
	private int value=1;
	
	public Tag(String tag, Integer value) {
		this.tag = tag;
		this.value= value;
	}
	public Tag(String tag) {
		this.tag = tag;
	}
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	@Override
	public int compareTo(Tag o2) {
		// TODO Auto-generated method stub
		if (this.getValue() < o2.getValue()) 
            return -1; 
        else if (this.getValue() > o2.getValue()) 
            return 1; 
        return 0;
	}
}
