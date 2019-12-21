package pl.com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.com.app.parsers.FileNames;
import pl.com.app.parsers.json.ProductsConverter;

@SpringBootApplication
public class RestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApiApplication.class, args);
    }

    @Bean
    public ProductsConverter addressesConverter() {
        return new ProductsConverter(FileNames.PRODUCTS);
    }
}
