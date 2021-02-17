package com.example.searchengine;

import com.example.searchengine.Controller.SearchEngineController;
import com.example.searchengine.DAO.SearchEngineDAO;
import com.example.searchengine.DTO.Response.ProductFoundResponseDTO;
import com.example.searchengine.DTO.Validation.SearchValidation;
import com.example.searchengine.Model.Product;
import com.example.searchengine.Service.Impl.SearchEngineServiceImpl;
import com.example.searchengine.Service.SearchEngineService;
import com.example.searchengine.Utils.FilterBuilder;
import com.example.searchengine.Utils.HeapSort;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class SearchEngineApplicationTests {

//	@Test
//	void contextLoads() {
//	}

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private SearchEngineDAO repository;

	private SearchEngineService service;

	private SearchEngineController controller;

	@BeforeEach
	void setUp(){
		service = new SearchEngineServiceImpl(repository);
		controller = new SearchEngineController(service);
	}

	/**
	 * Ejercicio 1
	 */
	@Test
	void controllerShouldGetAllProducts(){
		//
		SearchValidation search = new SearchValidation();
		List<Product> products = createProducts("static/test_1.json");
		ObjectMapper objectMapper = new ObjectMapper();
		List<ProductFoundResponseDTO> list = objectMapper.convertValue(products, new TypeReference<List<ProductFoundResponseDTO>>() {});
		when(repository.getProductsByFilter(search)).thenReturn(products);

		//
		List<ProductFoundResponseDTO> returnedProducts = controller.getFromDTO(search);
		verify(repository, atLeast(1)).getProductsByFilter(search);

		//
		assertThat(returnedProducts, is(equalTo(list)));
	}

	/**
	 * Ejercicio 2
	 */
	@Test
	void controllerShouldGetFilteredProductsByCategory(){
		//
		SearchValidation search = new SearchValidation();
		search.setCategory("Herramientas");
		List<Product> products = createProducts("static/test_2.json");
		ObjectMapper objectMapper = new ObjectMapper();
		List<ProductFoundResponseDTO> list = objectMapper.convertValue(products, new TypeReference<List<ProductFoundResponseDTO>>() {});
		when(repository.getProductsByFilter(search)).thenReturn(products);

		//
		List<ProductFoundResponseDTO> returnedProducts = controller.getFromDTO(search);
		verify(repository, atLeast(1)).getProductsByFilter(search);

		//
		assertThat(returnedProducts, is(equalTo(list)));
	}

	/**
	 * Ejercicio 3
	 */
	@Test
	void controllerShouldGetFilteredProductsByCategoryAndShipping(){
		//
		SearchValidation search = new SearchValidation();
		search.setCategory("Herramientas");
		search.setFreeShipping(true);
		List<Product> products = createProducts("static/test_3.json");
		products = products.stream()
				.filter(i -> i.getFreeShipping() == true && i.getCategory().compareTo("Herramientas") == 0)
				.collect(Collectors.toList());
		ObjectMapper objectMapper = new ObjectMapper();
		List<ProductFoundResponseDTO> list = objectMapper.convertValue(products, new TypeReference<List<ProductFoundResponseDTO>>() {});
		when(repository.getProductsByFilter(search)).thenReturn(products);

		//
		List<ProductFoundResponseDTO> returnedProducts = controller.getFromDTO(search);
		verify(repository, atLeast(1)).getProductsByFilter(search);

		//
		assertThat(returnedProducts, is(equalTo(list)));
	}


	/**
	 * Ejercicio 4
	 */
	@Test
	void controllerShouldGetOrderedProductsAlphabeticallyAsc(){
		//
		Boolean ascendant = true;
		SearchValidation search = new SearchValidation();
		search.setOrder(0);
		List<Product> products = createProducts("static/test_1.json");
		products = FilterBuilder.filter(search, products);
		ObjectMapper objectMapper = new ObjectMapper();
		List<ProductFoundResponseDTO> list = objectMapper.convertValue(products, new TypeReference<List<ProductFoundResponseDTO>>() {});
		System.out.println(list);
		when(repository.getProductsByFilter(search)).thenReturn(products);

		//
		List<ProductFoundResponseDTO> returnedProducts = controller.getFromDTO(search);
		verify(repository, atLeast(1)).getProductsByFilter(search);

		//
		assertThat(returnedProducts, is(equalTo(list)));
	}

	/**
	 * Ejercicio 4
	 */
	@Test
	void controllerShouldGetOrderedProductsAlphabeticallyDesc(){
		//
		Boolean ascendant = true;
		SearchValidation search = new SearchValidation();
		search.setOrder(1);
		List<Product> products = createProducts("static/test_1.json");
		products =  FilterBuilder.filter(search, products);
		ObjectMapper objectMapper = new ObjectMapper();
		List<ProductFoundResponseDTO> list = objectMapper.convertValue(products, new TypeReference<List<ProductFoundResponseDTO>>() {});
		System.out.println(list);
		when(repository.getProductsByFilter(search)).thenReturn(products);

		//
		List<ProductFoundResponseDTO> returnedProducts = controller.getFromDTO(search);
		verify(repository, atLeast(1)).getProductsByFilter(search);

		//
		assertThat(returnedProducts, is(equalTo(list)));
	}

	/**
	 * Ejercicio 5
	 */
	@Test
	void controllerShouldGetOrderedProductsByPriceAsc(){
		//
		Boolean ascendant = true;
		SearchValidation search = new SearchValidation();
		search.setOrder(2);
		List<Product> products = createProducts("static/test_1.json");
		products =  FilterBuilder.filter(search, products);
		ObjectMapper objectMapper = new ObjectMapper();
		List<ProductFoundResponseDTO> list = objectMapper.convertValue(products, new TypeReference<List<ProductFoundResponseDTO>>() {});
		System.out.println(list);
		when(repository.getProductsByFilter(search)).thenReturn(products);

		//
		List<ProductFoundResponseDTO> returnedProducts = controller.getFromDTO(search);
		verify(repository, atLeast(1)).getProductsByFilter(search);

		//
		assertThat(returnedProducts, is(equalTo(list)));
	}

	/**
	 * Ejercicio 6
	 */
	@Test
	void controllerShouldGetOrderedProductsByPriceDesc(){
		//
		Boolean ascendant = true;
		SearchValidation search = new SearchValidation();
		search.setOrder(3);
		List<Product> products = createProducts("static/test_1.json");
		products =  FilterBuilder.filter(search, products);
		ObjectMapper objectMapper = new ObjectMapper();
		List<ProductFoundResponseDTO> list = objectMapper.convertValue(products, new TypeReference<List<ProductFoundResponseDTO>>() {});
		System.out.println(list);
		when(repository.getProductsByFilter(search)).thenReturn(products);

		//
		List<ProductFoundResponseDTO> returnedProducts = controller.getFromDTO(search);
		verify(repository, atLeast(1)).getProductsByFilter(search);

		//
		assertThat(returnedProducts, is(equalTo(list)));
	}


	private List<Product> createProducts(String resourceFilePath){
		try{
			ObjectMapper objectMapper = new ObjectMapper();
			List<Product> products = objectMapper.readValue(new ClassPathResource(resourceFilePath).getFile(), new TypeReference<List<Product>>() {});
			return products;
		} catch (Exception e){
			throw new RuntimeException("Error reading JSON file");
		}
	}

}
