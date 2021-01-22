package com.techmojo.trend.domain;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.PriorityQueue;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class LRUCache {

	private HashMap<String, Tag> map;
	private LinkedHashSet<String> set ;
	private PriorityQueue<Tag> queue;
	private HashSet<String> prioritySet ;
	int capacity;
	
	public LRUCache() {
		map=new HashMap<String, Tag>();
		set = new LinkedHashSet<String>();
		this.capacity=50;
		this.queue= new PriorityQueue<Tag>(10, new Comparator<Tag>(){
			@Override
			public int compare(Tag o1, Tag o2) {
				// TODO Auto-generated method stub
				if (o1.getValue() < o2.getValue()) 
                    return -1; 
                else if (o1.getValue() > o2.getValue()) 
                    return 1; 
                return 0;
			}
		});
		this.prioritySet = new HashSet<String>();
	}

	public synchronized Tag get(String key) {
		if(set.contains(key)){
			set.remove(key);
			set.add(key);
			return map.get(key);
		}
		return null;
	}

	public synchronized void put(String key, int value) {
		if(set.size()<capacity || set.contains(key)){
			set.remove(key);
			set.add(key);
		}
		else{
			String firstKey = set.iterator().next();
			set.remove(firstKey);
			
			// the below statements will make sure we remove it from top 10 as well(rare case of collision)
			Tag t = map.get(firstKey);
			t.setValue(-1);
			map.put(firstKey, t);
			
			
			map.remove(firstKey);
			set.add(key);            
		}
		
		// if map contains key will mean it will be present in priority queue as well
		if(map.containsKey(key)) {
			Tag t = map.get(key);
			t.setValue(t.getValue()+value);
			map.put(key, t);
			// this will handle duplicate and also heapify
			if(queue.peek().equals(map.get(key)) || queue.peek().getValue()< map.get(key).getValue()) {
				if(prioritySet.add(key)) {
					String removed = queue.poll().getTag();
					queue.add(map.get(key));
					prioritySet.remove(removed);
					return;
				}
			}
		}else {
			map.put(key, new Tag(key,value));
			if(queue.size()<10) {
				queue.add(map.get(key));
				this.prioritySet.add(key);
			}
		}
	}

	public PriorityQueue<Tag> getQueue() {
		return new PriorityQueue<>(this.queue);
	}
	
}