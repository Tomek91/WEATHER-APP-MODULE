package pl.com.app.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.app.exceptions.ExceptionCode;
import pl.com.app.exceptions.MyException;
import pl.com.app.model.Product;
import pl.com.app.parsers.json.ProductsConverter;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductsConverter productsConverter;

    @GetMapping
    public List<Product> getAll() {
        return productsConverter
                .fromJson()
                .orElseThrow(() -> new MyException(ExceptionCode.HTTP, "PARSE PRODUCTS EXCEPTION"));
    }
}
