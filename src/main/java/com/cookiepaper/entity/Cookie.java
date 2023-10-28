package com.cookiepaper.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Getter @Setter
@ToString
@Table(name="tb_cookie")
public class Cookie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ck_id")
    private Long ckId;
    @Column(name = "ov_id")
    private Long ovId;
    @Column(name = "ck_nickname")
    private String ckNickname;
    @Column(name = "ck_content")
    private String ckContent;
    @Column(name = "ck_design")
    private int ckDesign;
    @Column(name = "ck_private_yn")
    private int ckPrivateYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "ov_id", name = "ov_id", insertable = false, updatable = false)
    @JsonIgnore
    private Oven oven;

    public Cookie() {
    }

    @Builder
    public Cookie(Long ovId, String ckNickname, String ckContent, int ckDesign, int ckPrivateYn) {
        this.ovId = ovId;
        this.ckNickname = ckNickname;
        this.ckContent = ckContent;
        this.ckDesign = ckDesign;
        this.ckPrivateYn = ckPrivateYn;
    }

}
