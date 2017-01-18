package com.example.dao;

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

import com.example.domain.Person;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonDaoTest {

    Random random = new Random();

    @Autowired
    PersonDao personDao;

    @Test
    public void testCreate() {
        Person person = this.createRandomPerson();
        personDao.add(person);
        List<Person> persons = personDao.find();
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
    public void testCreateMultiple() {
        Person person = this.createRandomPerson();
        Person person2 = this.createRandomPerson();
        List<Person> people = new ArrayList<>();
        people.add(person);
        people.add(person2);
        personDao.add(people);
        List<Person> persons = personDao.find();
        Assert.assertNotNull(persons);
        Assert.assertTrue(persons.size() > 1);
        
        //System.out.println("\n!!!\n!!!\n!!!\n" + persons.size());
        
        boolean found = false;
        for (Person p : persons) {
            if (p.equals(person)) {
                found = true;
                break;
            }
        }

        boolean found2 = false;
        for (Person p : persons) {
            if (p.equals(person2)) {
                found2 = true;
                break;
            }
        }

        Assert.assertTrue("Could not find " + person, found);
        Assert.assertTrue("Could not find second " + person2, found2);
    }
    
    @Test
    public void testCannotCreate() {
    	Person person = createRandomPerson();
    	person.setGender("AB");
    	try {
        	personDao.add(person);
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

