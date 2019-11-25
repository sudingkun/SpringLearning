package com.we.springboot.jpa;

import com.we.springboot.jpa.bean.User;
import com.we.springboot.jpa.dao.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
class JpaApplicationTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void findAll() {
        System.out.println(userRepository.findAll());
    }

    @Test
    public void findById() {
        System.out.println(userRepository.findById(1).orElse(null));
    }

    @Test
    public void deleteById() {
        userRepository.deleteById(18);
    }

    @Test
    public void update() {
        System.out.println(userRepository.save(new User(17,"testJpa17",22)));
    }

    @Test
    public void save() {
        List<User> users = new ArrayList<>();
        users.add(new User("u1", 11));
        users.add(new User("u2", 12));
        users.add(new User("u3", 13));
        System.out.println(userRepository.saveAll(users));
    }

    @Test
    public void query(){
        System.out.println(userRepository.findByNameLikeAndAgeBetween("%test%", 1, 20));
    }

}
