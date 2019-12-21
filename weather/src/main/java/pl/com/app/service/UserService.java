package pl.com.app.service;

import pl.com.app.dto.ProductDto;
import pl.com.app.dto.UserDto;
import pl.com.app.exception.MyException;
import pl.com.app.mappers.ModelMapper;
import pl.com.app.model.Category;
import pl.com.app.model.Product;
import pl.com.app.model.User;
import pl.com.app.model.enums.Preferences;
import pl.com.app.repository.UserRepository;
import pl.com.app.repository.impl.UserRepositoryImpl;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserService {
    private UserRepository userRepository = new UserRepositoryImpl();
    private ModelMapper modelMapper = new ModelMapper();

    public List<UserDto> findAll() {
        List<UserDto> userDtoList = new ArrayList<>();
        try {
            userDtoList = userRepository.findAll()
                    .stream()
                    .map(modelMapper::fromUserToUserDto)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            throw new MyException("USER SERVICE, FIND ALL");
        }
        return userDtoList;
    }

    public void deleteAll() {
        try {
            userRepository.deleteAll();
        } catch (Exception e) {
            throw new MyException("USER SERVICE, DELETE ALL");
        }
    }

    public void addAll(List<UserDto> items) {
        try {
            if (items == null) {
                throw new NullPointerException("USERS IS NULL");
            }
            userRepository.addAll(
                    items
                            .stream()
                            .map(modelMapper::fromUserDtoToUser)
                            .collect(Collectors.toList())
            );

        } catch (Exception e) {
            throw new MyException("USER SERVICE, ADD ALL");
        }
    }

    public Optional<UserDto> findByPassword(String password) {
        Optional<UserDto> userDtoOptional = Optional.empty();
        try {
            if (password == null) {
                throw new NullPointerException("PASSWORD IS NULL");
            }

            userDtoOptional = userRepository
                    .findByPassword(password)
                    .stream()
                    .map(modelMapper::fromUserToUserDto)
                    .findFirst();
        } catch (Exception e) {
            throw new MyException("USER SERVICE, FIND BY PASSWORD");
        }
        return userDtoOptional;
    }

    public void addUser(UserDto userDto) {
        try {
            if (userDto == null){
                throw new NullPointerException("USER DTO IS NULL");
            }

            boolean isUserExist = findByPassword(userDto.getPassword()).isPresent();

            if (isUserExist){
                throw new IllegalArgumentException("PASSWORD ALREADY EXIST");
            }

            User user = modelMapper.fromUserDtoToUser(userDto);
            userRepository.addOrUpdate(user);
        } catch (Exception e) {
            throw new MyException("USER SERVICE, ADD USER");
        }
    }

    public List<ProductDto> sortByUserPreferences(List<ProductDto> productDtoList, Preferences preferences) {
        List<ProductDto> sortedProductDtoList = new ArrayList<>();
        try {
            if (productDtoList == null || preferences == null){
                throw new NullPointerException("ARGUMENTS ARE NULL");
            }
            if(preferences == Preferences.NAME_DESC){
                sortedProductDtoList = sortByNameDesc(productDtoList);
            } else if(preferences == Preferences.PRICE_DESC){
                sortedProductDtoList = sortByPriceDesc(productDtoList);
            } else if(preferences == Preferences.PRICE_MIN){
                sortedProductDtoList = productMinPrice(productDtoList);
            }

        } catch (Exception e) {
            throw new MyException("USER SERVICE, SORT BY USER PREFERENCES");
        }
        return sortedProductDtoList;
    }

    private List<ProductDto> productMinPrice(List<ProductDto> productDtoList) {
        return productDtoList
                .stream()
                .min(Comparator.comparing(ProductDto::getPrice))
                .stream()
                .collect(Collectors.toList());
    }

    private List<ProductDto> sortByNameDesc(List<ProductDto> productDtoList) {
        return productDtoList
                .stream()
                .sorted(Comparator.comparing(ProductDto::getName, Comparator.reverseOrder()))
                .limit(10)
                .collect(Collectors.toList());
    }

    private List<ProductDto> sortByPriceDesc(List<ProductDto> productDtoList) {
        return productDtoList
                .stream()
                .sorted(Comparator.comparing(ProductDto::getPrice, Comparator.reverseOrder()))
                .limit(10)
                .collect(Collectors.toList());
    }
}
