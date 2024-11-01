package com.example.noteTaker.dao;

import com.example.noteTaker.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.parser.Entity;


@Repository
public interface UserDAO extends JpaRepository<UserEntity,String> {
    UserEntity getUserEntityByUserId(String userId);

}
