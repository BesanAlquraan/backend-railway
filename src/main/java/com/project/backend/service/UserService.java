package com.project.backend.service;

import com.project.backend.dto.UserRequestDTO;
import com.project.backend.dto.UserResponseDTO;

public interface UserService {
    UserResponseDTO register(UserRequestDTO request);
}
