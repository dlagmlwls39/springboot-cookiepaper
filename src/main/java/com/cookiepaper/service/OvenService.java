package com.cookiepaper.service;

import com.cookiepaper.dto.OvenDto;
import com.cookiepaper.entity.Oven;

import java.util.List;

public interface OvenService {

    // 오븐 생성
    public Oven createOven(OvenDto ovenDto) throws Exception;

    // 오븐 목록 조회
    public List<OvenDto> ovenList() throws Exception;

    // 오븐 상세보기
    public OvenDto ovenDetails(String usId) throws Exception;


}
