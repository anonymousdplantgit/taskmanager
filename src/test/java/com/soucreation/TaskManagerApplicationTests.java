package com.soucreation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.soucreation.repository.RessourceRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskManagerApplicationTests {
	@Autowired
	RessourceRepository repository;

	@Test
	public void contextLoads() {
	}
	@Test
	public void findAll(){
		repository.findAll();
	}

}
