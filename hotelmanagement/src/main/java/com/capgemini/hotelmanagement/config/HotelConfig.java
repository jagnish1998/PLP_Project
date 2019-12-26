package com.capgemini.hotelmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;


@Configuration
public class HotelConfig {

	@Bean
	public LocalEntityManagerFactoryBean getEntityManagerFactory() {
		LocalEntityManagerFactoryBean factoryBean = new LocalEntityManagerFactoryBean();
		factoryBean.setPersistenceUnitName("hotelPersistenceUnit");
		return factoryBean;

	}
}
