package com.example.service;

import java.util.List;

import com.example.domain.Person;

public interface PersonService {
	void add(Person person);
    void add(List<Person> persons);
    List<Person> find();
    Person find(int id);
}
