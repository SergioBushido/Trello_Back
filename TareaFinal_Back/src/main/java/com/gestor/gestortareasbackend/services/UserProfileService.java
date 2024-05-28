package com.gestor.gestortareasbackend.services;

import com.gestor.gestortareasbackend.model.user.dto.ResponseUser;
import com.gestor.gestortareasbackend.model.user.dto.UserResponseMapper;
import com.gestor.gestortareasbackend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserProfileService {

    private UserRepository userRepository;
    private UserResponseMapper userResponseMapper;

    public ResponseUser getMyProfile() {
        String username = getCurrentUsername();
        return userResponseMapper.userToResponseUser(userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found")));
    }

    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new SecurityException("No user authenticated");
        }
        return authentication.getName();
    }
}

