package com.ssimone94.listaspesasano;

import java.util.List;

public class Lista implements Comparable<Lista>{
	private String name;
	private List<Entry> entries;
	
	public Lista(String name, List<Entry> ents){
		this.name = name;
		this.entries = ents;
	}
	
	public String getName(){
		return this.name;
	}
	
	public List<Entry> getEntries(){
		return this.entries;
	}
	
	public void setName(String name){
		this.name = name;
	}

	@Override
	public int compareTo(Lista that) {
		return this.name.compareTo(that.getName());
	}
	
	@Override
	public int hashCode(){
		return this.name.hashCode();
	}
	
	@Override
	public boolean equals(Object obj){
		try{
			Lista that = (Lista)obj;
			return this.name.equals(that.getName());
		} catch(Exception e){
			return false;
		}
	}
}
