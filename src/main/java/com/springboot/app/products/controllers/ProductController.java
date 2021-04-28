package com.springboot.app.products.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.commons.models.entity.Product;
import com.springboot.app.products.models.service.IProductService;

@RestController
//@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	private IProductService productoService;
	
	@Autowired
	private Environment env;
	
	@Value("${server.port}")
	private Integer port;

	@GetMapping("/getAll")
	public List<Product> getAllProducts(){
		return productoService.findAll().stream().map(p -> {
			//p.setPort(Integer.parseInt(env.getProperty("local.server.port")));
			p.setPort(port);
			return p;
		}).collect(Collectors.toList());
	}
	
	@GetMapping("/get/{id}")
	public Product productDetails(@PathVariable Long id) {
		Product product = productoService.findById(id);
		//product.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		product.setPort(port);
		return product;
	}
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public Product create(@RequestBody Product product) {
		return productoService.save(product);
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		productoService.delete(id);
	}
	
	@PutMapping("/modify/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Product modify(@RequestBody Product product, @PathVariable Long id) {
		Product prod = productoService.findById(id);
		prod.setName(product.getName());
		prod.setPrice(product.getPrice());
		return productoService.save(product);
	}
}
