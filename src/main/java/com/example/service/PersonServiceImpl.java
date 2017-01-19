package com.example.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.PersonDao;
import com.example.dao.PersonDaoImpl;
import com.example.domain.Person;

@Service
public class PersonServiceImpl implements PersonService {

	private static final Logger log = LoggerFactory.getLogger(PersonServiceImpl.class);
	
	@Autowired
	private PersonDao personDao;
	
	@Override
	@Transactional
	public void add(Person person) {
		log.debug("adding a person " + person);
		personDao.add(person);
	}

	@Override
	@Transactional
	public void add(List<Person> persons) {
		log.info("adding a people " + persons);
		personDao.add(persons);
	}

	@Override
	public List<Person> find() {
		log.warn("finding peopele");
		return personDao.find();
	}
	
}
