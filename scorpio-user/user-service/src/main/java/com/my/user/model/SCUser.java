package com.my.user.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sc_user")
public class SCUser implements Serializable {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "email")
    private String email;

    @Column(name = "sex")
    private Integer sex;

    @Column(name = "status")
    private Integer status;

    @Column(name = "head_url")
    private String head_url;

    @Column(name = "register_date")
    private Date registerDate;
}
