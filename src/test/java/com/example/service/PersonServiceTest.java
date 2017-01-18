package com.example.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.service.PersonService;
import com.example.domain.Person;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonServiceTest {
    Random random = new Random();

    @Autowired
    PersonService personService;

    @Test
    public void testCreate() {
        Person person = this.createRandomPerson();
        personService.add(person);
        List<Person> persons = personService.find();
        Assert.assertNotNull(persons);
        Assert.assertTrue(persons.size() > 0);
        boolean found = false;
        for (Person p : persons) {
            if (p.equals(person)) {
                found = true;
                break;
            }
        }

        Assert.assertTrue("Could not find " + person, found);
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
    
    @Test
    public void testCannotCreate() {
    	Person person = createRandomPerson();
    	person.setGender("AB");
    	try {
        	personService.add(person);
        	Assert.fail("Should not be able to add a person with gender field more than one character" + person);
    	} catch(DataIntegrityViolationException e){
    		// all good
    		// System.out.println("all good!!!!");
    	}
    }

    

    private Person createRandomPerson() {
        Person person = new Person();
        person.setName(Integer.toString(random.nextInt()));
        person.setDob(LocalDate.now());
        person.setGender(random.nextBoolean() ? "F" : "M");
        return person;
    }
}
