package com.example.common;

import java.util.List;

import com.example.domain.Person;

public interface PersonCrud {
   void add(Person person);
   List<Person> find();
   void add(List<Person> persons);
}
