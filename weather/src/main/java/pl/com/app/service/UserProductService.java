package pl.com.app.service;

import pl.com.app.dto.UserDto;
import pl.com.app.dto.UserProductDto;
import pl.com.app.dto.WeatherProductsDto;
import pl.com.app.exception.MyException;
import pl.com.app.mappers.ModelMapper;
import pl.com.app.model.UserProduct;
import pl.com.app.model.enums.WeatherCondition;
import pl.com.app.repository.UserCategoryRepository;
import pl.com.app.repository.UserProductRepository;
import pl.com.app.repository.UserRepository;
import pl.com.app.repository.impl.UserCategoryRepositoryImpl;
import pl.com.app.repository.impl.UserProductRepositoryImpl;
import pl.com.app.repository.impl.UserRepositoryImpl;
import pl.com.app.weather.Weather;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserProductService {
    private UserProductRepository userProductRepository = new UserProductRepositoryImpl();
    private UserRepository userRepository = new UserRepositoryImpl();
    private UserCategoryRepository userCategoryRepository = new UserCategoryRepositoryImpl();
    private ModelMapper modelMapper = new ModelMapper();

    public List<UserProductDto> findAll() {
        List<UserProductDto> userProductDtos = new ArrayList<>();
        try {
            userProductDtos = userProductRepository.findAll()
                    .stream()
                    .map(modelMapper::fromUserProductToUserProductDto)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            throw new MyException("USER PRODUCT SERVICE, FIND ALL");
        }
        return userProductDtos;
    }

    public void deleteAll() {
        try {
            userProductRepository.deleteAll();
        } catch (Exception e) {
            throw new MyException("USER PRODUCT SERVICE, DELETE ALL");
        }
    }

    public void addAll(List<UserProductDto> items) {
        try {
            if (items == null) {
                throw new NullPointerException("USER PRODUCTS IS NULL");
            }
            userProductRepository.addAll(
                    items
                            .stream()
                            .map(modelMapper::fromUserProductDtoToUserProduct)
                            .peek(x -> x.setUserCategory(userCategoryRepository.findByName(x.getUserCategory().getName()).orElseThrow(NullPointerException::new)))
                            .peek(x -> x.setUser(userRepository.findByPassword(x.getUser().getPassword()).orElseThrow(NullPointerException::new)))
                            .collect(Collectors.toList())
            );

        } catch (Exception e) {
            throw new MyException("USER PRODUCT SERVICE, ADD ALL");
        }
    }

    public void saveUserProducts(WeatherProductsDto weatherProductsDto, UserDto loginUser) {
        try {
            if (weatherProductsDto == null || loginUser == null) {
                throw new NullPointerException("ARGS ARE NULL");
            }

            final List<String> weatherConditions = weatherProductsDto
                    .getWeatherData()
                    .getWeather()
                    .stream()
                    .map(Weather::getMain)
                    .map(String::toLowerCase)
                    .collect(Collectors.toList());

            List<UserProduct> userProductsToSave = weatherProductsDto
                    .getProductModelDtoList()
                    .stream()
                    .filter(x -> weatherConditions
                            .stream()
                            .anyMatch(w -> w.equalsIgnoreCase(getWeatherByProductCondition(x.getName()).toString()))
                    )
                    .map(x -> new UserProductDto(x.getName(), x.getPrice(), x.getCategory(), loginUser))
                    .map(modelMapper::fromUserProductDtoToUserProduct)
                    .peek(x ->
                            x.setUserCategory(
                                    userCategoryRepository.findByName(x.getUserCategory().getName())
                                    .orElseGet(() -> userCategoryRepository.addOrUpdate(x.getUserCategory()))
                            )
                    )
                    .collect(Collectors.toList());
            System.out.println(userProductsToSave);
            userProductRepository.addAll(userProductsToSave);

        } catch (Exception e) {
            throw new MyException("USER PRODUCT SERVICE, FIND ALL");
        }
    }

    public WeatherCondition getWeatherByProductCondition(String productName){
        char firstLetterOfProductName = productName.charAt(0);
        if(firstLetterOfProductName >= 'a' && firstLetterOfProductName <= 'd'){
            return WeatherCondition.CLEAR;
        } else if(firstLetterOfProductName >= 'e' && firstLetterOfProductName <= 'h'){
            return WeatherCondition.RAIN;
        } else if(firstLetterOfProductName >= 'i' && firstLetterOfProductName <= 'm'){
            return WeatherCondition.DRIZZLE;
        } else if(firstLetterOfProductName >= 'n' && firstLetterOfProductName <= 's'){
            return WeatherCondition.CLOUDS;
        } else if(firstLetterOfProductName >= 't' && firstLetterOfProductName <= 'x'){
            return WeatherCondition.SUNSHINE;
        } else {
            return WeatherCondition.UNKNOWN;
        }
    }
}
