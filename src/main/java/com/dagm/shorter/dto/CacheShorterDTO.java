/*
 * Copyright (c) 2024 ly.com
 * All rights reserved.
 *
 */
package com.dagm.shorter.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Guimu
 * @date 2024/09/23
 */
@Getter
@Setter
public class CacheShorterDTO {
    private ShortRecordCacheDTO shortRecord;
    private Long expire;

    public CacheShorterDTO() {
    }

    public CacheShorterDTO(ShortRecordCacheDTO shortRecord, Long expire) {
        this.shortRecord = shortRecord;
        this.expire = expire;
    }



    @Override
    public String toString() {
        return "CacheShorterDTO{" +
                "shortRecord=" + shortRecord +
                ", expire=" + expire +
                '}';
    }
}