package com.cookiepaper.controller;

import com.cookiepaper.dto.OvenDto;
import com.cookiepaper.service.OvenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/oven/*")
@CrossOrigin(origins = "http://15.165.55.131/:80")
public class OvenController {

    @Autowired
    OvenService ovenService;

    // 오븐 생성
    @PostMapping("create")
    public @ResponseBody ResponseEntity createOven(OvenDto ovenDto) throws Exception {
        return new ResponseEntity<>(ovenService.createOven(ovenDto), HttpStatus.OK);
    }

    // 오븐 목록 조회
    @GetMapping("list")
    public @ResponseBody ResponseEntity ovenList() throws Exception {
        return new ResponseEntity<>(ovenService.ovenList(), HttpStatus.OK);
    }

    // 오븐 상세보기
    @GetMapping("details/{usId}")
    public @ResponseBody ResponseEntity ovenDetails(@PathVariable("usId") String usId) throws Exception {
        return new ResponseEntity<>(ovenService.ovenDetails(usId), HttpStatus.OK);
    }

}
