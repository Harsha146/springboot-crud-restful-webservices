package com.in60minutes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in60minutes.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
