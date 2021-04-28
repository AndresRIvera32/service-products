package com.springboot.app.products.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.springboot.app.commons.models.entity.Product;

public interface ProductDao extends CrudRepository<Product, Long> {

}
