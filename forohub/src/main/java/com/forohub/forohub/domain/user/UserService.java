package com.forohub.forohub.domain.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserResponseDTO register(UserRegisterDTO userRegister){
        String pwdEncode=passwordEncoder.encode(userRegister.password());
        User user=new User(userRegister.name(),userRegister.last_name(),userRegister.username(),userRegister.email(),pwdEncode);

        return new UserResponseDTO(userRepository.save(user));
    }

}
