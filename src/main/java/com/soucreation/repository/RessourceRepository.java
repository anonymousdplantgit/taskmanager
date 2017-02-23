package com.soucreation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soucreation.model.Ressource;

public interface RessourceRepository extends JpaRepository<Ressource, Long> {
	List<Ressource> findByCode(String code);
}