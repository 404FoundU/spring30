package com.javatechie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class PurchaseOrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PurchaseOrderServiceApplication.class, args);
	}

}


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MySpringBootApplication {
	private static ConfigurableApplicationContext context;

	public static void main(String[] args) {
		context = SpringApplication.run(MySpringBootApplication.class, args);
	}

	import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

	@Component
	public class GracefulShutdown implements DisposableBean {

		@Override
		public void destroy() {
			System.out.println("Closing resources... Performing graceful shutdown.");
			// Close database connections, flush logs, etc.
		}
	}

}
