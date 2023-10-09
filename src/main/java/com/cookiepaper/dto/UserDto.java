package com.cookiepaper.dto;

import com.sun.istack.NotNull;
import lombok.*;

@Getter @Setter
@Data
public class UserDto {

    @NotNull
    private String usId;

    @NotNull
    private String usPassword;

    @NotNull
    private String usNickname;

    @NotNull
    private String usEmail;

    public UserDto(String usId, String usPassword, String usNickname, String usEmail) {
        this.usId = usId;
        this.usPassword = usPassword;
        this.usNickname = usNickname;
        this.usEmail = usEmail;
    }

}
