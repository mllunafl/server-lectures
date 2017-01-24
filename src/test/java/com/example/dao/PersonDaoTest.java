package com.example.dao;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.common.PersonCrudTest;

public class PersonDaoTest extends PersonCrudTest{

    @Autowired
    PersonDao personDao;

    @Before
    public void before(){
    	personCrud = personDao;
    }

}

