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
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.springacentesbmdeneme.Repository.abstracts.BasePriceJpa;
import com.springacentesbmdeneme.Repository.abstracts.DaskJpa;
import com.springacentesbmdeneme.Repository.abstracts.PersonJpa;
import com.springacentesbmdeneme.Repository.abstracts.VehicleAgeRangeJpa;
import com.springacentesbmdeneme.Repository.abstracts.VehicleBrandJpa;
import com.springacentesbmdeneme.Repository.abstracts.VehicleJpa;
import com.springacentesbmdeneme.Repository.abstracts.VehicleTypeJpa;
import com.springacentesbmdeneme.Service.abstracts.AreaService;
import com.springacentesbmdeneme.Service.abstracts.BasePriceService;
import com.springacentesbmdeneme.Service.abstracts.BuildAgeService;
import com.springacentesbmdeneme.Service.abstracts.BuildTypeService;
import com.springacentesbmdeneme.Service.abstracts.CityService;
import com.springacentesbmdeneme.Service.abstracts.DaskService;
import com.springacentesbmdeneme.Service.abstracts.NumberofFloorsService;
import com.springacentesbmdeneme.Service.abstracts.PersonService;
import com.springacentesbmdeneme.Service.abstracts.ProposalService;
import com.springacentesbmdeneme.Service.abstracts.VehicleAgeRangeService;
import com.springacentesbmdeneme.Service.abstracts.VehicleBrandService;
import com.springacentesbmdeneme.Service.abstracts.VehicleTypeService;
import com.springacentesbmdeneme.entites.Area;
import com.springacentesbmdeneme.entites.BasePrice;
import com.springacentesbmdeneme.entites.BuildAge;
import com.springacentesbmdeneme.entites.BuildType;
import com.springacentesbmdeneme.entites.City;
import com.springacentesbmdeneme.entites.Dask;
import com.springacentesbmdeneme.entites.Life;
import com.springacentesbmdeneme.entites.NumberofFloors;
import com.springacentesbmdeneme.entites.Person;
import com.springacentesbmdeneme.entites.Proposal;
import com.springacentesbmdeneme.entites.Vehicle;
import com.springacentesbmdeneme.entites.VehicleAgeRange;
import com.springacentesbmdeneme.entites.VehicleBrand;
import com.springacentesbmdeneme.entites.VehicleType;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(connection=EmbeddedDatabaseConnection.H2)
public class DaskManagerTest {
	@Autowired
	private DaskManager daskService;
	@Autowired
	private DaskJpa daskDao;
	@Autowired
	private PersonJpa personJpa;
	@Autowired 
	private PersonService personService;
	@Autowired
	private ProposalService proposalService;
	@Autowired
	private BuildTypeService buildTypeService;
	@Autowired
	private CityService cityService;
	@Autowired
	private AreaService areaService;
	@MockBean
	private BasePriceService basePriceService;
	@Autowired
	private BuildAgeService buildAgeService;
	@Autowired
	private NumberofFloorsService numberofFloorsService;
	@Mock
	private Dask dask;
	@MockBean
	private Person person;
	@MockBean
	private BasePrice basePrice;
	@MockBean
	private Area area;
	@Mock
	private BuildAge buildAge;
	@MockBean
	private City city;
	@MockBean
	private NumberofFloors numberFloor;
	@MockBean
	private BuildType buildType;
	@MockBean
	private Proposal proposal;
	@BeforeEach
	void init() {
		 person= new Person().builder()
					.first_name("Kaan")
					.last_name("Erdoğan")
					.email("test@Mail.com")
					.tc(484216521L)
					.dasks(new ArrayList<>())
					.birth_date(new Date())
					.build();
		personService.save(person);
			 buildType=new BuildType()
					.builder()
					.id(1L)
					.name("buildType")
					.price_multiplier(1)
					.build();
			buildTypeService.save(buildType);
			buildAge=new BuildAge().builder()
					.id(1L)
					.max(10)
					.min(1)
					.title("buildAge")
					.price_multiplier(1.0)
					.build();
			buildAgeService.save(buildAge);
			
			 area=new Area().builder()
					 .id(1L)
					 .max(10)
					 .min(1)
					 .price_multiplier(1)
					 .title("area")
					 .build();
			 areaService.save(area);
			
			 basePrice=new BasePrice().builder().
					type("Vehicle")
					.initialprice(1800.00).build();
			 city=new City().builder()
					 .id(1L)
					 .name("city")
					 .price_multiplier(1)
					 .build();
			 cityService.save(city);
			 numberFloor=new NumberofFloors().builder()
					 .id(1L)
					 .max(10)
					 .min(1)
					 .price_multiplier(1)
					 .title("floor")
					 .build();
					 numberofFloorsService.save(numberFloor);
			dask=new Dask().builder()
					.adress("adres1")
					.area(area)
					.build_age(buildAge)
					.buildtype(buildType)
					.city(city)
					.person(person)
					.floor(numberFloor)
					.daskproposal(new ArrayList<>())
					.build();
			daskService.save(dask);
	}
	@Test
	void daskCreate_Test() {
		
		Assertions.assertThat(dask.getId()).isGreaterThan(0L);
	}
	@Test
	void daskInsuranceCreateResponseTest() {
		when(basePriceService.getByType("Dask")).thenReturn(basePrice);
		Proposal proposal=daskService.daskInsuranceCreateResponse(dask);
		Assertions.assertThat(proposal.getPrice()).isEqualTo(1800.00);
	}
	@Test
	void daskGetAll_Test() {
		Assertions.assertThat(daskService.getAll().size()).isGreaterThan(0);
	
	}
	@Test
	void daskDeleteTest() {
		int init=daskService.getAll().size();
		daskService.delete(dask.getId());
		int last=daskService.getAll().size();
		Assertions.assertThat(init-last).isEqualTo(1);
	}
	@Test
	void getDaskById_Test() {
		
		
		Dask daskupdate=daskService.getDaskById(dask.getId());
		Assertions.assertThat(daskupdate.getAdress()).isEqualTo(dask.getAdress());
	}
	@Test
	void getDaskbyPersonId_Test() {
		
		Assertions.assertThat(daskService.getDaskByPersonId(person.getId())).isNotNull();
	}
	
}
