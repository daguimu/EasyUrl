package com.dagm.shorter.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author Guimu
 * @date 2020/01/03
 */
@Getter
@Setter
@Accessors(chain = true)
public class ShorterRecordPO {

    private Long id;
    private String shouterStr;
    private String originalUrl;
    private Date addTime;
    private Date modTime;
    private Date expiredTime;
}