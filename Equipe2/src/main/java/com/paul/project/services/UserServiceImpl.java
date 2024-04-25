package com.paul.project.services;

import com.paul.project.dto.UserDto;
import com.paul.project.entity.User;
import com.paul.project.repository.UserRepository;
import com.paul.project.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    //communicate with database
    private final UserRepository userRepository;
    //to validate the objects
    private final ObjectsValidator<UserDto> validator;

    @Override
    public Integer save(UserDto dto) {
        validator.validate(dto);
        User user = UserDto.toEntity(dto);
        return userRepository.save(user).getId();
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Integer id) {
        return userRepository.findById(id)
                .map(UserDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException("no user not found the provided: "+ id));
    }

    @Override
    public void delete(Integer id) {
        //todo check
        userRepository.deleteById(id);
    }
}
