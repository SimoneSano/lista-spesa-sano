package com.ssimone94.listaspesasano;

public class Entry implements Comparable<Entry>{
	private String descrizione;
	private int moltiplicatore;
	private boolean checked;
	
	public Entry(String desc, int mul, boolean checked){
		this.descrizione = desc;
		this.moltiplicatore = mul;
		this.checked = checked;
	}
	
	public String getDescrizione(){
		return this.descrizione;
	}
	
	public int getMoltiplicatore(){
		return this.moltiplicatore;
	}
	
	public boolean isChecked(){
		return this.checked;
	}
	
	public void setMoltiplicatore(int mul){
		this.moltiplicatore = mul;
	}
	
	public void check(){
		this.checked = true;
	}
	
	public void uncheck(){
		this.checked = false;
	}
	
	@Override
	public int compareTo(Entry that) {
		return this.descrizione.compareTo(that.descrizione);
	}
}
