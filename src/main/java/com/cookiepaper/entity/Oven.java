package com.cookiepaper.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Getter @Setter
@ToString
@Table(name="tb_oven")
public class Oven {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ov_id")
    private Long ovId;
    @Column(name = "us_id")
    private String usId;
    @Column(name = "ov_design")
    private int ovDesign;
    @Column(name = "ov_private_yn")
    private int ovPrivateYn;

    public Oven() {
    }

    @Builder
    public Oven(String usId, int ovDesign, int ovPrivateYn) {
        this.usId = usId;
        this.ovDesign = ovDesign;
        this.ovPrivateYn = ovPrivateYn;
    }

}
