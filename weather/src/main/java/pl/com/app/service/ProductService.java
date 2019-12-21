package pl.com.app.service;

import pl.com.app.dto.ProductDto;
import pl.com.app.exception.MyException;
import pl.com.app.mappers.ModelMapper;
import pl.com.app.model.enums.WeatherCondition;
import pl.com.app.repository.CategoryRepository;
import pl.com.app.repository.ProductRepository;
import pl.com.app.repository.impl.CategoryRepositoryImpl;
import pl.com.app.repository.impl.ProductRepositoryImpl;
import pl.com.app.weather.Weather;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductService {
    private ProductRepository productRepository = new ProductRepositoryImpl();
    private CategoryRepository categoryRepository = new CategoryRepositoryImpl();
    private ModelMapper modelMapper = new ModelMapper();

    public List<ProductDto> findAll() {
        List<ProductDto> productDtoList = new ArrayList<>();
        try {
            productDtoList = productRepository.findAll()
                    .stream()
                    .map(modelMapper::fromProductToProductDto)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            throw new MyException("PRODUCT SERVICE, FIND ALL");
        }
        return productDtoList;
    }

    public void deleteAll() {
        try {
            productRepository.deleteAll();
        } catch (Exception e) {
            throw new MyException("PRODUCT SERVICE, DELETE ALL");
        }
    }

    public void addAll(List<ProductDto> items) {
        try {
            if (items == null) {
                throw new NullPointerException("PRODUCTS IS NULL");
            }
            productRepository.addAll(
                    items
                            .stream()
                            .map(modelMapper::fromProductDtoToProduct)
                            .peek(x -> x.setCategory(categoryRepository.findByName(x.getCategory().getName()).orElseThrow(NullPointerException::new)))
                            .collect(Collectors.toList())
            );

        } catch (Exception e) {
            throw new MyException("PRODUCT SERVICE, ADD ALL");
        }
    }

    public List<ProductDto> findProductsByWeatherCondition(List<Weather> weather) {
        List<ProductDto> productDtoList = new ArrayList<>();
        try {
            if (weather == null) {
                throw new NullPointerException("WEATHER IS NULL");
            }

            final List<String> weatherConditions = weather
                    .stream()
                    .map(Weather::getMain)
                    .map(String::toLowerCase)
                    .collect(Collectors.toList());

            List<ProductDto> allProducts = productRepository.findAllWithChildren()
                    .stream()
                    .map(modelMapper::fromProductToProductDto)
                    .collect(Collectors.toList());

            productDtoList = allProducts
                    .stream()
                    .filter(x -> x.getCategoryDto()
                            .getWeatherConditions()
                            .stream()
                            .map(WeatherCondition::toString)
                            .map(String::toLowerCase)
                            .anyMatch(weatherConditions::contains)
                    )
                    .collect(Collectors.toList());

            if (productDtoList.isEmpty()) {
                productDtoList = allProducts
                        .stream()
                        .filter(x -> x.getCategoryDto().getWeatherConditions().contains(WeatherCondition.UNKNOWN))
                        .collect(Collectors.toList());
            }

        } catch (Exception e) {
            throw new MyException("PRODUCT SERVICE, FIND BY WEATHER CONDITION");
        }
        return productDtoList;
    }
}
