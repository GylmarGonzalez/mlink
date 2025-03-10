package com.mlink.conf.db;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "transactionalEntityManagerFactory", transactionManagerRef = "transactionalTransactionManager", 
	basePackages = { "com.mlink"})
@Profile("dev")
public class DataSourceConfigDev {
	
	
	String defecto = "default";
	
	@Value("${spring.datasource.username}")
	private String usernameDev = defecto;
	
	@Value("${spring.datasource.password}")
	private String passDev = defecto;
	
	@Value("${spring.datasource.url}")
	private String urlDev = defecto;


    @Bean(name = "transactionalDataSource")
    DataSource userDatasource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(urlDev);
		dataSource.setUsername(usernameDev);
		dataSource.setPassword(passDev);
		dataSource.setDriverClassName("org.hibernate.dialect.PostgreSQLDialect");
		
		return dataSource;
	}
	
	@Bean(name = "transactionalEntityManagerFactory")
	 LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(userDatasource());
		em.setPackagesToScan("com.mlink.entities");
		
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);		
		
		return em;
		
	}
	
	@Bean(name = "transactionalTransactionManager")
	 PlatformTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		
		return transactionManager;
	}
	
	@Bean(name = "jdbcTransactional")
	 JdbcTemplate jdbcTemplate1(@Qualifier("transactionalDataSource") DataSource ds) {
	  return new JdbcTemplate(ds);
	}

}