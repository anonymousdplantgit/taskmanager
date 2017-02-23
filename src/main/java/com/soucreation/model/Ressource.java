package com.soucreation.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.domain.Persistable;

@Entity
public class Ressource implements Persistable<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long ressourceId;
	private String firstName;
	private String lastName;
	private String code;

	public Long getRessourceId() {
		return ressourceId;
	}

	public void setRessourceId(Long ressourceId) {
		this.ressourceId = ressourceId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public Long getId() {
		return ressourceId;
	}

	@Override
	public boolean isNew() {
		return null == getId() ;
	}

	
}