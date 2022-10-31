package com.pre21.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Entity
@Getter
@Table(name = "REFRESH_TOKEN")
@NoArgsConstructor
@AllArgsConstructor
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REFRESH_TOKEN_ID", nullable = false)
    private Long id;

    @Column(name = "TOKEN_VALUE", nullable = false)
    private String tokenValue;

    @Column(name = "TOKEN_EMAIL", nullable = false)
    private String tokenEmail;

    public RefreshToken(String refreshToken, String tokenEmail) {
        this.tokenValue = refreshToken;
        this.tokenEmail = tokenEmail;
    }
}
