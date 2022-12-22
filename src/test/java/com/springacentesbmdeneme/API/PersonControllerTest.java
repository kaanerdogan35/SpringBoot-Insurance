package com.springacentesbmdeneme.API;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.MockAwareVerificationMode;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ConcurrentModel;

import com.springacentesbmdeneme.Repository.abstracts.PersonJpa;

import com.springacentesbmdeneme.Service.abstracts.DaskService;
import com.springacentesbmdeneme.Service.abstracts.LifeService;
import com.springacentesbmdeneme.Service.abstracts.PersonService;
import com.springacentesbmdeneme.Service.abstracts.VehicleBrandService;
import com.springacentesbmdeneme.Service.abstracts.VehicleService;
import com.springacentesbmdeneme.Service.abstracts.VehicleTypeService;
import com.springacentesbmdeneme.Service.concretes.PersonManager;
import com.springacentesbmdeneme.entites.BasePrice;
import com.springacentesbmdeneme.entites.Dask;
import com.springacentesbmdeneme.entites.Life;
import com.springacentesbmdeneme.entites.Person;
import com.springacentesbmdeneme.entites.Vehicle;
import com.springacentesbmdeneme.entites.VehicleAgeRange;
import com.springacentesbmdeneme.entites.VehicleBrand;
import com.springacentesbmdeneme.entites.VehicleType;



@WebMvcTest(PersonController.class)
@AutoConfigureMockMvc(addFilters = false)
class PersonControllerTest {
	@Autowired
	private PersonController personController;
	@MockBean 
	private PersonService personService;
	@MockBean
	private VehicleService vehicleService;
	@MockBean 
	private DaskService daskService;
	@MockBean
	private LifeService lifeService;
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private VehicleTypeService vehicleTypeService;
	@MockBean
	private VehicleBrandService vehicleBrandService;
	@MockBean
	private PersonJpa personDao;
	@Mock
	private Person person;
	@Mock 
	private Vehicle vehicle;
   @BeforeEach
   void init() {
	   person=new Person().builder()
			   .id(1L)
				.first_name("Kaan")
				.last_name("Erdoğan")
				.email("test@Mail.com")
				.tc(484216521L)
				.birth_date(new Date())
				.build();
	   personService.save(person);
	   VehicleBrand vehicleBrand=new VehicleBrand()
				
				.builder()
				.id(1L)
				.name("BMW")
				.price_multiplier(1.8)
				.vehicle(new ArrayList<>())
				.build();
		
		VehicleType vehicleType=new VehicleType().builder().
				name("Sedan")
				.id(1L)
				.vehicle(new ArrayList<>())
				.price_multiplier(1.0).
				build();
		
		VehicleAgeRange vehicleAgeRange=new VehicleAgeRange().builder().
				maxAge(122).minAge(50).
				id(1L).price_multiplier(4.0).
				vehicles(new ArrayList<>()).
				build();
		
		BasePrice basePrice=new BasePrice().builder().
				type("Vehicle")
				.initialprice(1800.00).build();
		
		vehicle=new Vehicle().builder()
				.id(1L)
				.chassis_number("15315135153")
				.plate("00Test00")
				.modelYear(1999)
				.person(person)
				.vehicleAgeRange(vehicleAgeRange)
				.vehicleBrand(vehicleBrand)
				.vehicleType(vehicleType)
				.build();
		vehicleService.save(vehicle);
	  
   }
    @Test
    void personListController_Test()throws Exception{
    
    	ArrayList<Person> personList=new ArrayList<Person>(){{add(person);}}; 
        when(personService.getAll()).thenReturn(personList);
        assertEquals("PersonList", personController.personList(new ConcurrentModel()));
       
    }
    @Test 
    void personListController_Test2() throws Exception{
    	 List<Person> personList=new ArrayList<Person>(){{add(person);}};
		 when(personService.getAll()).thenReturn(personList);
		 MvcResult result=MockMvcBuilders.standaloneSetup(personController)
			.build()
			.perform(get("/person/personList"))
			.andExpect(MockMvcResultMatchers.model().attributeExists("persons")).andReturn();
		 	Map<String,Object> map= result.getModelAndView().getModel();
		    List<Person> persons=(List<Person>)map.get("persons");
	        Assertions.assertThat(persons).isEqualTo(personList);
    }
	@Test
	void personControllerpersonCreate_Test() throws Exception{
		
		when(personService.PersonExistCheck(any())).thenReturn("0");
		
		
		MockMvcBuilders.standaloneSetup(personController)
		.build()
		.perform(post("/person/person_create")
		.param("{first_name}","Kaan")
				.param("{last_name}", "Erdoğan")
				.param("{birth_date}","22/02/2000" )
				.param("{email}", "kaan.erdogan@bahcesehir.edu.tr")
				.param("{tc}", "49405587204"))
		//
		.andExpect(MockMvcResultMatchers.view().name("redirect:/person/personList"))
		.andExpect(MockMvcResultMatchers.model().size(1))
		.andExpect(MockMvcResultMatchers.redirectedUrl("/person/personList"));;
		;
		
		
       
	
	
	}
	
	@Test
	void personControllerpersonCreate_Test2() throws Exception{
		
		when(personService.PersonExistCheck(any())).thenReturn("1");
		
		when(personService.getPersonByTc(any())).thenReturn(person);
		
		MockMvcBuilders.standaloneSetup(personController)
		.build()
		.perform(post("/person/person_create")
		.param("{first_name}","Kaan")
		.param("{last_name}", "Erdoğan")
		.param("{birth_date}","22/02/2000" )
		.param("{email}", "kaan.erdogan@bahcesehir.edu.tr")
		.param("{tc}", "49405587204"))
		.andExpect(MockMvcResultMatchers.view().name("redirect:/person/personExist/1"))
		.andExpect(MockMvcResultMatchers.model().size(1))
		.andExpect(MockMvcResultMatchers.redirectedUrl("/person/personExist/1"));;
		;
		
		
        
	
	
	}
	@Test 
	void personControllerDeleteMethod_Test()throws Exception{

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/person/_delete/{id}", person.getId());
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(personController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.view().name("redirect:/person/personList"))
        		.andExpect(MockMvcResultMatchers.redirectedUrl("/person/personList"));;
               
                ;
        Assertions.assertThat(personService.getPersonByID(person.getId())).isNull();
	}
	
	@Test
	void personEditMethod_Test() throws Exception{
		
		Person personUpdate=new Person();
		personUpdate.setFirst_name("Nazif");
		personUpdate.setLast_name("Ilbek");
		personUpdate.setEmail("test2@mail.com");
		personUpdate.setTc(888888888L);

        MockMvcBuilders.standaloneSetup(personController)
        		.build()
        		.perform(post("/person/_editjob/{id}",1L)
        		.param("{first_name}",personUpdate.getFirst_name())
        		.param("{last_name}", personUpdate.getLast_name())
        		.param("{email}",personUpdate.getEmail())
        		.param("{tc}", personUpdate.getTc().toString()))
       
        		.andExpect(MockMvcResultMatchers.view().name("redirect:/person/personList"))
        		.andExpect(MockMvcResultMatchers.model().size(1))
        		.andExpect(MockMvcResultMatchers.redirectedUrl("/person/personList"));;
	}
	
	
	@Test
	void personActionPage_Test() throws Exception{
		List<Vehicle> vehicleList=new ArrayList<>() {{
				 add(vehicle);
			 }};
		 when(personService.getPersonByID(any())).thenReturn(person);
		 when(lifeService.getLifeByPersonId(any())).thenReturn(new ArrayList<>());
		 when(vehicleService.getVehiclebyPersonId(any())).thenReturn(vehicleList);
		 when(daskService.getDaskByPersonId(any())).thenReturn(new ArrayList<>());
		  MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/person/PersonActionPage/{id}", 1L);
		   MvcResult result=MockMvcBuilders.standaloneSetup(personController)
	                .build()
	                .perform(requestBuilder)
	                .andExpect(MockMvcResultMatchers.status().isOk())
	                .andExpect(MockMvcResultMatchers.model().size(5))
	                .andExpect(MockMvcResultMatchers.model().attributeExists("persons", "lifes", "vehicles", "dasks","id"))
	                .andExpect(MockMvcResultMatchers.view().name("PersonActionPage"))
	                .andExpect(MockMvcResultMatchers.forwardedUrl("PersonActionPage")).andReturn();
		   	Map<String,Object> map= result.getModelAndView().getModel();
		    Person persons=(Person)map.get("persons");
	        Assertions.assertThat(persons).isEqualTo(person);
	        List<Life> lifes=(List<Life>)map.get("lifes");
	        Assertions.assertThat(lifes).isEmpty();
	        List<Vehicle> vehicles=(List<Vehicle>)map.get("vehicles");
	        Assertions.assertThat(vehicles).isEqualTo(vehicleList);
	        List<Dask> dasks=(List<Dask>)map.get("dasks");
	        Assertions.assertThat(dasks).isEmpty();
	        
	}
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
