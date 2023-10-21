package com.cookiepaper.repository;

import com.cookiepaper.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Long countByUsId(String usId);

    Long countByUsEmail(String usEmail);

    User getByUsEmail(String usEmail);

    Long countByUsIdAndUsEmail(String usId, String usEmail);

    User getByUsIdAndUsEmail(String usId, String usEmail);

}