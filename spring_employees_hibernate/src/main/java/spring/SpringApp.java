package spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

//@ComponentScan(basePackages =
//		"spring.hibernate")

@SpringBootApplication
//@EnableJpaRepositories
public class SpringApp {

	public static void main(String[] args) {
		SpringApplication.run(SpringApp.class, args);

	}
}
