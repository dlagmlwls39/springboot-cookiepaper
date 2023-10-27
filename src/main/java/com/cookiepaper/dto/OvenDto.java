package com.cookiepaper.dto;

import com.sun.istack.NotNull;
import lombok.*;

@Getter @Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OvenDto {

    @NotNull
    private Long ovId;

    @NotNull
    private String usId;

    @NotNull
    private String usNickname;

    @NotNull
    private int ovDesign;

    @NotNull
    private int ovPrivateYn;

    public OvenDto(String usId, int ovDesign, int ovPrivateYn) {
        this.usId = usId;
        this.ovDesign = ovDesign;
        this.ovPrivateYn = ovPrivateYn;
    }

}
