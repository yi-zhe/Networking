package com.networking.ch7;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

// 7.2
public class WorkerCallable implements Callable<Float> {

	private static ConcurrentHashMap<String, Float> map;
    private String partName;

    public WorkerCallable(String partName) {
    	this.partName = partName;
    }
    
	static {
		map = new ConcurrentHashMap<>();
		map.put("Axle", 238.50f);
		map.put("Gear", 45.55f);
		map.put("Wheel", 86.30f);
		map.put("Rotor", 8.50f);
	}

	@Override
	public Float call() throws Exception {
		Float price = map.get(partName);
		System.out.println("WorkerCallable returned " + price);
		return price;
	}

}
