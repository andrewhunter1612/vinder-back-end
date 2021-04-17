package com.vinder.vinderbackend.repositories;

import com.vinder.vinderbackend.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

}
