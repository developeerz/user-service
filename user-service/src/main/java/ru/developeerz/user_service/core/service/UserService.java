package ru.developeerz.user_service.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.developeerz.user_service.api.user.model.JwtResponse;
import ru.developeerz.user_service.api.user.model.LoginRequest;
import ru.developeerz.user_service.api.user.model.RegistrationRequest;
import ru.developeerz.user_service.core.UserMapper;
import ru.developeerz.user_service.core.entity.AuthorityId;
import ru.developeerz.user_service.core.entity.User;
import ru.developeerz.user_service.core.repository.UserRepository;
import ru.developeerz.user_service.core.repository.UserAuthorityRepository;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserAuthorityRepository userAuthorityRepository;

    private final PasswordUtil passwordUtil;

    private final JwtProvider jwtProvider;

    private final UserMapper userMapper;

    public ResponseEntity<JwtResponse> loginUser(LoginRequest request) {
        Optional<User> optUser = userRepository.findByEmail(request.email());
        User user = optUser.orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordUtil.isMatch(request.password(), user.getHashedPassword())) {
            throw new RuntimeException("Wrong password");
        }

        Set<AuthorityId> authorities = userAuthorityRepository.findAuthorityByUserId(user.getId());

        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setAccessToken(jwtProvider.generateAccessToken(user.getId(), authorities));
        jwtResponse.setRefreshToken(jwtProvider.generateRefreshToken(user.getId()));

        return ResponseEntity.ok(jwtResponse);
    }


    public void registrationUser(RegistrationRequest request) {
        // TODO check valid email

        User user = userMapper.map(request);
        userRepository.save(user);

        // TODO save verification code

        // TODO send verification code

        // maybe return refresh token
    }


    public void verifyUser(VerificationRequest request) {
        Optional<User> optUser = userRepository.findById(request.userId());
        User user = optUser.orElseThrow(() -> new RuntimeException("User not found"));

        // TODO get verification code

        // TODO compare verification code

        // TODO verify user
    }
}
