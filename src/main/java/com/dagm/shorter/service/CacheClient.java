package com.dagm.shorter.service;

import com.dagm.shorter.adapter.ShorterAdapter;
import com.dagm.shorter.dto.CacheShorterDTO;
import com.dagm.shorter.dto.ShortRecordCacheDTO;
import com.dagm.shorter.mapper.ShorterRecordMapper;
import com.dagm.shorter.model.ShorterRecordPO;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author cm41643
 * @date 2017/2/8
 */
@Service
public class CacheClient {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private ShorterRecordMapper shorterRecordMapper;


    /**
     * Cache expiration time configuration, in milliseconds (3 minutes).
     */
    private static final int EXPIRE_TIME_MILLS = 180 * 1000;

    public final LoadingCache<Long, CacheShorterDTO> RECORD_INFO_CACHE = CacheBuilder.newBuilder()
            .maximumSize(3000)
            .expireAfterWrite(3, TimeUnit.MINUTES)
            .build(new CacheLoader<Long, CacheShorterDTO>() {

                @Override
                public CacheShorterDTO load(Long key) {
                    CacheShorterDTO cacheShorterDTO = new CacheShorterDTO();
                    ShorterRecordPO shorterRecordPO = shorterRecordMapper.getShorterRecById(key);
                    ShortRecordCacheDTO shortRecordCacheDTO = ShorterAdapter.shortPo2Dto(shorterRecordPO);

                    if (shortRecordCacheDTO != null) {
                        cacheShorterDTO.setShortRecord(shortRecordCacheDTO);
                        cacheShorterDTO.setExpire(System.currentTimeMillis() + EXPIRE_TIME_MILLS);
                    }

                    return shortRecordCacheDTO == null ? new CacheShorterDTO() : cacheShorterDTO;
                }
            });


    public Optional<CacheShorterDTO> get(Long key) {
        try {
            return Optional.of(RECORD_INFO_CACHE.get(key));
        } catch (ExecutionException e) {
            logger.error("get occur a error.", e);
            return Optional.of(new CacheShorterDTO());
        }
    }
}