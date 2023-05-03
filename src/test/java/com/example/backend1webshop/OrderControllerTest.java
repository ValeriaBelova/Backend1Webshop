package com.example.backend1webshop;


import com.example.backend1webshop.Webshop.models.Customer;
import com.example.backend1webshop.Webshop.models.Orders;
import com.example.backend1webshop.Webshop.models.Product;
import com.example.backend1webshop.Webshop.repos.CustomerRepo;
import com.example.backend1webshop.Webshop.repos.OrdersRepo;
import com.example.backend1webshop.Webshop.repos.ProductRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private OrdersRepo ordersRepo;

    @MockBean
    private CustomerRepo customerRepo;

    @MockBean
    private ProductRepo productRepo;

    @BeforeEach
    public void init() {
        Product p1 = new Product(1L, "Chanel", 1200);
        Product p2 = new Product(2L, "Prada", 1000);
        Product p3 = new Product(3L, "Gucci", 750);

        List<Product> prod1 = Arrays.asList(p1, p2);
        List<Product> prod2 = Arrays.asList(p2, p3);

        when(productRepo.findById(1L)).thenReturn(Optional.of(p1));
        when(productRepo.findById(2L)).thenReturn(Optional.of(p2));
        when(productRepo.findById(3L)).thenReturn((Optional.of(p3)));
        when((productRepo.findAll())).thenReturn(Arrays.asList(p1, p2, p3));


        Customer c1 = new Customer(1L, "Nicki", "19877656-8733");
        Customer c2 = new Customer(2L, "Pedro", "19576956-7856");

        when(customerRepo.findById(1L)).thenReturn(Optional.of(c1));
        when(customerRepo.findById(2L)).thenReturn(Optional.of(c2));
        when((customerRepo.findAll())).thenReturn(Arrays.asList(c1, c2));

        Orders o1 = new Orders(1L, LocalDateTime.now(), new Customer(3L, "Nicki", "19877656-8733"),
                prod1);
        Orders o2 = new Orders(2L, LocalDateTime.now(), new Customer(4L, "Pedro", "19576956-7856"),
                prod2);

        when(ordersRepo.findById(1L)).thenReturn((Optional.of(o1)));
        when(ordersRepo.findById(2L)).thenReturn((Optional.of(o2)));
        when(ordersRepo.findOrdersByCustomer_Id(3L)).thenReturn(Arrays.asList(o1));

        when((ordersRepo.findAll())).thenReturn(Arrays.asList(o1, o2));

    }

    /* @RequestMapping("/orders")
    public List<Orders> getAllOrders(){
        List<Orders> listOfOrders = repo.findAll();
        if(listOfOrders.isEmpty()){
            log.warn("The list of orders was empty");
        }
        return repo.findAll();
    }*/

    @Test
    void getAllOrders() throws Exception {
        MvcResult mvcResult = mvc.perform(get("/orders"))
                .andExpect(status().isOk())
                .andReturn();

        String expectedResponse = objectMapper.writeValueAsString(ordersRepo.findAll());
        String actualResponse = mvcResult.getResponse().getContentAsString();
        JSONAssert.assertEquals(expectedResponse, actualResponse, true);
    }

    /* @RequestMapping("/orders/{customerId}")
    public List<Orders> getOrdersByCustomerId(@PathVariable Long customerId){
        List<Orders> listOfOrders = repo.findAll();
        return listOfOrders.stream()
                .filter(orders -> Objects.equals(orders.getCustomer().getId(), customerId))
                .toList();
    }*/

   @Test
   void getOrdersByCustomerId() throws Exception{
       MvcResult mvcResult = mvc.perform(get("/orders/3"))
               .andExpect(status().isOk())
               .andReturn();

       String expectedResponse = objectMapper.writeValueAsString(ordersRepo.findOrdersByCustomer_Id(3L));
       String actualResponse = mvcResult.getResponse().getContentAsString();
       JSONAssert.assertEquals(expectedResponse, actualResponse, true);
   }
}
