package com.cookiepaper.service;

import com.cookiepaper.dto.OvenDto;
import com.cookiepaper.entity.Oven;
import com.cookiepaper.repository.OvenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OvenServiceImpl implements OvenService {

    @Autowired
    OvenRepository ovenRepository;

    // 오븐 생성
    @Override
    @Transactional
    public Oven createOven(OvenDto ovenDto) throws Exception {
        Oven newOven = Oven.builder()
                .usId(ovenDto.getUsId())
                .ovDesign(ovenDto.getOvDesign())
                .ovPrivateYn(ovenDto.getOvPrivateYn())
                .build();

        return ovenRepository.save(newOven);
    }

    // 오븐 목록 조회
    @Override
    public List<Oven> ovenList() throws Exception {
        return ovenRepository.findAll();
    }

    // 오븐 상세보기
    @Override
    public Oven ovenDetails(String usId) throws Exception {
        return ovenRepository.getByUsId(usId);
    }

}
