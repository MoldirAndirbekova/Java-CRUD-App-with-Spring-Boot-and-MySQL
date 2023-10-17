package com.moldir.api.rest.UserRepo;

import com.moldir.api.rest.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
