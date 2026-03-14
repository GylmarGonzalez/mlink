package com.mlink.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.mlink.entities.Role;
import com.mlink.entities.User;
import com.mlink.repository.RoleRepository;
import com.mlink.repository.UserRepository;
import com.mlink.request.UserReq;

import com.mlink.services.impl.CustomUserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.*;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private MessageSource messageSource;

    @InjectMocks
    private CustomUserDetailsService service;

    @BeforeEach
    void setup() {
        service.setMessageSource(messageSource);
    }

    @Test
    void testLoadUserByUsernameSuccess() {

        Role role = new Role();
        role.setName("ROLE_USER");

        Set<Role> roles = new HashSet<>();
        roles.add(role);

        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        user.setRoles(roles);

        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));

        var result = service.loadUserByUsername("testuser");

        assertEquals("testuser", result.getUsername());
        assertEquals(1, result.getAuthorities().size());
    }

    @Test
    void testLoadUserByUsernameNotFound() {

        when(userRepository.findByUsername("missing")).thenReturn(Optional.empty());
        when(messageSource.getMessage(anyString(), any(), any(Locale.class)))
                .thenReturn("User not found");

        assertThrows(UsernameNotFoundException.class,
                () -> service.loadUserByUsername("missing"));
    }

    @Test
    void testRegisterUserSuccess() {

        UserReq req = new UserReq();
        req.setUsername("newuser");
        req.setPassword("123");
        req.setRoles(Set.of("ROLE_USER"));

        Role role = new Role();
        role.setName("ROLE_USER");

        when(userRepository.findByUsername("newuser")).thenReturn(Optional.empty());
        when(roleRepository.findByName("ROLE_USER")).thenReturn(Optional.of(role));
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArgument(0));

        User saved = service.registerUser(req);

        assertEquals("newuser", saved.getUsername());
        assertEquals(1, saved.getRoles().size());
    }

    @Test
    void testRegisterUserUsernameExists() {

        UserReq req = new UserReq();
        req.setUsername("existing");
        req.setPassword("123");

        when(userRepository.findByUsername("existing"))
                .thenReturn(Optional.of(new User()));

        when(messageSource.getMessage(anyString(), any(), any(Locale.class)))
                .thenReturn("User already exists");

        assertThrows(RuntimeException.class,
                () -> service.registerUser(req));
    }

    @Test
    void testRegisterUserRoleNotFound() {

        UserReq req = new UserReq();
        req.setUsername("user");
        req.setPassword("123");
        req.setRoles(Set.of("ROLE_ADMIN"));

        when(userRepository.findByUsername("user")).thenReturn(Optional.empty());
        when(roleRepository.findByName("ROLE_ADMIN")).thenReturn(Optional.empty());

        when(messageSource.getMessage(anyString(), any(), any(Locale.class)))
                .thenReturn("Role not found");

        assertThrows(RuntimeException.class,
                () -> service.registerUser(req));
    }
}