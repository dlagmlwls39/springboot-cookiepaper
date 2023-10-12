package com.cookiepaper.dto;

import com.sun.istack.NotNull;
import lombok.*;

@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OvenDto {

    @NotNull
    private Long ovId;

    @NotNull
    private String usId;

    @NotNull
    private int ovDesign;

    @NotNull
    private int ovPrivateYn;

}
