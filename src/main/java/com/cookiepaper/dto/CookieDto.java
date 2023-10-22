package com.cookiepaper.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CookieDto {

    @NotNull
    private Long ckId;

    @NotNull
    private Long ovId;

    @NotNull
    private String ckNickname;

    @NotNull
    private String ckContent;

    @NotNull
    private int ckDesign;

    @NotNull
    private int ckPrivateYn;

    public CookieDto(Long ovId, String ckNickname, String ckContent, int ckDesign, int ckPrivateYn) {
        this.ovId = ovId;
        this.ckNickname = ckNickname;
        this.ckContent = ckContent;
        this.ckDesign = ckDesign;
        this.ckPrivateYn = ckPrivateYn;
    }

}
