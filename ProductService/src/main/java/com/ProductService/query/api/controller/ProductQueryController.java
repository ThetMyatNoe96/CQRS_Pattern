package com.ProductService.query.api.controller;

import java.util.List;

import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProductService.command.api.model.ProductRestModel;
import com.ProductService.query.api.queries.GetProductQuery;

@RestController
@RequestMapping("/products")
public class ProductQueryController {

	private QueryGateway queryGateway;

	public ProductQueryController(QueryGateway queryGateway) {
		super();
		this.queryGateway = queryGateway;
	}
	
	@GetMapping
	public List<ProductRestModel> getAllProducts(){
		GetProductQuery getProductQuery= new GetProductQuery();
		
		List<ProductRestModel> productRestModels=  queryGateway.
							query(getProductQuery, ResponseTypes.multipleInstancesOf(ProductRestModel.class))
							.join();
		return productRestModels;
	}
	
	
}
