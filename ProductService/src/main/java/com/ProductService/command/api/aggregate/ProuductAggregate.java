package com.ProductService.command.api.aggregate;

import java.math.BigDecimal;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import com.ProductService.command.api.commands.CreateProductCommand;
import com.ProductService.command.api.events.ProductCreatedEvent;

@Aggregate
public class ProuductAggregate {
	
	@AggregateIdentifier
	private String productId;
	private String name;
	private BigDecimal price;
	private Integer quantity;
	
		@CommandHandler
		public ProuductAggregate(CreateProductCommand createProductCommand) {
			
			// You can perform all the validations 
			ProductCreatedEvent productCreatedEvent=new ProductCreatedEvent();
			 
			BeanUtils.copyProperties(createProductCommand, productCreatedEvent);
			AggregateLifecycle.apply(productCreatedEvent);  // to publish the events 
											
			
		}
		
		public ProuductAggregate() {
			
		}
		
		@EventSourcingHandler		// Whenever there is a command , you should have event sourcing handler to handle that particular command
		public void on (ProductCreatedEvent productCreatedEvent) {
			this.quantity=productCreatedEvent.getQuantity();
			this.productId=productCreatedEvent.getProductId();
			this.price=productCreatedEvent.getPrice();
			this.name=productCreatedEvent.getName();
			
			
		}
		
		

}
