package com.springacentesbmdeneme.Service.concretes;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.springacentesbmdeneme.Repository.abstracts.PersonAgeJpa;
import com.springacentesbmdeneme.Repository.abstracts.PersonJpa;
import com.springacentesbmdeneme.Repository.abstracts.VehicleJpa;
import com.springacentesbmdeneme.Service.abstracts.BasePriceService;
import com.springacentesbmdeneme.Service.abstracts.CareerService;
import com.springacentesbmdeneme.Service.abstracts.DiseaseService;
import com.springacentesbmdeneme.Service.abstracts.LifeService;
import com.springacentesbmdeneme.Service.abstracts.PersonAgeService;
import com.springacentesbmdeneme.Service.abstracts.PersonService;
import com.springacentesbmdeneme.Service.abstracts.ProposalService;
import com.springacentesbmdeneme.Service.abstracts.VehicleAgeRangeService;
import com.springacentesbmdeneme.entites.BasePrice;
import com.springacentesbmdeneme.entites.Career;
import com.springacentesbmdeneme.entites.Disease;
import com.springacentesbmdeneme.entites.Life;
import com.springacentesbmdeneme.entites.Person;
import com.springacentesbmdeneme.entites.PersonAge;
import com.springacentesbmdeneme.entites.Proposal;
import com.springacentesbmdeneme.entites.Vehicle;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(connection=EmbeddedDatabaseConnection.H2)
public class LifeManagerTest {
	@Autowired
	private VehicleJpa vehicleDao;
	@Autowired
	private LifeService lifeService;
	@Autowired
	private ProposalService proposalService;
	@Autowired
	private CareerService careerService;
	@Autowired 
	private PersonService personService;
	@Autowired
	private DiseaseService diseaseService;
	@MockBean
	private PersonAgeService personAgeService;
	@Autowired
	private PersonAgeJpa personAgejpa;
	@Autowired
	private VehicleAgeRangeService vehicleAgeRangeService;
	@MockBean
	private BasePriceService basePriceService;
	@Autowired
	private PersonJpa personJpa;
	@Mock
	private Life life;
	@MockBean
	private Person person;
	@MockBean
	private Career career;
	@MockBean
	private Disease disease;
	@MockBean
	private PersonAge personAge;
	@MockBean
	private BasePrice basePrice;
	@BeforeEach
	void init() {
		 person= new Person().builder()
					.first_name("Kaan")
					.last_name("Erdoğan")
					.email("test@Mail.com")
					.tc(484216521L)
					.vehicles(new ArrayList<>())
					.birth_date(new Date())
					.build();
		 personService.save(person);
		 personAge=new PersonAge().builder()
				 .id(1L)
				 .max(20)
				 .min(1)
				 .price_multiplier(1)
				 .title("personAge")
				 .build();
		 personAgeService.save(personAge);
		 basePrice = new BasePrice().builder().
					type("Vehicle")
					.initialprice(10000.00).build();
		career=new Career().builder()
				.id(1L)
				.name("career")
				.price_multiplier(1)
				.build();
		careerService.save(career);
		disease=new Disease().builder()
				.id(1L)
				.name("disease")
				.price_multiplier(1)
				.build();
		diseaseService.save(disease);
		life=new Life().builder()
				.career(career)
				.disease(disease)
				.person(person)
				.security_deposit(100000.0)
				.build();
		lifeService.save(life);
		
	}
	@Test
	void lifeCreate_Test() {
		
		Assertions.assertThat(life.getId()).isGreaterThan(0L);
	}
	@Test
	void lifeInsuranceCreateResponseTest() {
		when(basePriceService.getByType("Life")).thenReturn(basePrice);
		when(personAgeService.choiceAgeRange(any(Date.class),any(Date.class))).thenReturn(personAge);
		Proposal proposal=lifeService.lifeInsuranceCreateResponse(life);
		
		Assertions.assertThat(proposal.getPrice()).isEqualTo(10.00);
	}
	@Test
	void lifeGetAll_Test() {
		Assertions.assertThat(lifeService.getAll().size()).isGreaterThan(0);
	
	}
	@Test
	void lifeDeleteTest() {
		int init=lifeService.getAll().size();
		lifeService.delete(1L);
		int last=lifeService.getAll().size();
		Assertions.assertThat(init-last).isEqualTo(1);
	}
	@Test
	void getLifeById_Test() {
		Life lifeupdate=lifeService.getLifeById(life.getId());
		Assertions.assertThat(lifeupdate.getSecurity_deposit()).isEqualTo(life.getSecurity_deposit());
	}
	@Test
	void getLifeByProposalId_Test() {
		
		Proposal proposal=new Proposal().builder()
				.id(1L)
				.build();
		proposalService.save(proposal);
		life.setProposal(proposal);
		lifeService.save(life);
		Life life2=lifeService.getLifeByProposalId(proposal.getId());
		Assertions.assertThat(life.getSecurity_deposit()).isEqualTo(life2.getSecurity_deposit());
	}
	
	@Test
	void getLifebyPersonId_Test() {
		
		Assertions.assertThat(lifeService.getLifeByPersonId(person.getId())).isNotNull();
	}
	
	
}
