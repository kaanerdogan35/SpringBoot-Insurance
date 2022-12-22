package com.springacentesbmdeneme.API;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ConcurrentModel;

import com.springacentesbmdeneme.Service.abstracts.DaskService;
import com.springacentesbmdeneme.Service.abstracts.LifeService;
import com.springacentesbmdeneme.Service.abstracts.PersonService;
import com.springacentesbmdeneme.Service.abstracts.ProposalService;
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

@WebMvcTest(VehicleController.class)
@AutoConfigureMockMvc(addFilters = false)
public class VehicleControllerTest {
	@Autowired
	private VehicleController vehicleController;
	@MockBean
	private VehicleService vehicleService;
	@MockBean 
	private PersonService personService;
	@MockBean
	private ProposalService proposalService;
	@MockBean
	private VehicleBrandService vehicleBrandService;
	@MockBean
	private VehicleTypeService vehicleTypeService;
	@Autowired
	private MockMvc mockMvc;
	
	@Mock
	private Vehicle vehicle;
	@MockBean
	private Person person;
	@MockBean
	private VehicleBrand vehicleBrand;
	@MockBean
	private VehicleType vehicleType;
	@BeforeEach
	void init() {
		 person= new Person().builder()
				.first_name("Kaan")
				.last_name("Erdoğan")
				.email("test@Mail.com")
				.tc(484216521L)
				.id(1L)
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
	    void vehicleListController_Test()throws Exception{

	    	ArrayList<Vehicle> vehicleList=new ArrayList<Vehicle>(){{add(vehicle);}}; 
	        when(vehicleService.getAll()).thenReturn(vehicleList);
	       
	        assertEquals("VehicleList", vehicleController.VehicleList(new ConcurrentModel("vehicles",vehicle)));
	       
	    }
	 @Test
	 void vehicleListController_Test2()throws Exception{
		 List<Vehicle> vehicleList=new ArrayList<Vehicle>(){{add(vehicle);}};
		 when(vehicleService.getAll()).thenReturn(vehicleList);
		 MvcResult result=MockMvcBuilders.standaloneSetup(vehicleController)
			.build()
			.perform(get("/vehicle/vehicleList"))
					.andExpect(MockMvcResultMatchers.model().attributeExists("vehicles")).andReturn();
		 	Map<String,Object> map= result.getModelAndView().getModel();
		   List<Vehicle> vehicles=(List<Vehicle>)map.get("vehicles");
	       Assertions.assertThat(vehicles).isEqualTo(vehicleList);
		 
	 }
	 @Test
		void vehicleControllerVehicleCreate_Test() throws Exception{
			//MockHttpServletRequestBuilder requestBuilder=MockMvcRequestBuilders.post("/person/person_create");
			when(vehicleService.getVehicleById(any())).thenReturn(vehicle);
			
			
			MockMvcBuilders.standaloneSetup(vehicleController)
			.build()
			.perform(post("/vehicle/add_vehicle")
			.param("{chassis_number}","1000005555")
					.param("{year}", "1999")
					.param("{plate}","00test00" )
					.param("{person.id}", person.getId().toString())
					.param("{vehicleBrand.id}", vehicleBrand.getId().toString())
					.param("{vehicleType.id}", vehicleType.getId().toString()))
			//
			.andExpect(MockMvcResultMatchers.view().name("redirect:/person/personList"))
			.andExpect(MockMvcResultMatchers.model().size(1))
			.andExpect(MockMvcResultMatchers.redirectedUrl("/person/personList"));;
			;
	 }
	 @Test
	    void existVehicleInsuranceController_Test()throws Exception{
		 ArrayList<Proposal> vehicleProposalList=new ArrayList<Proposal>(); 
	        when(vehicleService.getVehicleById(any())).thenReturn(vehicle);
	        //when(vehicle.getProposal()).thenReturn(vehicleProposalList);
	       vehicle.setProposal(vehicleProposalList);
	       MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/vehicle/VehicleExistInsurance/{id}", 1L);
		   MvcResult result= MockMvcBuilders.standaloneSetup(vehicleController)
		   .build()
		   .perform(requestBuilder)
           .andExpect(MockMvcResultMatchers.status().isOk())
           .andExpect(MockMvcResultMatchers.model().size(3))
           .andExpect(MockMvcResultMatchers.model().attributeExists("header","vehicles", "proposals"))
           .andExpect(MockMvcResultMatchers.view().name("VehicleInsuranceExistPage"))
           .andExpect(MockMvcResultMatchers.forwardedUrl("VehicleInsuranceExistPage")).andReturn();
		   Map<String,Object> map= result.getModelAndView().getModel();
		   Vehicle vehicles=(Vehicle)map.get("vehicles");
	       List<Proposal> proposals=(List<Proposal>)map.get("proposals");
	       Assertions.assertThat(proposals).isEqualTo(vehicleProposalList);
	       Assertions.assertThat(vehicles).isEqualTo(vehicle);
	    }
	
	 @Test
	    void detailController_Test()throws Exception{
		 Proposal proposal=new Proposal().builder()
				 .id(1L)
				 .price(100.00)
				 .accepted_date(new Date(100,02,22))
				 .end_date(new Date(101,02,22))
				 .status("waiting")
				 .build();
		 ArrayList<Proposal> vehicleProposalList=new ArrayList<Proposal>() {{
			 add(proposal);
		 }
		 };
		 when(vehicleService.getVehicleById(any())).thenReturn(vehicle);
	        //when(vehicle.getProposal()).thenReturn(vehicleProposalList);
	       vehicle.setProposal(vehicleProposalList);
	       MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/vehicle/VehicleExistInsurance/{id}", 1L);
		   MvcResult result=MockMvcBuilders.standaloneSetup(vehicleController)
		   .build()
		   .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.model().size(3))
        .andExpect(MockMvcResultMatchers.model().attributeExists("header","vehicles", "proposals"))
        .andExpect(MockMvcResultMatchers.view().name("VehicleInsuranceExistPage"))
        .andExpect(MockMvcResultMatchers.forwardedUrl("VehicleInsuranceExistPage")).andReturn();
		 Map<String,Object> map=result.getModelAndView().getModel();
		 List<Proposal> proposals=(List<Proposal>)map.get("proposals");
		 Vehicle vehicles=(Vehicle)map.get("vehicles");
		 Assertions.assertThat(vehicles).isEqualTo(vehicle);
		 Assertions.assertThat(proposals).isEqualTo(vehicleProposalList);
	       
	    }
	 @Test
	    void detailController_Test2()throws Exception{
		 Proposal proposal=new Proposal().builder()
				 .id(1L)
				 .price(100.00)
				 .accepted_date(new Date(100,02,22))
				 .status("waiting")
				 .build();
		 ArrayList<Proposal> vehicleProposalList=new ArrayList<Proposal>() {{
			 add(proposal);
		 }
		 };
		 when(vehicleService.getVehicleById(any())).thenReturn(vehicle);
	        //when(vehicle.getProposal()).thenReturn(vehicleProposalList);
	       vehicle.setProposal(vehicleProposalList);
	       MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/vehicle/VehicleExistInsurance/{id}", 1L);
		   MvcResult result=MockMvcBuilders.standaloneSetup(vehicleController)
		   .build()
		   .perform(requestBuilder)
		   	.andExpect(MockMvcResultMatchers.status().isOk())
		   	.andExpect(MockMvcResultMatchers.model().size(3))
		   	.andExpect(MockMvcResultMatchers.model().attributeExists("header","vehicles", "proposals"))
		   	.andExpect(MockMvcResultMatchers.view().name("VehicleInsuranceExistPage"))
		   	.andExpect(MockMvcResultMatchers.forwardedUrl("VehicleInsuranceExistPage")).andReturn();  
		   Map<String,Object> map=result.getModelAndView().getModel();
			List<Proposal> proposals=(List<Proposal>)map.get("proposals");
			Vehicle vehicles=(Vehicle)map.get("vehicles");
			Assertions.assertThat(vehicles).isEqualTo(vehicle);
			Assertions.assertThat(proposals).isEqualTo(vehicleProposalList);
	    }
	 
	 @Test
		void vehicleEditMethod_Test() throws Exception{
			
		 Vehicle vehicle2=new Vehicle().builder()
					.chassis_number("15315135153")
					.plate("02Test202")
					.modelYear(2022)
					.person(person)
					.vehicleBrand(vehicleBrand)
					.vehicleType(vehicleType)
					.build();
		 when(vehicleService.getPersonIdByVehicleId(any())).thenReturn(person.getId());
		 when(vehicleService.getVehicleById(any())).thenReturn(vehicle);
	        MockMvcBuilders.standaloneSetup(vehicleController)
	        		.build()
	        		.perform(post("/vehicle/_editjob/{id}",1L)
	        				.param("{chassis_number}",vehicle2.getChassis_number())
	        						.param("{plate}", vehicle2.getPlate())
	        						.param("{year}","2022")
	        						.param("{person.id}", person.getId().toString())
	        						.param("{vehicleBrand.id}", vehicleBrand.getId().toString())
	        						.param("{vehicleType.id}", vehicleType.getId().toString()))
	       
	        .andExpect(MockMvcResultMatchers.view().name("redirect:/person/PersonActionPage/1"))
			.andExpect(MockMvcResultMatchers.model().size(1))
			.andExpect(MockMvcResultMatchers.redirectedUrl("/person/PersonActionPage/1"));;
		}
	 @Test 
		void VehicleControllerDeleteMethod_Test()throws Exception{
		 	
		 	
		 	when(vehicleService.getPersonIdByVehicleId(any())).thenReturn(person.getId());
	        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/vehicle/_delete/{id}", vehicle.getId());
	        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(vehicleController)
	                .build()
	                .perform(requestBuilder)
	                .andExpect(MockMvcResultMatchers.view().name("redirect:/person/PersonActionPage/1"))
	        		.andExpect(MockMvcResultMatchers.redirectedUrl("/person/PersonActionPage/1"));;
	               
	                ;
	        Assertions.assertThat(vehicleService.getVehicleById(vehicle.getId())).isNull();
		}
	 @Test 
		void vehicleInsuranceSaveController_Test()throws Exception{
		 
		 
		 when(vehicleService.getVehicleById(any())).thenReturn(vehicle);
		 when(proposalService.getProposalsIdByVehicleId(vehicle.getId())).thenReturn(new ArrayList<Long>() {{add(1L);}});
		 MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/vehicle/InsuranceCreate/{id}", 1L);
		   MockMvcBuilders.standaloneSetup(vehicleController)
		   .build()
		   .perform(requestBuilder)
   
		   	.andExpect(MockMvcResultMatchers.view().name("redirect:/vehicle/Detail/1"))
		   	.andExpect(MockMvcResultMatchers.model().size(0))
		   	.andExpect(MockMvcResultMatchers.redirectedUrl("/vehicle/Detail/1"));;
		 
	 }
}
