package com.springacentesbmdeneme.Service.concretes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;

import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.hibernate.query.criteria.internal.expression.SimpleCaseExpression.WhenClause;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.autoconfigure.webservices.client.WebServiceClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.springacentesbmdeneme.Repository.abstracts.BasePriceJpa;
import com.springacentesbmdeneme.Repository.abstracts.PersonJpa;
import com.springacentesbmdeneme.Repository.abstracts.VehicleAgeRangeJpa;
import com.springacentesbmdeneme.Repository.abstracts.VehicleBrandJpa;
import com.springacentesbmdeneme.Repository.abstracts.VehicleJpa;
import com.springacentesbmdeneme.Repository.abstracts.VehicleTypeJpa;
import com.springacentesbmdeneme.Service.abstracts.BasePriceService;
import com.springacentesbmdeneme.Service.abstracts.DaskService;
import com.springacentesbmdeneme.Service.abstracts.LifeService;
import com.springacentesbmdeneme.Service.abstracts.PersonService;
import com.springacentesbmdeneme.Service.abstracts.ProposalService;
import com.springacentesbmdeneme.Service.abstracts.VehicleAgeRangeService;
import com.springacentesbmdeneme.Service.abstracts.VehicleBrandService;
import com.springacentesbmdeneme.Service.abstracts.VehicleService;
import com.springacentesbmdeneme.Service.abstracts.VehicleTypeService;
import com.springacentesbmdeneme.entites.BasePrice;
import com.springacentesbmdeneme.entites.Life;
import com.springacentesbmdeneme.entites.Person;
import com.springacentesbmdeneme.entites.Proposal;
import com.springacentesbmdeneme.entites.Vehicle;
import com.springacentesbmdeneme.entites.VehicleAgeRange;
import com.springacentesbmdeneme.entites.VehicleBrand;
import com.springacentesbmdeneme.entites.VehicleType;
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(connection=EmbeddedDatabaseConnection.H2)
class VehicleManagerTest {
	@Autowired
	private VehicleManager vehicleService;
	@Autowired
	private VehicleJpa vehicleDao;
	@Autowired
	private PersonJpa personJpa;
	@Autowired 
	private PersonService personService;
	@Autowired
	private ProposalService proposalService;
	@Autowired
	private VehicleBrandService vehicleBrandService;
	@MockBean
	private VehicleAgeRangeService vehicleAgeRangeService;
	@Autowired
	private VehicleAgeRangeJpa vehicleAgeRangeDao;
	@MockBean
	private BasePriceService basePriceService;
	@Autowired
	private VehicleTypeService vehicleTypeService;
	@Mock
	private Vehicle vehicle;
	@MockBean
	private Person person;
	@MockBean
	private BasePrice basePrice;
	@MockBean
	private VehicleBrand vehicleBrand;
	@Mock
	private VehicleAgeRange vehicleAgeRange;
	@MockBean
	private VehicleType vehicleType;
	@MockBean
	private Proposal proposal;
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
			 vehicleBrand=new VehicleBrand()
					
					.builder()
					.id(1L)
					.name("BMW")
					.price_multiplier(1.8)
					.vehicle(new ArrayList<>())
					.build();
			vehicleBrandService.save(vehicleBrand);
			vehicleType=new VehicleType().builder().
					name("Sedan")
					.id(1L)
					.vehicle(new ArrayList<>())
					.price_multiplier(1.0).
					build();
			vehicleTypeService.save(vehicleType);
			
			 vehicleAgeRange=new VehicleAgeRange().builder().
					maxAge(122).minAge(50).
					id(1L).price_multiplier(4.0).
					vehicles(new ArrayList<>()).
					build();
			 vehicleAgeRangeDao.save(vehicleAgeRange);
			
			 basePrice=new BasePrice().builder().
					type("Vehicle")
					.initialprice(1800.00).build();
			
			vehicle=new Vehicle().builder()
					
					.chassis_number("15315135153")
					.plate("00Test00")
					.modelYear(1999)
					.person(person)
					.vehicleBrand(vehicleBrand)
					.vehicleType(vehicleType)
					.proposal(new ArrayList<Proposal>())
					.build();
			vehicleService.save(vehicle);
		
	}
	@Test
	void vehicleCreate_Test() {
		
		Assertions.assertThat(vehicle.getId()).isGreaterThan(0L);
	}
	@Test
	void vehicleInsuranceCreateResponseTest() {
		when(basePriceService.getByType("Vehicle")).thenReturn(basePrice);
		when(vehicleAgeRangeService.choiceAgeRange(anyInt(),any(Date.class))).thenReturn(vehicleAgeRange);
		Proposal proposal=vehicleService.vehicleInsuranceCreateResponse(vehicle);
		
		Assertions.assertThat(proposal.getPrice()).isEqualTo(12960.00);
	}
	
	@Test
	void vehicleGetAll_Test() {
		Assertions.assertThat(vehicleService.getAll().size()).isGreaterThan(0);
	
	}
	@Test
	void vehicleDeleteTest() {
		int init=vehicleService.getAll().size();
		vehicleService.delete(1L);
		int last=vehicleService.getAll().size();
		Assertions.assertThat(init-last).isEqualTo(1);
	}
	@Test
	void getVehicleById_Test() {
		
		
		Vehicle vehicleupdate=vehicleService.getVehicleById(vehicle.getId());
		Assertions.assertThat(vehicleupdate.getChassis_number()).isEqualTo(vehicle.getChassis_number());
	}
	@Test
	void getPersonIdByVehicleId_Test() {
		Vehicle vehicle1=new Vehicle().builder()
	
				.chassis_number("23131313")
				.plate("00Te2st00")
				.modelYear(3133)
				.person(person)
				.vehicleBrand(vehicleBrand)
				.vehicleType(vehicleType)
				.proposal(new ArrayList<Proposal>())
				.build();
			vehicleService.save(vehicle1);
		Assertions.assertThat(vehicleService.getPersonIdByVehicleId(vehicle1.getId())).isEqualTo(person.getId());
	}
	@Test
	void getVehiclebyPersonId_Test() {
		
		Assertions.assertThat(vehicleService.getVehiclebyPersonId(person.getId())).isNotNull();
	}
}
