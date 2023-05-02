package com.example.backend1webshop;

import com.example.backend1webshop.Webshop.models.Customer;
import com.example.backend1webshop.Webshop.repos.CustomerRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CustomerRepo mockRepository;

    @BeforeEach
    public void init(){
        Customer customer1 = new Customer(1L,"George", "19910419-4460");
        Customer customer2 = new Customer(2L,"Mickael", "19877656-8735");
        when(mockRepository.findById(1L)).thenReturn(Optional.of(customer1));
        when(mockRepository.findById(2L)).thenReturn(Optional.of(customer2));
        when(mockRepository.findAll()).thenReturn(Arrays.asList(customer1, customer2));
    }

    @Test
    void getAllCustomersTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/customers");
        MvcResult result = mvc.perform(request).andReturn();
        assertEquals("[{\"id\":1,\"name\":\"George\",\"ssn\":\"19910419-4460\"}," +
                "{\"id\":2,\"name\":\"Mickael\",\"ssn\":\"19877656-8735\"}]", result.getResponse().getContentAsString());
    }

    @Test
    void getCustomerByIdTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/customers/2");
        MvcResult result = mvc.perform(request).andReturn();
        assertEquals("{\"id\":2,\"name\":\"Mickael\",\"ssn\":\"19877656-8735\"}", result.getResponse().getContentAsString());
    }


    @Test
    void addCustomerTest() throws Exception{
        this.mvc.perform(post("/customers/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":3,\"name\":\"Elina\",\"ssn\":\"19877656-8735\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("New customer Elina was added")));
    }

}
