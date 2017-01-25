package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import com.example.common.PersonCrudTest;
import com.example.domain.Person;

public class PersonServiceTest extends PersonCrudTest{
    Random random = new Random();

    @Autowired
    PersonService personService;
    
    @Before
    public void before(){
    	personCrud = personService;
    }
    
    @Test
    public void testCreateMultipleFail() {
        Person person = this.createRandomPerson();
        Person person2 = this.createRandomPerson();
        person2.setGender("AB");
        List<Person> people = new ArrayList<>();
        people.add(person);
        people.add(person2);
        try {
        	personService.add(people);
        	Assert.fail("Should not be able to add a person with gender field more than one character" + person);
    	} catch(DataIntegrityViolationException e){
    		Assert.assertTrue(true);
    	}
        List<Person> persons = personService.find();
        Assert.assertNotNull(persons);
        
        //System.out.println("\n!!!\n!!!\n!!!\n" + persons.size());
        
        boolean found = false;
        for (Person p : persons) {
            if (p.equals(person)) {
                found = true;
                break;
            }
        }

        Assert.assertTrue("Should not have found " + person, !found);
    }
    

   
}
