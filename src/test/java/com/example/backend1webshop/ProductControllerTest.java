package com.example.backend1webshop;

import com.example.backend1webshop.Webshop.models.Customer;
import com.example.backend1webshop.Webshop.models.Orders;
import com.example.backend1webshop.Webshop.models.Product;
import com.example.backend1webshop.Webshop.repos.CustomerRepo;
import com.example.backend1webshop.Webshop.repos.OrdersRepo;
import com.example.backend1webshop.Webshop.repos.ProductRepo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductRepo productRepo;

    @MockBean
    private CustomerRepo customerRepo;

    @MockBean
    private OrdersRepo ordersRepo;

    @BeforeEach
    public void init(){
        Product p1 = new Product(1L,"Chanel",1200);
        Product p2 = new Product(2L,"Prada",1000);
        Product p3 = new Product(3L,"Gucci",750);

        List<Product>prod1 = Arrays.asList(p1,p2);
        List<Product>prod2 = Arrays.asList(p2,p3);

        when(productRepo.findById(1L)).thenReturn(Optional.of(p1));
        when(productRepo.findById(2L)).thenReturn(Optional.of(p2));
        when(productRepo.findById(3L)).thenReturn((Optional.of(p3)));
        when((productRepo.findAll())).thenReturn(Arrays.asList(p1,p2,p3));


        Customer c1 = new Customer(1L,"Nicki","19877656-8733");
        Customer c2 = new Customer(2L,"Pedro","19576956-7856");

        when(customerRepo.findById(1L)).thenReturn(Optional.of(c1));
        when(customerRepo.findById(2L)).thenReturn(Optional.of(c2));
        when((customerRepo.findAll())).thenReturn(Arrays.asList(c1,c2));

        Orders o1 = new Orders(1L, LocalDateTime.now(), new Customer(3L,"Nicki","19877656-8733"),
                prod1 );
        Orders o2 = new Orders(2L, LocalDateTime.now(),new Customer(4L,"Pedro","19576956-7856"),
                prod2);

        when(ordersRepo.findById(1L)).thenReturn((Optional.of(o1)));
        when(ordersRepo.findById(2L)).thenReturn((Optional.of(o2)));
        when((ordersRepo.findAll())).thenReturn(Arrays.asList(o1,o2));

    }

    /*  @RequestMapping("items")
    public List<Product> getAllProducts(){
        return repo.findAll();
    }*/

    @Test
    void getAllProducts() throws Exception{
        this.mvc.perform(get("/items"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"name\":\"Chanel\",\"price\":1200}," +
                        "{\"id\":2,\"name\":\"Prada\",\"price\":1000}," +
                        "{\"id\":3,\"name\":\"Gucci\",\"price\":750}]"));
    }

    /* @RequestMapping("/items/{id}/getItem")
    public Product itemsById(@PathVariable Long id) {
        if (repo.findById(id).isPresent()) {
            return repo.findById(id).get();
        } else {
            log.info("No item with that id exists");
            return null;
        }
    }*/

    @Test
    void itemsById() throws Exception{
        this.mvc.perform(get("/items/1/getItem"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"name\":\"Chanel\",\"price\":1200}"));
    }


    /*  @PostMapping("items/add")
    public String addProduct(@RequestBody Product p){
        try {
            repo.save(p);
            log.info("Product saved");
            return "Saved";
        } catch(DataAccessException e){
                log.error("Failed to save product", e);
                return "Failed to save product";
            }
    }*/

    @Test
    void addProduct() throws Exception{
        this.mvc.perform(post("/items/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":4,\"name\":\"Valentino\",\"price\":1300}"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Saved")));
    }

   /* @RequestMapping("items/buy")
    public List<Orders> buyProductById(@RequestParam Long customerId, @RequestParam Long productId){
        try{
            this.oRepo.save(new Orders(LocalDateTime.now(),
                    cRepo.findById(customerId).get(),
                    Arrays.asList(this.repo.findById(productId).get())));
            log.info("Order was successfully made!");
            return oRepo.findAll();
        }
        catch (Exception e){
            e.printStackTrace();
            log.error("Something went wrong!");
        }
        return null;
        // ?customerId=1&productId=1
    }*/

    @Test
    void buyProductById() throws Exception {
        Customer customer = new Customer(3L, "Lilu", "19981202-4563");
        Product product = new Product(2L, "Moschino", 650);
        when(customerRepo.findById(3L)).thenReturn(Optional.of(customer));
        when(productRepo.findById(2L)).thenReturn(Optional.of(product));

        mvc.perform(post("/items/buy")
                        .param("customerId", "3")
                        .param("productId", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].customer.id").value(customer.getId()))
                .andExpect(jsonPath("$[0].products[1].id").value(product.getId()));
    }


}
