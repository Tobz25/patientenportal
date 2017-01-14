package org.softwaredevelopment.webportal.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public abstract class BaseClass {

	private long Id;
	
	/*
	 * Getter & Setter
	 */
	
	public long getId() {
		return this.Id;
	}
	
}
