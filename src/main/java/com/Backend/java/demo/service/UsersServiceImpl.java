package com.Backend.java.demo.service;

import com.Backend.java.demo.entity.Users;
import com.Backend.java.demo.payload.UsersDto;
import com.Backend.java.demo.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UsersDto createUser(UsersDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Users user=usersDtoToEntity(userDto);
        Users savedUser=repository.save(user);

        return EntityToUserDto(savedUser);
    }
    private Users usersDtoToEntity(UsersDto userDto){
        Users users=new Users();
        users.setName(userDto.getName());
        users.setPassword(userDto.getPassword());
        users.setId(userDto.getId());
        users.setEmail(userDto.getEmail());
        return users;
    }
    private UsersDto EntityToUserDto(Users savedUser){
        UsersDto userDTO=new UsersDto();
        userDTO.setEmail(savedUser.getEmail());
        userDTO.setName(savedUser.getName());
        userDTO.setId(savedUser.getId());
        userDTO.setPassword(savedUser.getPassword());
        return userDTO;
    }
}
