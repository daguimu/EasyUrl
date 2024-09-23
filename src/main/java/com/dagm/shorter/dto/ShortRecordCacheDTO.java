package com.dagm.shorter.dto;

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
public class ShortRecordCacheDTO {

    private Long id;
    private String shouterStr;
    private String originalUrl;
    /**
     * "Deadline."
     */
    private Date expireTime;

    @Override
    public String toString() {
        return "ShortRecordCacheDTO{" +
                "id=" + id +
                ", shouterStr='" + shouterStr + '\'' +
                ", originalUrl='" + originalUrl + '\'' +
                ", expires=" + expireTime +
                '}';
    }
}