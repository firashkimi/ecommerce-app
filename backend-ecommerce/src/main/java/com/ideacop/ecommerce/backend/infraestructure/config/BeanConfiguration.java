package com.ideacop.ecommerce.backend.infraestructure.config;

import com.ideacop.ecommerce.backend.application.*;
import com.ideacop.ecommerce.backend.domain.port.ICategoryRepository;
import com.ideacop.ecommerce.backend.domain.port.IOrderRepository;
import com.ideacop.ecommerce.backend.domain.port.IProductRepository;
import com.ideacop.ecommerce.backend.domain.port.IUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class BeanConfiguration {

    @Bean
    public UserService userService(IUserRepository iUserRepository) {
        return new UserService(iUserRepository);
    }

    @Bean
    public CategoryService categoryService(ICategoryRepository iCategoryRepository) {
        return new CategoryService(iCategoryRepository);
    }

    @Bean
    public UploadFile uploadFile(){
        return new UploadFile();
    }

    @Bean
    public ProductService productService(IProductRepository iProductRepository, UploadFile uploadFile) {
        return new ProductService(iProductRepository, uploadFile);
    }

    @Bean
    public OrderService orderService(IOrderRepository iOrderRepository) {
        return new OrderService(iOrderRepository);
    }

    @Bean
    public RegistrationService registrationService(IUserRepository iUserRepository) {
        return new RegistrationService(iUserRepository);
    }
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
        config.setAllowedHeaders(Arrays.asList(
                HttpHeaders.ORIGIN,
                HttpHeaders.CONTENT_TYPE,
                HttpHeaders.ACCEPT,
                HttpHeaders.AUTHORIZATION
        ));
        config.setAllowedMethods(Arrays.asList(
                "GET",
                "POST",
                "DELETE",
                "PUT",
                "PATCH"
        ));
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);

    }
}
