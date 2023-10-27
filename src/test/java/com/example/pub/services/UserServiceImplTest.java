package com.example.pub.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.pub.dtos.OrderDTO;
import com.example.pub.dtos.RegisterRequestDTO;
import com.example.pub.dtos.UserDTO;
import com.example.pub.dtos.UserProfileDTO;
import com.example.pub.models.ErrorMessage;
import com.example.pub.models.Order;
import com.example.pub.models.User;
import com.example.pub.repos.UserRepo;

import java.util.ArrayList;
import java.util.List;

import javax.naming.AuthenticationException;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

class UserServiceImplTest {

    @Test
    void testGetAllUsersDTO() {
        UserRepo userRepo = mock(UserRepo.class);
        when(userRepo.findAll()).thenReturn(new ArrayList<>());
        assertTrue((new UserServiceImpl(userRepo)).getAllUsersDTO().isEmpty());
        verify(userRepo).findAll();
    }

    @Test
    void testGetAllUsers() {
        UserRepo userRepo = mock(UserRepo.class);
        ArrayList<User> userList = new ArrayList<>();
        when(userRepo.findAll()).thenReturn(userList);
        List<User> actualAllUsers = (new UserServiceImpl(userRepo)).getAllUsers();
        assertSame(userList, actualAllUsers);
        assertTrue(actualAllUsers.isEmpty());
        verify(userRepo).findAll();
    }

    @Test
    void testFindUserById() {
        UserRepo userRepo = mock(UserRepo.class);
        when(userRepo.findUserById(Mockito.<Long>any())).thenReturn(new User("janedoe", "iloveyou"));
        assertTrue((new UserServiceImpl(userRepo)).findUserById(1L).isPresent());
        verify(userRepo).findUserById(Mockito.<Long>any());
    }

    @Test
    void testFindUserByName() {
        UserRepo userRepo = mock(UserRepo.class);
        when(userRepo.findUserByName(Mockito.<String>any())).thenReturn(new User("janedoe", "iloveyou"));
        assertTrue((new UserServiceImpl(userRepo)).findUserByName("Name").isPresent());
        verify(userRepo).findUserByName(Mockito.<String>any());
    }

    @Test
    void testIsUsernameInDatabase() {
        UserRepo userRepo = mock(UserRepo.class);
        when(userRepo.existsByUsername(Mockito.<String>any())).thenReturn(true);
        assertTrue((new UserServiceImpl(userRepo)).isUsernameInDatabase("janedoe"));
        verify(userRepo).existsByUsername(Mockito.<String>any());
    }

    @Test
    void testAddUser() throws AuthenticationException {
        UserRepo userRepo = mock(UserRepo.class);
        when(userRepo.existsByUsername(Mockito.<String>any())).thenThrow(new IllegalArgumentException("User created."));
        UserServiceImpl userServiceImpl = new UserServiceImpl(userRepo);
        assertThrows(IllegalArgumentException.class,
                () -> userServiceImpl.addUser(new RegisterRequestDTO("janedoe", "Name", true, "iloveyou")));
        verify(userRepo).existsByUsername(Mockito.<String>any());
    }
}

