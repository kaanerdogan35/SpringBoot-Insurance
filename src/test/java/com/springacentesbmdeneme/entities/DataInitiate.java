package com.springacentesbmdeneme.entities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.text.DateFormatter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springacentesbmdeneme.Service.abstracts.AreaService;
import com.springacentesbmdeneme.Service.abstracts.BasePriceService;
import com.springacentesbmdeneme.Service.abstracts.BuildAgeService;
import com.springacentesbmdeneme.Service.abstracts.BuildTypeService;
import com.springacentesbmdeneme.Service.abstracts.CareerService;
import com.springacentesbmdeneme.Service.abstracts.CityService;
import com.springacentesbmdeneme.Service.abstracts.DiseaseService;
import com.springacentesbmdeneme.Service.abstracts.NumberofFloorsService;
import com.springacentesbmdeneme.Service.abstracts.PersonAgeService;
import com.springacentesbmdeneme.Service.abstracts.PersonService;
import com.springacentesbmdeneme.Service.abstracts.VehicleAgeRangeService;
import com.springacentesbmdeneme.Service.abstracts.VehicleBrandService;
import com.springacentesbmdeneme.Service.abstracts.VehicleTypeService;
import com.springacentesbmdeneme.entites.Area;
import com.springacentesbmdeneme.entites.BasePrice;
import com.springacentesbmdeneme.entites.BuildAge;
import com.springacentesbmdeneme.entites.BuildType;
import com.springacentesbmdeneme.entites.Career;
import com.springacentesbmdeneme.entites.City;
import com.springacentesbmdeneme.entites.Disease;
import com.springacentesbmdeneme.entites.NumberofFloors;
import com.springacentesbmdeneme.entites.Person;
import com.springacentesbmdeneme.entites.PersonAge;
import com.springacentesbmdeneme.entites.VehicleAgeRange;
import com.springacentesbmdeneme.entites.VehicleBrand;
import com.springacentesbmdeneme.entites.VehicleType;
/*
import com.springacentasbm.entities.CarBrand;
import com.springacentasbm.entities.CarType;
import com.springacentasbm.entities.Career;
import com.springacentasbm.entities.City;
import com.springacentasbm.entities.Disease;
import com.springacentasbm.services.abstracts.BuildTypeService;
import com.springacentasbm.services.abstracts.CarBrandService;
import com.springacentasbm.services.abstracts.CarTypeService;
import com.springacentasbm.services.abstracts.CareerService;
import com.springacentasbm.services.abstracts.CityService;
import com.springacentasbm.services.abstracts.DiseaseService;
*/
@SpringBootTest
class DataInitiate {
	 @Autowired
	 private VehicleBrandService vehicleBrandService;
	 @Autowired
	 private CityService cityService;
	 @Autowired
	 private VehicleTypeService vehicleTypeService;
	 @Autowired
	 private VehicleAgeRangeService vehicleAgeRangeService;
	 @Autowired
	 private PersonService personService;
	 @Autowired
	 private BuildTypeService buildTypeService;
	 @Autowired
	 private CareerService  careerService;
	 @Autowired
	 private DiseaseService diseaseService;
	 @Autowired
	 private NumberofFloorsService numberofFloorsService;
	 @Autowired
	 private AreaService areaService;
	 @Autowired
	 private BuildAgeService buildAgeService;
	 @Autowired
	 private PersonAgeService personAgeService;
	 @Autowired
	 private BasePriceService basePriceService;
	 @Test
	 void dataInitiate() {
		 String tc="49405587204";
		 
		 Date date=new Date(100,02,22);
		 Person person=Person.builder()
				 .birth_date(date)
				 .first_name("Kaan")
				 .last_name("Erdoğan")
				 .email("kaan.erdogan@bahcesehir.edu.tr")
				 .tc(Long.valueOf(tc))
				 .build();
		 personService.save(person);
		 ArrayList<VehicleBrand> vehicleBrands = new ArrayList<>() {
	            {
	                add(VehicleBrand.builder().name("BMW").price_multiplier(1.8).build());
	                add(VehicleBrand.builder().name("Audi").price_multiplier(1.7).build());
	                add(VehicleBrand.builder().name("Volkswagen").price_multiplier(1.5).build());
	                add(VehicleBrand.builder().name("Mercedes-Benz").price_multiplier(1.9).build());
	                add(VehicleBrand.builder().name("Opel").price_multiplier(1.4).build());
	                add(VehicleBrand.builder().name("Peugout").price_multiplier(1.3).build());
	                add(VehicleBrand.builder().name("Renault").price_multiplier(1.2).build());
	                add(VehicleBrand.builder().name("Dacia").price_multiplier(1.1).build());
	                add(VehicleBrand.builder().name("Isuzu").price_multiplier(1.7).build());
	                
	            }
	        };

	        for (VehicleBrand brands : vehicleBrands) {
	            vehicleBrandService.save(brands);
	        }
	        
	        ArrayList<VehicleType> VehicleTypes = new ArrayList<>() {
	            {
	                add(VehicleType.builder().name("Sedan").price_multiplier(1.0).build());
	                add(VehicleType.builder().name("Hatchback").price_multiplier(1.1).build());
	                add(VehicleType.builder().name("SUV").price_multiplier(1.3).build());
	                add(VehicleType.builder().name("Coupe").price_multiplier(1.6).build());
	                add(VehicleType.builder().name("Kamyonet").price_multiplier(2.1).build());
	                add(VehicleType.builder().name("Otobüs").price_multiplier(2.4).build());
	                
	            }
	        };

	        for (VehicleType Vehicletype : VehicleTypes) {
	            vehicleTypeService.save(Vehicletype);
	            
	           
		        };
		    ArrayList<BasePrice> BasePrices = new ArrayList<>() {
		            {
		                add(BasePrice.builder().type("Vehicle").initialprice(1800.00).build());
		                add(BasePrice.builder().type("Dask").initialprice(90.00).build());
		                add(BasePrice.builder().type("Life").initialprice(40.00).build());
		            }

		       
		        
	        }; 
		    for (BasePrice BasePrice : BasePrices) {
		            basePriceService.save(BasePrice);
		            }
	        ArrayList<VehicleAgeRange> vehicleAgeRanges = new ArrayList<>() {
	            {
	                add(VehicleAgeRange.builder().maxAge(122).minAge(50).price_multiplier(4.0).build());
	                add(VehicleAgeRange.builder().maxAge(49).minAge(40).price_multiplier(3.2).build());
	                add(VehicleAgeRange.builder().maxAge(39).minAge(30).price_multiplier(2.8).build());
	                add(VehicleAgeRange.builder().maxAge(29).minAge(20).price_multiplier(2.3).build());
	                add(VehicleAgeRange.builder().maxAge(19).minAge(15).price_multiplier(1.8).build());
	                add(VehicleAgeRange.builder().maxAge(14).minAge(10).price_multiplier(1.5).build());
	                add(VehicleAgeRange.builder().maxAge(9).minAge(6).price_multiplier(1.3).build());
	                add(VehicleAgeRange.builder().maxAge(5).minAge(3).price_multiplier(1.1).build());
	                add(VehicleAgeRange.builder().maxAge(3).minAge(0).price_multiplier(0.8).build());
	                
	                
	            }
	        };

	        for (VehicleAgeRange vehicleAgeRange : vehicleAgeRanges) {
	        	vehicleAgeRangeService.save(vehicleAgeRange);
	        }
	        
	        
	        
	       
	        ArrayList<Disease> diseases = new ArrayList<>() {
	            {
	            	
	                add(Disease.builder().name("Şeker").price_multiplier(1.7).build());
	                add(Disease.builder().name("Diyaliz").price_multiplier(1.5).build());
	                add(Disease.builder().name("Siroz").price_multiplier(2.0).build());
	             
	            }
	        };
	        for (Disease disease : diseases) {
	            diseaseService.save(disease);
	        }
	        
	        ArrayList<Career> careers = new ArrayList<>() {
	            {
	            	
	                add(Career.builder().name("Gemi Kaptanı").price_multiplier(1.7).build());
	                add(Career.builder().name("Teknisyen").price_multiplier(1.5).build());
	                add(Career.builder().name("İnşaat İşçisi").price_multiplier(2.0).build());
	             
	            }
	        };
	        for (Career career : careers) {
	            careerService.save(career);
	        }
	        
	        ArrayList<BuildType> types = new ArrayList<>() {
	            {
	            	
	                add(BuildType.builder().name("Betonarme").price_multiplier(1.1).build());
	                add(BuildType.builder().name("Diğer").price_multiplier(1.35).build());
	             
	            }
	        };
	        for (BuildType type : types) {
	            buildTypeService.save(type);
	        }
	        
	        ArrayList<City> cities = new ArrayList<>() {
	            {
	                add(City.builder().name("Adana").price_multiplier(1.3).build());
	                add(City.builder().name("Adıyaman").price_multiplier(1.1).build());
	                add(City.builder().name("Afyonkarahisar").price_multiplier(1.1).build());
	                add(City.builder().name("Ağrı").price_multiplier(1.4).build());
	                add(City.builder().name("Aksaray").price_multiplier(1.2).build());
	                add(City.builder().name("Amasya").price_multiplier(1.0).build());
	                add(City.builder().name("Ankara").price_multiplier(1.5).build());
	                add(City.builder().name("Antalya").price_multiplier(1.2).build());
	                add(City.builder().name("Ardahan").price_multiplier(1.1).build());
	                add(City.builder().name("Artvin").price_multiplier(1.4).build());
	                add(City.builder().name("Aydın").price_multiplier(1.3).build());
	                add(City.builder().name("Balıkesir").price_multiplier(1.6).build());
	                add(City.builder().name("Bartın").price_multiplier(1.5).build());
	                add(City.builder().name("Batman").price_multiplier(1.0).build());
	                add(City.builder().name("Bayburt").price_multiplier(0.9).build());
	                add(City.builder().name("Bilecik").price_multiplier(1.4).build());
	                add(City.builder().name("Bingöl").price_multiplier(1.6).build());
	                add(City.builder().name("Bitlis").price_multiplier(1.5).build());
	                add(City.builder().name("Bolu").price_multiplier(1.8).build());
	                add(City.builder().name("Burdur").price_multiplier(1.2).build());
	                add(City.builder().name("Bursa").price_multiplier(1.4).build());
	                add(City.builder().name("Çanakkale").price_multiplier(1.8).build());
	                add(City.builder().name("Çankırı").price_multiplier(1.4).build());
	                add(City.builder().name("Çorum").price_multiplier(1.1).build());
	                add(City.builder().name("Denizli").price_multiplier(1.0).build());
	                add(City.builder().name("Diyarbakır").price_multiplier(0.9).build());
	                add(City.builder().name("Düzce").price_multiplier(1.9).build());
	                add(City.builder().name("Edirne").price_multiplier(1.2).build());
	                add(City.builder().name("Elazığ").price_multiplier(1.8).build());
	                add(City.builder().name("Erzincan").price_multiplier(1.7).build());
	                add(City.builder().name("Erzurum").price_multiplier(1.1).build());
	                add(City.builder().name("Eskişehir").price_multiplier(1.0).build());
	                add(City.builder().name("Gaziantep").price_multiplier(0.9).build());
	                add(City.builder().name("Giresun").price_multiplier(1.6).build());
	                add(City.builder().name("Gümüşhane").price_multiplier(1.5).build());
	                add(City.builder().name("Hakkari").price_multiplier(1.4).build());
	                add(City.builder().name("Hatay").price_multiplier(1.2).build());
	                add(City.builder().name("Iğdır").price_multiplier(1.5).build());
	                add(City.builder().name("Isparta").price_multiplier(1.4).build());
	                add(City.builder().name("İstanbul").price_multiplier(2.3).build());
	                add(City.builder().name("İzmir").price_multiplier(2.0).build());
	                add(City.builder().name("Kahramanmaraş").price_multiplier(1.4).build());
	                add(City.builder().name("Karabük").price_multiplier(1.6).build());
	                add(City.builder().name("Karaman").price_multiplier(1.2).build());
	                add(City.builder().name("Kars").price_multiplier(1.1).build());
	                add(City.builder().name("Kastamonu").price_multiplier(1.0).build());
	                add(City.builder().name("Kayseri").price_multiplier(1.5).build());
	                add(City.builder().name("Kırıkkale").price_multiplier(1.4).build());
	                add(City.builder().name("Kırklareli").price_multiplier(1.1).build());
	                add(City.builder().name("Kırşehir").price_multiplier(1.3).build());
	                add(City.builder().name("Kilis").price_multiplier(1.5).build());
	                add(City.builder().name("Kocaeli").price_multiplier(2.4).build());
	                add(City.builder().name("Konya").price_multiplier(1.9).build());
	                add(City.builder().name("Kütahya").price_multiplier(1.7).build());
	                add(City.builder().name("Malatya").price_multiplier(1.1).build());
	                add(City.builder().name("Manisa").price_multiplier(1.8).build());
	                add(City.builder().name("Mardin").price_multiplier(1.4).build());
	                add(City.builder().name("Mersin").price_multiplier(1.1).build());
	                add(City.builder().name("Muğla").price_multiplier(1.8).build());
	                add(City.builder().name("Muş").price_multiplier(1.4).build());
	                add(City.builder().name("Nevşehir").price_multiplier(1.2).build());
	                add(City.builder().name("Niğde").price_multiplier(1.5).build());
	                add(City.builder().name("Ordu").price_multiplier(1.8).build());
	                add(City.builder().name("Osmaniye").price_multiplier(1.4).build());
	                add(City.builder().name("Rize").price_multiplier(1.9).build());
	                add(City.builder().name("Sakarya").price_multiplier(1.4).build());
	                add(City.builder().name("Samsun").price_multiplier(1.5).build());
	                add(City.builder().name("Siirt").price_multiplier(1.1).build());
	                add(City.builder().name("Sinop").price_multiplier(1.0).build());
	                add(City.builder().name("Sivas").price_multiplier(1.0).build());
	                add(City.builder().name("Şanlıurfa").price_multiplier(1.3).build());
	                add(City.builder().name("Şırnak").price_multiplier(1.0).build());
	                add(City.builder().name("Tekirdağ").price_multiplier(1.4).build());
	                add(City.builder().name("Tokat").price_multiplier(1.3).build());
	                add(City.builder().name("Trabzon").price_multiplier(1.6).build());
	                add(City.builder().name("Tunceli").price_multiplier(1.3).build());
	                add(City.builder().name("Uşak").price_multiplier(1.2).build());
	                add(City.builder().name("Van").price_multiplier(1.8).build());
	                add(City.builder().name("Yalova").price_multiplier(2.5).build());
	                add(City.builder().name("Yozgat").price_multiplier(1.3).build());
	                add(City.builder().name("Zonguldak").price_multiplier(1.7).build());
	            	} 
	            };

	            for (City city : cities) {
	                cityService.save(city);
	            }
	            ArrayList<NumberofFloors> floors=new ArrayList<>() {
	            	{
	            	add(NumberofFloors.builder().title("Between 01-03").max(3).min(0).price_multiplier(1.3).build());
	            	add(NumberofFloors.builder().title("Between 04-07").max(7).min(4).price_multiplier(1.1).build());
	            	add(NumberofFloors.builder().title("Between 08-18").max(18).min(8).price_multiplier(0.9).build());
	            	add(NumberofFloors.builder().title("More Than 18").max(100).min(19).price_multiplier(0.7).build());
	            	
	            	}
	 			};
	 			for (NumberofFloors floor :floors) {
	 				numberofFloorsService.save(floor);
	 			}
	 			ArrayList<Area> areas=new ArrayList<>() {
	 				{
	 					add(Area.builder().title("0-50 Squearemeters").max(50).min(0).price_multiplier(0.7).build());
	 					add(Area.builder().title("51-100 Squearemeters").max(100).min(51).price_multiplier(0.9).build());
	 					add(Area.builder().title("101-150 Squearemeters").max(150).min(101).price_multiplier(1.2).build());
	 					add(Area.builder().title("151-200 Squearemeters").max(200).min(151).price_multiplier(1.4).build());
	 					add(Area.builder().title("201-250 Squearemeters").max(250).min(201).price_multiplier(1.8).build());
	 					add(Area.builder().title("251-500 Squearemeters").max(500).min(251).price_multiplier(2.5).build());
	 					add(Area.builder().title("501-1000 Squearemeters").max(1000).min(501).price_multiplier(3.8).build());
	 				}
	 			};
	 			for (Area area :areas) {
	 				areaService.save(area);
	 			}
	 			
	 			ArrayList<BuildAge> ages=new ArrayList<>() {
	 				{
	 					add(BuildAge.builder().title("0-3 Years").max(3).min(0).price_multiplier(0.7).build());
	 					add(BuildAge.builder().title("4-7 Years").max(7).min(4).price_multiplier(0.9).build());
	 					add(BuildAge.builder().title("7-15 Years").max(15).min(7).price_multiplier(1.2).build());
	 					add(BuildAge.builder().title("15-30 Years").max(30).min(15).price_multiplier(1.4).build());
	 					add(BuildAge.builder().title("31-50 Years").max(50).min(31).price_multiplier(1.8).build());
	 					add(BuildAge.builder().title("51-100 Years").max(100).min(51).price_multiplier(2.5).build());
	 					add(BuildAge.builder().title("More Than 101 Years").max(1000).min(101).price_multiplier(3.8).build());
	 				}
	 			};
	 			for (BuildAge age :ages) {
	 				buildAgeService.save(age);
	 			}
	 			ArrayList<PersonAge> personages=new ArrayList<>(){
	 				{
	 					add(PersonAge.builder().title("0-18 Years").max(18).min(0).price_multiplier(3.5).build());
	 					add(PersonAge.builder().title("18-25 Years").max(25).min(18).price_multiplier(3.0).build());
	 					add(PersonAge.builder().title("26-35 Years").max(35).min(26).price_multiplier(2.5).build());
	 					add(PersonAge.builder().title("36-45 Years").max(45).min(36).price_multiplier(2.0).build());
	 					add(PersonAge.builder().title("46-55 Years").max(55).min(46).price_multiplier(1.5).build());
	 					add(PersonAge.builder().title("56-65 Years").max(65).min(56).price_multiplier(1.0).build());
	 					add(PersonAge.builder().title("66-75 Years").max(75).min(66).price_multiplier(0.5).build());
	 					add(PersonAge.builder().title("More Than 75 Years").max(90).min(76).price_multiplier(0.1).build());
	 					
	 					
	 				}
	 			};
	 			for (PersonAge age :personages) {
	 				personAgeService.save(age);
	 			}
	 }
	 }
