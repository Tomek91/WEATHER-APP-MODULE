package pl.com.app.service;

import pl.com.app.dto.UserCategoryDto;
import pl.com.app.exception.MyException;
import pl.com.app.mappers.ModelMapper;
import pl.com.app.repository.UserCategoryRepository;
import pl.com.app.repository.impl.UserCategoryRepositoryImpl;

import java.util.List;
import java.util.stream.Collectors;

public class UserCategoryService {
    private UserCategoryRepository  userCategoryRepository = new UserCategoryRepositoryImpl();
    private ModelMapper modelMapper = new ModelMapper();


    public void deleteAll() {
        try {
            userCategoryRepository.deleteAll();
        } catch (Exception e) {
            throw new MyException("USER CATEGORY SERVICE, DELETE ALL");
        }
    }

    public void addAll(List<UserCategoryDto> items) {
        try {
            if (items == null) {
                throw new NullPointerException("CATEGORIES IS NULL");
            }
            userCategoryRepository.addAll(
                    items
                            .stream()
                            .map(modelMapper::fromUserCategoryDtoToUserCategory)
                            .collect(Collectors.toList())
            );

        } catch (Exception e) {
            throw new MyException("USER CATEGORY SERVICE, ADD ALL");
        }
    }

}
