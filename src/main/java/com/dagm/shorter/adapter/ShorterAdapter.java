package com.dagm.shorter.adapter;

import com.dagm.shorter.dto.ShortRecordCacheDTO;
import com.dagm.shorter.dto.ShortRecordDTO;
import com.dagm.shorter.model.ShorterRecordPO;

/**
 * @author: Guimu
 * @date 2020/01/03
 */
public class ShorterAdapter {

    public static ShorterRecordPO shorterDto2Po(ShortRecordDTO shortRecordDto) {
        if (shortRecordDto == null) {
            return null;
        }
        ShorterRecordPO shorterRecordPO = new ShorterRecordPO();
        shorterRecordPO.setId(shortRecordDto.getId());
        shorterRecordPO.setShouterStr(shortRecordDto.getShouterStr());
        shorterRecordPO.setOriginalUrl(shortRecordDto.getOriginalUrl());

        return shorterRecordPO;
    }

    public static ShortRecordCacheDTO shortPo2Dto(ShorterRecordPO shorterRecordPo) {
        if (shorterRecordPo == null) {
            return null;
        }
        ShortRecordCacheDTO shortRecordCacheDTO = new ShortRecordCacheDTO();
        shortRecordCacheDTO.setId(shorterRecordPo.getId());
        shortRecordCacheDTO.setExpireTime(shorterRecordPo.getExpiredTime());
        shortRecordCacheDTO.setShouterStr(shorterRecordPo.getShouterStr());
        shortRecordCacheDTO.setOriginalUrl(shorterRecordPo.getOriginalUrl());

        return shortRecordCacheDTO;
    }
}