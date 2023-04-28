package com.example.backend1webshop;

import com.example.backend1webshop.Webshop.models.Customer;
import com.example.backend1webshop.Webshop.models.Orders;
import com.example.backend1webshop.Webshop.models.Product;
import com.example.backend1webshop.Webshop.repos.CustomerRepo;
import com.example.backend1webshop.Webshop.repos.OrdersRepo;
import com.example.backend1webshop.Webshop.repos.ProductRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class Backend1WebshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(Backend1WebshopApplication.class, args);
	}

	@Bean
	public CommandLineRunner bootstrapData(CustomerRepo customerRepo,
										  OrdersRepo ordersRepo,
										  ProductRepo productRepo) {
		return (args) -> {
			Customer c1 = new Customer("Gigi", "19910419-4459");
			Customer c2 = new Customer("Johan", "19610512-6745");
			Customer c3 = new Customer("Mimmi", "19810412-8965");
			Customer c4 = new Customer("Coco", "19712712-1243");
			customerRepo.save(c1);
			customerRepo.save(c2);
			customerRepo.save(c3);
			customerRepo.save(c4);

			Product p1 = new Product("Chanel 5", 1200);
			Product p2 = new Product("Chanel Cristalle", 1300);
			Product p3 = new Product("Black Phantom", 2400);
			Product p4 = new Product("Baccarat Rouge", 3200);
			Product p5 = new Product("Prada", 900);

			productRepo.save(p1);
			productRepo.save(p2);
			productRepo.save(p3);
			productRepo.save(p4);
			productRepo.save(p5);



			Orders ord1 = new Orders(LocalDateTime.now(), c1, Arrays.asList(p1, p2));
			Orders ord2 = new Orders(LocalDateTime.now(), c2, Arrays.asList(p3, p4));
			Orders ord3 = new Orders(LocalDateTime.now(), c3, List.of(p5));

			ordersRepo.save(ord1);
			ordersRepo.save(ord2);
			ordersRepo.save(ord3); //


		};
	}

}
