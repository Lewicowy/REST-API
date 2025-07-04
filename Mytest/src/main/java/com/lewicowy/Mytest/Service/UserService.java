package com.lewicowy.Mytest.Service;

import com.lewicowy.Mytest.DTO.UserRequest;
import com.lewicowy.Mytest.DTO.UserResponse;
import com.lewicowy.Mytest.entity.Role;
import com.lewicowy.Mytest.entity.User;
import com.lewicowy.Mytest.Exception.NotFoundException;
import com.lewicowy.Mytest.repository.RoleRepository;
import com.lewicowy.Mytest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "users")
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Transactional
    @CachePut(key = "#result.uuid")
    public UserResponse createUser(UserRequest request) {
        Role role = roleRepository.findByRoleName(request.getRoleName())
                .orElseGet(() -> roleRepository.save(new Role(request.getRoleName())));

        User user = new User();
        user.setFio(request.getFio());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setAvatar(request.getAvatar());
        user.setRole(role);

        return mapToResponse(userRepository.save(user));
    }

    @Cacheable(key = "#uuid")
    public UserResponse getUser(UUID uuid) {
        User user = userRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException("User not found"));
        return mapToResponse(user);
    }

    @Transactional
    @CachePut(key = "#uuid")
    public UserResponse updateUser(UUID uuid, UserRequest request) {
        User user = userRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException("User not found"));

        user.setFio(request.getFio());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setAvatar(request.getAvatar());

        if (request.getRoleName() != null) {
            Role role = roleRepository.findByRoleName(request.getRoleName())
                    .orElseGet(() -> roleRepository.save(new Role(request.getRoleName())));
            user.setRole(role);
        }

        return mapToResponse(userRepository.save(user));
    }

    @Transactional
    @CacheEvict(key = "#uuid")
    public void deleteUser(UUID uuid) {
        User user = userRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException("User not found"));
        userRepository.delete(user);
    }

    private UserResponse mapToResponse(User user) {
        return new UserResponse(
                user.getUuid(),
                user.getFio(),
                user.getPhoneNumber(),
                user.getAvatar(),
                user.getRole().getRoleName()
        );
    }
}