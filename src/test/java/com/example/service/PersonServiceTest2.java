package com.example.service;
import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.dao.PersonDao;
import com.example.domain.Person;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonServiceTest2 {

    @MockBean
    PersonDao personDao;

    @Autowired
    PersonService personService;

    @Test
    public void testFindById() {
        Person person = new Person();
        person.setId(1);
        person.setName("John Doe");
        person.setDob(LocalDate.now());
        person.setGender("M");
        //person.setContacted(LocalDate.now());
        org.mockito.BDDMockito.given(personDao.find(1)).willReturn(person);

        Person person2 = personService.find(1);
        System.out.println(person2);
        Assert.assertNotNull(person2);
        Assert.assertEquals(person, person2);
    }
}

