package com.cookiepaper.service;

import com.cookiepaper.dto.CookieDto;
import com.cookiepaper.dto.CookieListDto;
import com.cookiepaper.entity.Cookie;
import com.cookiepaper.repository.CookieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CookieServiceImpl implements CookieService {

    private final CookieRepository cookieRepository;

    public CookieServiceImpl(CookieRepository cookieRepository) {
        this.cookieRepository = cookieRepository;
    }

    // 쿠키 생성
    @Override
    @Transactional
    public Cookie createCookie(CookieDto cookieDto) throws Exception {
        Cookie newCookie = Cookie.builder()
                .ovId(cookieDto.getOvId())
                .ckNickname(cookieDto.getCkNickname())
                .ckContent(cookieDto.getCkContent())
                .ckDesign(cookieDto.getCkDesign())
                .ckPrivateYn(cookieDto.getCkPrivateYn())
                .build();
        return cookieRepository.save(newCookie);
    }

    // 쿠키 목록 조회
    @Override
    public CookieListDto cookieList(Long ovId, Pageable pageable) throws Exception {
        Page<Cookie> results = cookieRepository.findByOvIdOrderByCkIdDesc(ovId, pageable);

        System.out.println(results.getContent());

        CookieListDto cookieListDto = CookieListDto.builder()
                .cookieList(results.getContent())
                .totalPages((long)results.getTotalPages())
                .totalCount(results.getTotalElements())
                .build();
        return cookieListDto;
    }

    // 쿠키 상세보기
    @Override
    public Cookie cookieDetails(Long ckId) throws Exception {
        return cookieRepository.getByCkId(ckId);
    }
}
