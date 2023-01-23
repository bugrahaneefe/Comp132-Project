package main;

import java.util.HashMap;

public class equipment {
	
	/*
	 * Purpose of this class is to create hashmap which maps equipment name to equipment value.
	 */
	
	HashMap<String,Integer> equipments;
	
	public equipment() {
		equipments = new HashMap<String,Integer>(); 
	}
	
	public void addEquipment(String equname,int price) {
		equipments.put(equname, price);
	}
	
	public HashMap<String, Integer> getEquipments() {
		return equipments;
	}
	
}
