package com.cookiepaper.service;

import com.cookiepaper.dto.CookieDto;
import com.cookiepaper.dto.CookieListDto;
import com.cookiepaper.entity.Cookie;
import org.springframework.data.domain.Pageable;

public interface CookieService {

    // 쿠키 생성
    public Cookie createCookie(CookieDto cookieDto) throws Exception;

    // 쿠키 목록 조회
    public CookieListDto cookieList(Long ovId, Pageable pageable) throws Exception;

    // 쿠키 상세보기
    public Cookie cookieDetails(Long ckId) throws Exception;

}
