package com.company;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.company.controller.GreetingController;
import com.company.service.GreetingService;

@RunWith(SpringRunner.class)
@WebMvcTest(GreetingController.class)
public class WebMockTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private GreetingService service;

	@Test
	public void greetingShouldReturnMessageFromService() throws Exception {
		when(service.greet()).thenReturn("Hello Mock");
		this.mockMvc.perform(get("/greeting")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Hello Mock")));
	}

	@Test
	public void modelShouldReturnGreetingModel() throws Exception {
		this.mockMvc.perform(get("/model")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(jsonPath("id", is("1")))
				.andExpect(jsonPath("description", is("greet")));
	}

	@Test
	public void modelsShouldReturnListOfGreetingModel() throws Exception {
		this.mockMvc.perform(get("/models")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$[0].id", is("1")))
				.andExpect(jsonPath("$[0].description", is("a")))
				.andExpect(jsonPath("$[1].id", is("2")))
				.andExpect(jsonPath("$[1].description", is("b")))
				.andExpect(jsonPath("$[2].id", is("3")))
				.andExpect(jsonPath("$[2].description", is("c")));
	}

}
