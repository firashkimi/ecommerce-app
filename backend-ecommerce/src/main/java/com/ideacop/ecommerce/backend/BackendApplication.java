package com.ideacop.ecommerce.backend;


import com.ideacop.ecommerce.backend.domain.model.User;
import com.ideacop.ecommerce.backend.domain.model.UserType;
import com.ideacop.ecommerce.backend.domain.port.IUserRepository;
import com.ideacop.ecommerce.backend.infraestructure.adapter.IUserCrudRepository;
import com.ideacop.ecommerce.backend.infraestructure.adapter.UserCrudRepositoryImpl;
import com.ideacop.ecommerce.backend.infraestructure.entity.UserEntity;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BackendApplication {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private IUserCrudRepository iUserRepository;

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@PostConstruct
	public void init() {
		if (iUserRepository.findByEmail("ahlem@gmail.com").isEmpty()) {
			UserEntity userEntity = new UserEntity();
			userEntity.setEmail("ahlem@gmail.com");
			userEntity.setPassword("Ahlem123");
			userEntity.setAddress("Tunis");
			userEntity.setFirstname("ahlem");
			userEntity.setLastname("ahlem");
			userEntity.setUserType(UserType.ADMIN);
			userEntity.setCellphone("99999999");

			userEntity.setPassword(passwordEncoder.encode("Ahlem123"));
userEntity.setUsername("ahlem@gmail.com");
			iUserRepository.save(userEntity);
		}

	}
}
