package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.PersonDao;
import com.example.domain.Person;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonDao personDao;
	
	@Override
	@Transactional
	public void add(Person person) {
		personDao.add(person);
	}

	@Override
	@Transactional
	public void add(List<Person> persons) {
		personDao.add(persons);
	}

	@Override
	public List<Person> find() {
		return personDao.find();
	}
	
}
