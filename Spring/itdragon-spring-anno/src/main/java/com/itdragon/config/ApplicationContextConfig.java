package com.itdragon.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * Spring 配置类
 * 配置数据源，事务管理，bean，自动扫描包
 * @author itdragon
 *
 */
@Configuration	// 声明该类为配置类
@PropertySource({"classpath:propertySource.properties"})	// 引入外部文件
@ComponentScan("com.itdragon")	// 配置自动扫描包的路径
@EnableTransactionManagement	// 开启基于注解的事务管理功能
public class ApplicationContextConfig {
	
	@Value("${DATA_USER}")
	private String DATA_USER;
	
	@Value("${DATA_PAWD}")
	private String DATA_PAWD;
	
	@Value("${DATA_DRIVER}")
	private String DATA_DRIVER;
	
	@Value("${DATA_JDBC_URL}")
	private String DATA_JDBC_URL;
	
	@Bean
	public DataSource dataSource() throws Exception{
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setUser(DATA_USER);
		dataSource.setPassword(DATA_PAWD);
		dataSource.setDriverClass(DATA_DRIVER);
		dataSource.setJdbcUrl(DATA_JDBC_URL);
		return dataSource;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate() throws Exception{
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
		return jdbcTemplate;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() throws Exception{
		return new DataSourceTransactionManager(dataSource());
	}

}
