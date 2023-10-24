package com.cookiepaper.dto;

import com.cookiepaper.entity.Cookie;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Data
@NoArgsConstructor
public class CookieListDto {

    private List<CookieDto> cookieDtoList = new ArrayList<>();

    private Long totalPages;

    private Long totalCount;

    @Builder
    public CookieListDto(List<Cookie> cookieList, Long totalPages, Long totalCount) {
        this.cookieDtoList = cookieList.stream()
                .map(CookieDto::new).collect(Collectors.toList());
        this.totalPages = totalPages;
        this.totalCount = totalCount;
    }

}
