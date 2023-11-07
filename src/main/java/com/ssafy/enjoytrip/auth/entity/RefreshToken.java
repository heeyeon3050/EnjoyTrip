package com.ssafy.enjoytrip.auth.entity;

import com.ssafy.enjoytrip.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
@Entity
@Table(name = "refresh_token")
public class RefreshToken extends BaseEntity {

    @Id
    @Column(name = "user_id", nullable = false)
    private String userId;

    private String token;

    public void update(String token) {
        this.token = token;
    }

}
