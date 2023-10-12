package com.cookiepaper.repository;

import com.cookiepaper.entity.Oven;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OvenRepository extends JpaRepository<Oven, Integer> {

}