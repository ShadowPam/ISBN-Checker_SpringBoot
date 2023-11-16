package com.isbn.isbnchecker;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.isbn.isbnchecker.api.ISBNController;

@SpringBootTest
@AutoConfigureMockMvc
class IsbncheckerApplicationTests {

	@Autowired
	private ISBNController controller;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

	@Test
	void shouldReturnErrorWhenNotANumber() throws Exception {
		this.mockMvc.perform(get("/api/isbncheck/hdwak")).andExpect(status().isBadRequest())
				.andExpect(content().string(containsString("Expected input should be a sequence of number.")));
	}

	@Test
	void shouldReturnErrorWhenNot10or13Long() throws Exception {
		this.mockMvc.perform(get("/api/isbncheck/123775")).andExpect(status().isBadRequest())
				.andExpect(content().string(containsString("The input is not 10 or 13 numbers long.")));
		this.mockMvc.perform(get("/api/isbncheck/12377541651561321")).andExpect(status().isBadRequest())
				.andExpect(content().string(containsString("The input is not 10 or 13 numbers long.")));
	}

	@Test
	void shouldReturnErrorWhenNotISBN10() throws Exception {
		this.mockMvc.perform(get("/api/isbncheck/1234567890")).andExpect(status().isBadRequest())
				.andExpect(content().string(containsString("The 10 number input string is not an ISBN.")));
	}

	@Test
	void shouldReturnErrorWhenNotISBN13() throws Exception {
		this.mockMvc.perform(get("/api/isbncheck/1234567890123")).andExpect(status().isBadRequest())
				.andExpect(content().string(containsString("The 13 number input string is not an ISBN.")));
	}

	@Test
	void shouldReturnOkWhenISBN10() throws Exception {
		this.mockMvc.perform(get("/api/isbncheck/1566199093")).andExpect(status().isOk())
				.andExpect(content().string(containsString("1566199093")));

		this.mockMvc.perform(get("/api/isbncheck/9185057819")).andExpect(status().isOk())
				.andExpect(content().string(containsString("9185057819")));

		this.mockMvc.perform(get("/api/isbncheck/0136091814")).andExpect(status().isOk())
				.andExpect(content().string(containsString("0136091814")));
	}

	@Test
	void shouldReturnOkWhenISBN13() throws Exception {
		this.mockMvc.perform(get("/api/isbncheck/9781566199094")).andExpect(status().isOk())
				.andExpect(content().string(containsString("9781566199094")));

		this.mockMvc.perform(get("/api/isbncheck/9783161484100")).andExpect(status().isOk())
				.andExpect(content().string(containsString("9783161484100")));

		this.mockMvc.perform(get("/api/isbncheck/9780136091813")).andExpect(status().isOk())
				.andExpect(content().string(containsString("9780136091813")));
	}

	@Test
	void shouldReturnOkWhenISBN10WithX() throws Exception {
		this.mockMvc.perform(get("/api/isbncheck/043942089X")).andExpect(status().isOk())
				.andExpect(content().string(containsString("043942089X")));

		this.mockMvc.perform(get("/api/isbncheck/123456789X")).andExpect(status().isOk())
				.andExpect(content().string(containsString("123456789X")));

	}
}
