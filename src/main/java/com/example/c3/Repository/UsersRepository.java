package com.example.c3.Repository;


import com.example.c3.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface UsersRepository extends JpaRepository<Users, Integer> {

    Users findUsersById(Integer id);




}
