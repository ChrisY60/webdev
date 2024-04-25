package com.example.webdevuserservice.service.impl;

import com.example.webdevuserservice.DTO.NotificationDTO;
import com.example.webdevuserservice.DTO.UserRegisterDTO;
import com.example.webdevuserservice.DTO.UserResponseDTO;
import com.example.webdevuserservice.entity.User;
import com.example.webdevuserservice.producer.NotificationProducer;
import com.example.webdevuserservice.repository.UserRepository;
import com.example.webdevuserservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.webdevuserservice.mapper.UserMapper.USER_MAPPER;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final NotificationProducer notificationProducer;

    @Override
    public UserResponseDTO createUser(UserRegisterDTO userRegisterDTO) {
        if (userRepository.existsByUsername(userRegisterDTO.getUsername())) {
            throw new RuntimeException("Username already exists!");
        }
        if (userRepository.existsByEmail(userRegisterDTO.getEmail())) {
            throw new RuntimeException("Email already exists!");
        }

        User user = USER_MAPPER.fromRegisterDTO(userRegisterDTO);
        userRepository.save(user);

        return USER_MAPPER.toResponseDTO(user);
    }

    @Override
    public UserResponseDTO getUser(Long id) {
        return USER_MAPPER.toResponseDTO(
                userRepository.findById(id).
                        orElseThrow(
                                () -> new RuntimeException("User not found!")
                        )
        );
    }

    @Override
    public UserResponseDTO getUserByUsername(String username) {
        return USER_MAPPER.toResponseDTO(
                userRepository.findByUsername(username).
                        orElseThrow(
                                () -> new RuntimeException("User not found!")
                        )
        );

    }

    @Override
    public UserResponseDTO getUserByEmail(String email) {
        return USER_MAPPER.toResponseDTO(
                userRepository.findByEmail(email).
                        orElseThrow(
                                () -> new RuntimeException("User not found!")
                        )
        );
    }

    @Override
    public List<UserResponseDTO> getAllSubscribers(Long id) {
        return USER_MAPPER.toResponseDTOList(userRepository.findAllBySubscriptionsId(id));
    }

    @Override
    public void subscribe(Long id, Long subscriptionId) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found!"));
        User subscription = userRepository.findById(subscriptionId)
                .orElseThrow(() -> new RuntimeException("Subscription not found!"));

        user.getSubscriptions().add(subscription);
        userRepository.save(user);

        NotificationDTO notificationDTO = new NotificationDTO(
                subscriptionId, id, "New subscriber!"
        );
        notificationProducer.sendNotification("user-subscribe", notificationDTO);
    }

    @Override
    public void unsubscribe(Long id, Long subscriptionId) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found!"));
        User subscription = userRepository.findById(subscriptionId)
                .orElseThrow(() -> new RuntimeException("Subscription not found!"));

        user.getSubscriptions().remove(subscription);
        userRepository.save(user);
    }
}
