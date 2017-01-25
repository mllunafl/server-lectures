package com.example.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.domain.Person;

@Repository
public class PersonDaoImpl implements PersonDao {
	
	private static final Logger log = LoggerFactory.getLogger(PersonDaoImpl.class);
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public void add(Person person) {
		log.info("adding " + person);
		jdbcTemplate.update("INSERT INTO person(name, dob, gender) VALUES (?,?,?)",
				person.getName(),
				person.getDob(),
				person.getGender());
		
	}
	
	@Override
	public void add(List<Person> persons) {
		// 1. for loop with sql
		// 2. for loop call add(person)
		// 3. jdbc template batch update(persons)
		for	(Person person : persons){
			this.add(person);
		}
		
	}

	@Override
	public List<Person> find() {
		List<Person> persons = this.jdbcTemplate.query(
                "select * from person",
                new PersonMapper());
        log.info("Found " + persons.size() + " persons");
		return persons;
	}
	
	private static class PersonMapper implements RowMapper<Person>{

		@Override
		public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
			Person person = new Person();
			person.setId(rs.getInt("id"));
			person.setName(rs.getString("name"));
            person.setGender(rs.getString("gender"));
            person.setDob(rs.getDate("dob").toLocalDate());
            return person;
		}
	
	}

	@Override
	public Person find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
