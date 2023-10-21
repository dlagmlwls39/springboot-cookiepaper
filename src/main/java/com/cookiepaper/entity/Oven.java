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
    @Column(name = "ov_design")
    private int ovDesign;
    @Column(name = "ov_private_yn")
    private int ovPrivateYn;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "us_id", name = "us_id")
    private User user;

    public Oven() {
    }

    @Builder
    public Oven(int ovDesign, int ovPrivateYn) {
        this.ovDesign = ovDesign;
        this.ovPrivateYn = ovPrivateYn;
    }

}
