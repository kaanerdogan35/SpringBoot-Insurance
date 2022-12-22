package com.springacentesbmdeneme.API;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@WebMvcTest(LifeController.class)
@AutoConfigureMockMvc(addFilters = false)
public class LifeControllerTest {
	
}
