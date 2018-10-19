package edu.utfpr.cp.sa.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Country implements Serializable {
	
	@Id @GeneratedValue
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
