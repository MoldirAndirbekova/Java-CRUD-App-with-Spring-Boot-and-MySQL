package com.moldir.api.rest.user;

import com.moldir.api.rest.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    public Long countById(Long id);
}
