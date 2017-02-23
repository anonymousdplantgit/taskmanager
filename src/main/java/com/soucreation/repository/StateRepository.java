package com.soucreation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soucreation.model.State;

public interface StateRepository extends JpaRepository<State, Long> {
	List<State> findByCode(String code);
}