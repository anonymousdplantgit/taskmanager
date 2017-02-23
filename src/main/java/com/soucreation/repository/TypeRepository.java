package com.soucreation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soucreation.model.Type;

public interface TypeRepository extends JpaRepository<Type, Long> {
	List<Type> findByCode(String code);
}