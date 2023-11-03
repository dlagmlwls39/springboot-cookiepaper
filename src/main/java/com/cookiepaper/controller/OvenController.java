package com.cookiepaper.controller;

import com.cookiepaper.dto.OvenDto;
import com.cookiepaper.entity.Oven;
import com.cookiepaper.service.OvenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/oven/*")
@CrossOrigin(origins = { "http://localhost:3000", "http://15.165.55.131:80", "https://cookie-paper.vercel.app" })
public class OvenController {

    private final OvenService ovenService;

    public OvenController(OvenService ovenService) {
        this.ovenService = ovenService;
    }

    // 오븐 생성
    @PostMapping("create")
    public @ResponseBody ResponseEntity createOven(@RequestBody OvenDto ovenDto) throws Exception {
        Oven oven = ovenService.createOven(ovenDto);
        return new ResponseEntity<>(oven != null ? oven : "fail", HttpStatus.OK);
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
