package edu.utfpr.cp.sa.entity;

import lombok.Data;

@Data
public class Country {
	
        private Long id;
	private String name;
	private String acronym;
	private int phoneDigits;
	
        @Override
	public int hashCode() {
		return this.getName().hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		Country other = (Country) obj;
		
		return this.getName().equalsIgnoreCase(other.getName());
	}
	
	

}
