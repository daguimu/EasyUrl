package com.dagm.shorter.service.impl;

import com.dagm.shorter.adapter.ShorterAdapter;
import com.dagm.shorter.config.ShorterConfig;
import com.dagm.shorter.dto.ShortRecordCacheDTO;
import com.dagm.shorter.dto.ShortRecordDTO;
import com.dagm.shorter.mapper.ShorterRecordMapper;
import com.dagm.shorter.model.ShorterRecordPO;
import com.dagm.shorter.service.CacheClient;
import com.dagm.shorter.service.ShorterRecordService;
import com.dagm.shorter.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Guimu
 * @date 2020/01/03
 */
@Slf4j
@Service
public class ShorterRecordServiceImpl implements ShorterRecordService {

    @Resource
    private ShorterRecordMapper shorterRecordMapper;

    @Resource
    private CacheClient cacheClient;

    @Resource
    private ShorterConfig shorterConfig;

    @Override
    public boolean addRecord(ShortRecordDTO shortRecordDto) {
        ShorterRecordPO shorterRecordPo = ShorterAdapter.shorterDto2Po(shortRecordDto);
        if (shortRecordDto.getExpires() != null) {
            shorterRecordPo.setExpiredTime(DateUtils.localDateTimeToDate(LocalDateTime.now().plusSeconds(shortRecordDto.getExpires())));
        }
        int count = shorterRecordMapper.addRecord(shorterRecordPo);
        shortRecordDto.setId(shorterRecordPo.getId());
        return count > 0;
    }

    @Override
    public String getRecordByLeafId(Long leafId) {
        ShortRecordCacheDTO recordDto = cacheClient.get(leafId).get().getShortRecord();
        if (recordDto == null || StringUtils.isEmpty(recordDto.getOriginalUrl()) || (recordDto.getExpireTime() != null && recordDto.getExpireTime().before(new Date()))) {
            //If the short link record does not exist or has expired, return the default path.
            return shorterConfig.getDefaultUrl();
        }
        return recordDto.getOriginalUrl();
    }
}