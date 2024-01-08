package com.example.instagram.Service;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.instagram.Exception.UserNotFoundException;
import com.example.instagram.Model.User;
import com.example.instagram.Repository.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository repo;
    static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User createUser(User user) {
        String codedPass = encoder.encode(user.getPassword());
        user.setPassword(codedPass);
        return repo.save(user);
    }

    public User updateUser(String username, Map<Object, Object> updatedProperties) throws Exception {
        Optional<User> userWrapper = repo.findById(username);
        if (userWrapper.isPresent()) {
            User user = userWrapper.get();
            for (Object key : updatedProperties.keySet()) {
                Field field = user.getClass().getDeclaredField((String) key);
                field.setAccessible(true);
                String val = (String) updatedProperties.get(key);
                if (((String) key).equals("password")) {
                    val = encoder.encode(val);
                }
                field.set(user, val);
            }
            return repo.save(user);
        } else
            throw new UserNotFoundException();
    }

    public Boolean authenticate(String username, String password) {
        Optional<User> userWrapper = repo.findById(username);
        if (userWrapper.isPresent()) {
            User user = userWrapper.get();
            if (encoder.matches(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public String validateUsername(String username) {
        if (repo.findById(username).isPresent()) {
            return "Invalid User";
        } else {
            return "Valid User";
        }
    }

    public Boolean validateMobile(String mobile) {
        User sample = new User();
        sample.setAllPropertiesNull();
        sample.setMobile(mobile);
        Example<User> example = Example.of(sample);
        Optional<User> userWrapper = repo.findOne(example);
        if (userWrapper.isPresent()) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean validateEmail(String email) {
        User sample = new User();
        sample.setAllPropertiesNull();
        sample.setEmail(email);
        Example<User> example = Example.of(sample);
        if (repo.findOne(example).isPresent())
            return false;
        return true;
    }

    public User getUser(String username) throws Exception {
        Optional<User> userWrapper = repo.findById(username);
        if (userWrapper.isPresent())
            return userWrapper.get();
        else
            throw new UserNotFoundException();
    }

    public User updateProfileImage(String username, String imageLink) throws Exception {
        Optional<User> userWrapper = repo.findById(username);
        if (userWrapper.isPresent()) {
            User user = userWrapper.get();
            user.setProfilePictureUrl(imageLink);
            repo.save(user);
            return user;
        } else {
            throw new UserNotFoundException();
        }
    }

    public User removeProfileImage(String username) throws Exception {
        Optional<User> userWrapper = repo.findById(username);
        if (userWrapper.isPresent()) {
            User user = userWrapper.get();
            user.setProfilePictureUrl(null);
            repo.save(user);
            return user;
        } else {
            throw new UserNotFoundException();
        }
    }
}
