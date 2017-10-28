package com.techprimers.jpa.springjpahibernateexample.repository;

import com.techprimers.jpa.springjpahibernateexample.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UsersRepository extends JpaRepository<Users, UUID> {
    List<Users> findByName(String name);

}
