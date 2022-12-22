package com.springacentesbmdeneme.API;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
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

import com.springacentesbmdeneme.Repository.abstracts.DaskJpa;
import com.springacentesbmdeneme.Repository.abstracts.PersonJpa;
import com.springacentesbmdeneme.Service.abstracts.AreaService;
import com.springacentesbmdeneme.Service.abstracts.BasePriceService;
import com.springacentesbmdeneme.Service.abstracts.BuildAgeService;
import com.springacentesbmdeneme.Service.abstracts.BuildTypeService;
import com.springacentesbmdeneme.Service.abstracts.CityService;
import com.springacentesbmdeneme.Service.abstracts.NumberofFloorsService;
import com.springacentesbmdeneme.Service.abstracts.PersonService;
import com.springacentesbmdeneme.Service.abstracts.ProposalService;
import com.springacentesbmdeneme.Service.concretes.DaskManager;
import com.springacentesbmdeneme.entites.Area;
import com.springacentesbmdeneme.entites.BasePrice;
import com.springacentesbmdeneme.entites.BuildAge;
import com.springacentesbmdeneme.entites.BuildType;
import com.springacentesbmdeneme.entites.City;
import com.springacentesbmdeneme.entites.Dask;
import com.springacentesbmdeneme.entites.NumberofFloors;
import com.springacentesbmdeneme.entites.Person;
import com.springacentesbmdeneme.entites.Proposal;
import com.springacentesbmdeneme.entites.Vehicle;

@WebMvcTest(DaskController.class)
@AutoConfigureMockMvc(addFilters = false)
public class DaskControllerTest {
	@Autowired
	private DaskController daskController;
	@MockBean
	private DaskManager daskService;
	@MockBean 
	private PersonService personService;
	@MockBean
	private ProposalService proposalService;
	@MockBean
	private BuildTypeService buildTypeService;
	@MockBean
	private CityService cityService;
	@MockBean
	private AreaService areaService;
	@MockBean
	private BasePriceService basePriceService;
	@MockBean
	private BuildAgeService buildAgeService;
	@MockBean
	private NumberofFloorsService numberofFloorsService;
	@Autowired
	private MockMvc mockMvc;
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
					.id(1L)
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
			
			buildAge=new BuildAge().builder()
					.id(1L)
					.max(10)
					.min(1)
					.title("buildAge")
					.price_multiplier(1.0)
					.build();
			
			
			 area=new Area().builder()
					 .id(1L)
					 .max(10)
					 .min(1)
					 .price_multiplier(1)
					 .title("area")
					 .build();
			 
			
			 basePrice=new BasePrice().builder().
					type("Vehicle")
					.initialprice(1800.00).build();
			 city=new City().builder()
					 .id(1L)
					 .name("city")
					 .price_multiplier(1)
					 .build();
			 
			 numberFloor=new NumberofFloors().builder()
					 .id(1L)
					 .max(10)
					 .min(1)
					 .price_multiplier(1)
					 .title("floor")
					 .build();
					
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
    void daskListController_Test()throws Exception{

    	ArrayList<Dask> daskList=new ArrayList<Dask>(){{add(dask);}}; 
        when(daskService.getAll()).thenReturn(daskList);
       
        assertEquals("DaskList", daskController.daskList(new ConcurrentModel("dasks",dask)));
       
    }
	 @Test
		void daskControllerDaskCreate_Test() throws Exception{
			MockMvcBuilders.standaloneSetup(daskController)
			.build()
			.perform(post("/dask/add_dask")
			.param("{adress}","adress")
					.param("{person.id}", person.getId().toString())
					.param("{city.id}", city.getId().toString())
					.param("{buildtype.id}", buildType.getId().toString())
					.param("{floor.id}", numberFloor.getId().toString())
					.param("{area.id}", area.getId().toString())
					.param("{buildage.id}", buildAge.getId().toString()))
			//
			
			.andExpect(MockMvcResultMatchers.view().name("redirect:/person/personList"))
			.andExpect(MockMvcResultMatchers.model().size(1))
			.andExpect(MockMvcResultMatchers.redirectedUrl("/person/personList"));;
			;
	 }
	 @Test
	    void existDaskInsuranceController_Test()throws Exception{
		 ArrayList<Proposal> daskProposalList=new ArrayList<Proposal>(); 
	        when(daskService.getDaskById(any())).thenReturn(dask);
	       dask.setDaskproposal(daskProposalList);
	       MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/dask/DaskInsuranceExistPage/{id}", 1L);
		   MvcResult result=MockMvcBuilders.standaloneSetup(daskController)
		   .build()
		   .perform(requestBuilder)
		   .andExpect(MockMvcResultMatchers.status().isOk())
		   .andExpect(MockMvcResultMatchers.model().size(3))
		   .andExpect(MockMvcResultMatchers.model().attributeExists("header","dasks", "proposals"))
		   .andExpect(MockMvcResultMatchers.view().name("DaskExistPage"))
		   .andExpect(MockMvcResultMatchers.forwardedUrl("DaskExistPage")).andReturn();
		   Map<String,Object> map= result.getModelAndView().getModel();
		   Dask dasks=(Dask)map.get("dasks");
		   List<Proposal> proposals=(List<Proposal>)map.get("proposals");
	       Assertions.assertThat(dasks).isEqualTo(dask);
	       Assertions.assertThat(proposals).isEqualTo(daskProposalList);
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
		 ArrayList<Proposal> daskProposalList=new ArrayList<Proposal>() {{
			 add(proposal);
		 }
		 };
		 when(daskService.getDaskById(any())).thenReturn(dask);
	        //when(vehicle.getProposal()).thenReturn(vehicleProposalList);
	       dask.setDaskproposal(daskProposalList);
	       MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/dask/Detail/{id}", 1L);
	       MvcResult result=MockMvcBuilders.standaloneSetup(daskController)
		   .build()
		   .perform(requestBuilder)
		   .andExpect(MockMvcResultMatchers.status().isOk())
		   .andExpect(MockMvcResultMatchers.model().size(3))
		   .andExpect(MockMvcResultMatchers.model().attributeExists("header","dasks", "proposals"))
		   .andExpect(MockMvcResultMatchers.view().name("DaskExistPage"))
		   .andExpect(MockMvcResultMatchers.forwardedUrl("DaskExistPage")).andReturn();   
		   Map<String,Object> map= result.getModelAndView().getModel();
		   Dask dasks=(Dask)map.get("dasks");
		   List<Proposal> proposals=(List<Proposal>)map.get("proposals");
	       Assertions.assertThat(dasks).isEqualTo(dask);
	       Assertions.assertThat(proposals).isEqualTo(daskProposalList);
	    }
	 
	 @Test
	    void detailController_Test2()throws Exception{
		 Proposal proposal=new Proposal().builder()
				 .id(1L)
				 .price(100.00)
				 .accepted_date(new Date(100,02,22))
				 .status("waiting")
				 .build();
		 ArrayList<Proposal> daskProposalList=new ArrayList<Proposal>() {{
			 add(proposal);
		 }
		 };
		 when(daskService.getDaskById(any())).thenReturn(dask);
		 dask.setDaskproposal(daskProposalList);
	       MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/dask/Detail/{id}", 1L);
		   MvcResult result= MockMvcBuilders.standaloneSetup(daskController)
		   .build()
		   .perform(requestBuilder)
		   .andExpect(MockMvcResultMatchers.status().isOk())
		   .andExpect(MockMvcResultMatchers.model().size(3))
		   .andExpect(MockMvcResultMatchers.model().attributeExists("header","dasks", "proposals"))
		   .andExpect(MockMvcResultMatchers.view().name("DaskExistPage"))
		   .andExpect(MockMvcResultMatchers.forwardedUrl("DaskExistPage")).andReturn();   
		   Map<String,Object> map= result.getModelAndView().getModel();
		   Dask dasks=(Dask)map.get("dasks");
		   List<Proposal> proposals=(List<Proposal>)map.get("proposals");
	       Assertions.assertThat(dasks).isEqualTo(dask);
	       Assertions.assertThat(proposals).isEqualTo(daskProposalList);
	    }
	 @Test
		void daskEditMethod_Test() throws Exception{
			
		 Dask dask2=new Dask().builder()
					.adress("adres2")
					.area(area)
					.build_age(buildAge)
					.buildtype(buildType)
					.city(city)
					.person(person)
					.floor(numberFloor)
					.daskproposal(new ArrayList<>())
					.build();
				
		 when(daskService.getPersonIdByDaskId(any())).thenReturn(person.getId());
		 when(daskService.getDaskById(any())).thenReturn(dask);
	        MockMvcBuilders.standaloneSetup(daskController)
	        		.build()
	        		.perform(post("/dask/_editjob/{id}",1L)
	        				.param("{adress}",dask2.getAdress())
	        				.param("{person.id}", person.getId().toString())
	    					.param("{city.id}", city.getId().toString())
	    					.param("{buildtype.id}", buildType.getId().toString())
	    					.param("{floor.id}", numberFloor.getId().toString())
	    					.param("{area.id}", area.getId().toString())
	    					.param("{buildage.id}", buildAge.getId().toString()))
	       
	        .andExpect(MockMvcResultMatchers.view().name("redirect:/person/PersonActionPage/1"))
			.andExpect(MockMvcResultMatchers.model().size(1))
			.andExpect(MockMvcResultMatchers.redirectedUrl("/person/PersonActionPage/1"));;
		}
	 @Test 
		void DaskControllerDeleteMethod_Test()throws Exception{
		 	
		 	
		 	when(daskService.getPersonIdByDaskId(any())).thenReturn(person.getId());
	        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/dask/_delete/{id}", 1L);
	        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(daskController)
	                .build()
	                .perform(requestBuilder)
	                .andExpect(MockMvcResultMatchers.view().name("redirect:/person/PersonActionPage/1"))
	        		.andExpect(MockMvcResultMatchers.redirectedUrl("/person/PersonActionPage/1"));;
	               
	                ;
	        Assertions.assertThat(daskService.getDaskById(dask.getId())).isNull();
		}
	 @Test 
		void daskInsuranceSaveController_Test()throws Exception{
		 
		 
		 when(daskService.getDaskById(any())).thenReturn(dask);
		 when(proposalService.getProposalsIdByDaskId(dask.getId())).thenReturn(new ArrayList<Long>() {{add(1L);}});
		 MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/dask/InsuranceCreate/{id}", 1L);
		   MockMvcBuilders.standaloneSetup(daskController)
		   .build()
		   .perform(requestBuilder)

		   	.andExpect(MockMvcResultMatchers.view().name("redirect:/dask/Detail/1"))
		   	.andExpect(MockMvcResultMatchers.model().size(0))
		   	.andExpect(MockMvcResultMatchers.redirectedUrl("/dask/Detail/1"));;
		 
	 }
}
