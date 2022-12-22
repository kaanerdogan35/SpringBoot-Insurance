package com.springacentesbmdeneme.Repository.abstracts;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import com.springacentesbmdeneme.entites.Person;
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(connection=EmbeddedDatabaseConnection.H2)
class PersonJpaTest {

	@Autowired
	private PersonJpa personJpa;
	

	@Test
	void PersonJpaRepository_SaveMethodTest() {
		//Arrange
		Person person= new Person().builder()
				.first_name("Kaan")
				.last_name("Erdoğan")
				.email("test@Mail.com")
				.tc(484216521L)
				.birth_date(new Date())
				
				.build();
		//ACT
		personJpa.save(person);
		//Assert	
		Assertions.assertThat(person.getId()).isGreaterThan(0L);

	}
	@Test
	void PersonJpaRepository_FindAllMethodTest() {
		Person person= new Person().builder()
				.first_name("Kaan")
				.last_name("Erdoğan")
				.email("test@Mail.com")
				.tc(484216521L)
				.birth_date(new Date())
				.build();
		Person person1= new Person().builder()
				.first_name("Kaan")
				.last_name("Erdoğan")
				.email("test@Mail.com")
				.tc(484216521L)
				.birth_date(new Date())
				.build();
		personJpa.save(person);
		personJpa.save(person1);
		
		List<Person> personList=personJpa.findAll();
		
		
		Assertions.assertThat(personList).isNotNull();
		Assertions.assertThat(personList.size()).isEqualTo(2);
		
	}
	
	@Test
	void personRepository_FindById_ReturnsMoreThanOnePerson() {
		Person person= new Person().builder()
				.first_name("Kaan")
				.last_name("Erdoğan")
				.email("test@Mail.com")
				.tc(484216521L)
				.birth_date(new Date())
				.build();
		personJpa.save(person);
		Person personReturn=personJpa.findById(person.getId()).get();
		
		
		Assertions.assertThat(personReturn).isNotNull();
	}
	@Test
	void PersonJpaRepository_UpdateMethodTest() {
		//Arrange
		Person person= new Person().builder()
				.first_name("Kaan")
				.last_name("Erdoğan")
				.email("test@Mail.com")
				.tc(484216521L)
				.birth_date(new Date())
				.build();
		//ACT
		personJpa.save(person);
		Person personUpdate=personJpa.findById(person.getId()).get();
		personUpdate.setFirst_name("Nazif");
		personUpdate.setLast_name("Ilbek");
		personUpdate.setEmail("test2@mail.com");
		personUpdate.setTc(888888888L);
		personJpa.save(personUpdate);
		//Assert	
		Assertions.assertThat(personJpa.findById(person.getId()).get().getTc()).isEqualTo(888888888L);
		Assertions.assertThat(personJpa.findById(person.getId()).get().getFirst_name()).isEqualTo("Nazif");
		Assertions.assertThat(personJpa.findById(person.getId()).get().getLast_name()).isEqualTo("Ilbek");
		Assertions.assertThat(personJpa.findById(person.getId()).get().getEmail()).isEqualTo("test2@mail.com");
	}
	
	@Test
	void PersonJpaRepository_DeleteMethodTest() {
		Person person= new Person().builder()
				.first_name("Kaan")
				.last_name("Erdoğan")
				.email("test@Mail.com")
				.tc(484216521L)
				.birth_date(new Date())
				.build();
		personJpa.save(person);
		
	
		personJpa.delete(person);
		
		Assertions.assertThat(personJpa.findAll().size()).isEqualTo(0);
	}
	@Test
	void PersonJpaRepository_findPersonByTcMethodTest() {
		Person person= new Person().builder()
				.first_name("Kaan")
				.last_name("Erdoğan")
				.email("test@Mail.com")
				.tc(484216521L)
				.birth_date(new Date())
				.build();
		personJpa.save(person);
		
		Person personReturn=personJpa.findPersonByTc(person.getTc());
		Assertions.assertThat(personReturn.getId()).isEqualTo(person.getId());
	}
	
	
	

}
