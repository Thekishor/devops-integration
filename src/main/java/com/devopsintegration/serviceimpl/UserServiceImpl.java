package com.devopsintegration.serviceimpl;

import com.devopsintegration.dto.UserDto;
import com.devopsintegration.exception.ResourceNotFoundException;
import com.devopsintegration.exception.UserAlreadyExist;
import com.devopsintegration.model.Address;
import com.devopsintegration.model.User;
import com.devopsintegration.repository.UserRepository;
import com.devopsintegration.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Override
    public void createUser(UserDto userDto) {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new UserAlreadyExist("User Already Exists With Email");
        }
        try {
            User user = modelMapper.map(userDto, User.class);
            userRepository.save(user);
        } catch (Exception exception) {
            throw new RuntimeException("Error occurred while saving user: " + exception.getMessage(), exception);
        }
    }

    @Override
    public UserDto getUserById(String id) {
        User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","User_Id",id));
        return modelMapper.map(user,UserDto.class);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> modelMapper.map(user,UserDto.class)).toList();
    }

    @Override
    public void deleteUser(String id) {
        User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","User_Id",id));
        userRepository.delete(user);
    }

    @Override
    public void updateUser(String id, UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","User_Id",id));
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        if (userDto.getAddress() != null){
            Address address = userDto.getAddress();
            user.setAddress(address);
        }
        userRepository.save(user);
        modelMapper.map(user, UserDto.class);c
    }
}