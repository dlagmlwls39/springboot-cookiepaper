package com.cookiepaper.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
@ToString
@Table(name="tb_oven")
public class Oven {

    @Id
    @Column(name = "ov_id")
    private int ovId;
    @Column(name = "us_id")
    private String usId;
    @Column(name = "ov_design")
    private int ovDesign;
    @Column(name = "ov_private_yn")
    private int ovPrivateYn;

    public Oven() {
    }

    @Builder
    public Oven(int ovId, String usId, int ovDesign, int ovPrivateYn) {
        this.ovId = ovId;
        this.usId = usId;
        this.ovDesign = ovDesign;
        this.ovPrivateYn = ovPrivateYn;
    }
}
