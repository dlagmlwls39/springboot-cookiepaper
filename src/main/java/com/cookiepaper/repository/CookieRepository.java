package com.cookiepaper.repository;

import com.cookiepaper.entity.Cookie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CookieRepository extends JpaRepository<Cookie, Long> {

    Page<Cookie> findByOvId(Long ovId, Pageable pageable);

    Cookie getByCkId(Long ckId);

}
