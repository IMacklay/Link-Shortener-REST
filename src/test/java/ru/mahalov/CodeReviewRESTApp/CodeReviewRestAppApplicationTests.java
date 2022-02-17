package ru.mahalov.CodeReviewRESTApp;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import ru.mahalov.CodeReviewRESTApp.service.LinkGeneratorService;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
class CodeReviewRestAppApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private LinkGeneratorService linkGeneratorService;

	@Test
	void contextLoads() throws Exception {
		mockMvc.perform(get("/"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Welcome to our service!")));
	}

	@Test
	void shouldReturnShortLink() throws Exception {
		mockMvc.perform(post("/generate")
						.param("original", "https://ya.ru")
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("link", Matchers.equalTo("/l/B")))
		;
	}

	@Test
	void shouldReturnErrorStatusForLocalHost() throws Exception {
		mockMvc.perform(post("/generate")
						.param("original", "http://localhost:8080"))
				.andDo(print())
				.andExpect(status().isBadRequest());
	}

	@Test
	void shouldReturnOriginalLink() throws Exception{

		if (linkGeneratorService.read("B")==null)
			linkGeneratorService.add("https://ya.ru");

		mockMvc.perform(get("/l/B"))
				.andDo(print())
				.andExpect(status().is3xxRedirection());
	}

}
