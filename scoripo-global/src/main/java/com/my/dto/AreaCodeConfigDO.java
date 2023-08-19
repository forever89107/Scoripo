package com.my.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AreaCodeConfigDO implements Serializable {

    private String code;

    private String zhName;

    private String enName;

    private String vietnamName;

    private String thaiName;

    private String japaneseName;

    private String portugueseName;

    private String spanishName;

    private String arabicName;

    private Boolean isBackend;

    private Boolean isForehead;

}