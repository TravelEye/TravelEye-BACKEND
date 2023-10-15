package com.service.traveleye.domain.user.repository;

import com.service.traveleye.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
