package com.cookiepaper.service;

import com.cookiepaper.dto.OvenDto;
import com.cookiepaper.entity.Oven;
import com.cookiepaper.repository.OvenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class OvenServiceImpl implements OvenService {

    private final OvenRepository ovenRepository;

    public OvenServiceImpl(OvenRepository ovenRepository) {
        this.ovenRepository = ovenRepository;
    }

    // 오븐 생성
    @Override
    @Transactional
    public Oven createOven(OvenDto ovenDto) throws Exception {
        Oven newOven = Oven.builder()
                .ovDesign(ovenDto.getOvDesign())
                .ovPrivateYn(ovenDto.getOvPrivateYn())
                .build();

        return ovenRepository.save(newOven);
    }

    // 오븐 목록 조회
    @Override
    public List<OvenDto> ovenList() throws Exception {
        List<Oven> ovenList = ovenRepository.findAll();
        List<OvenDto> ovenDtoList = new ArrayList<>();

        for (int i = 0; i < ovenList.size(); i++) {
            ovenDtoList.add(new OvenDto(ovenList.get(i).getOvId(),
                    ovenList.get(i).getUser().getUsId(),
                    ovenList.get(i).getUser().getUsNickname(),
                    ovenList.get(i).getOvDesign(),
                    ovenList.get(i).getOvPrivateYn()
            ));
        }

        return ovenDtoList;
    }

    // 오븐 상세보기
    @Override
    public Oven ovenDetails(String usId) throws Exception {
        return ovenRepository.getByUser(usId);
    }

}
