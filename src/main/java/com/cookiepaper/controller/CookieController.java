package com.cookiepaper.controller;

import com.cookiepaper.dto.CookieDto;
import com.cookiepaper.entity.Cookie;
import com.cookiepaper.service.CookieService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cookie/*")
@CrossOrigin(origins = { "http://localhost:3000", "http://15.165.55.131:80" })
public class CookieController {

    private final CookieService cookieService;

    public CookieController(CookieService cookieService) {
        this.cookieService = cookieService;
    }

    // 쿠키 생성
    @PostMapping("create")
    public @ResponseBody ResponseEntity createCookie(@RequestBody CookieDto cookieDto) throws Exception {
        Cookie cookie = cookieService.createCookie(cookieDto);
        return new ResponseEntity<>(cookie != null ? "success" : "fail", HttpStatus.OK);
    }

    // 쿠키 목록 조회
    @GetMapping("list")
    public @ResponseBody ResponseEntity cookieList(Long ovId, @PageableDefault(size = 9) Pageable pageable) throws Exception {
        return new ResponseEntity<>(cookieService.cookieList(ovId, pageable), HttpStatus.OK);
    }

    // 쿠키 상세보기
    @GetMapping("details/{ckId}")
    public @ResponseBody ResponseEntity cookieDetails(@PathVariable("ckId") Long ckId) throws Exception {
        return new ResponseEntity<>(cookieService.cookieDetails(ckId), HttpStatus.OK);
    }

}
