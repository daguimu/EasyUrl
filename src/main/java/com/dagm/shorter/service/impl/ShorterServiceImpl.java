package com.dagm.shorter.service.impl;

import com.dagm.shorter.config.ShorterConfig;
import com.dagm.shorter.dto.ShortRecordDTO;
import com.dagm.shorter.service.BaseShorterService;
import com.dagm.shorter.service.BitCheckService;
import com.dagm.shorter.service.LeafGenService;
import com.dagm.shorter.service.ShorterRecordService;
import com.dagm.shorter.service.ShorterService;
import com.dagm.shorter.utils.PreconditionsUtil;
import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.dagm.shorter.enums.ShorterTipEnum.URL_BIT_CHECK_ERROR;
import static com.dagm.shorter.enums.ShorterTipEnum.URL_FORMAT_ERROR;

/**
 * @author Guimu
 * @date 2020/01/03
 */
@Slf4j
@Service
public class ShorterServiceImpl implements ShorterService {

    @Resource
    private ShorterConfig shorterConfig;

    @Resource
    private ShorterRecordService shorterRecordService;
    @Resource
    private BaseShorterService baseShorterService;
    @Resource
    private BitCheckService bitCheckService;
    @Resource
    private LeafGenService leafGenService;

    @Override
    public String toBeShorter(String longStr, Integer expireSeconds) {
        // Validate whether the longStr format meets the specifications to avoid invalid conversions.
        PreconditionsUtil.checkArgument(this.checkUrl(longStr), URL_FORMAT_ERROR);
        Long leafId = leafGenService.getLeafId();
        ShortRecordDTO shortRecordDto = new ShortRecordDTO()
                .setShouterStr(this.encodeLeafIdAndCheck(leafId))
                .setExpires(expireSeconds != null ? (System.currentTimeMillis() + System.currentTimeMillis() * 1000) : null)
                .setId(leafId).setOriginalUrl(longStr);
        shorterRecordService.addRecord(shortRecordDto);
        log.info("Add short URL, longStr: [{}], shorterStr: [{}]", longStr, shortRecordDto.getShouterStr());
        return shorterConfig.getBaseUrl() + shortRecordDto.getShouterStr();
    }

    @Override
    public String backToLongStr(String shortStr) {
        // Obtain the checksum of the short code.
        String bitCode = shortStr.substring(shortStr.length() - 1);
        // Obtain the valid bit string of the short code.
        String originStr = shortStr.substring(0, shortStr.length() - 1);
        // Decode the short code to obtain the original leafId.
        long leafId = baseShorterService.deCodeBase62(originStr);
        // Obtain the bit checksum value for the received leafId.
        String code = bitCheckService.getCheckBitByOriginVal(String.valueOf(leafId));
        if (!StringUtils.equals(bitCode, code)) {
            log.info("msg:[{}] bitCode: [{}]", URL_BIT_CHECK_ERROR.getMsg(), bitCode);
            return shorterConfig.getDefaultUrl();
        }
        return shorterRecordService.getRecordByLeafId(leafId);
    }

    /**
     * Validate whether the longUrl format meets the specifications.
     *
     * @author Guimu
     * @date 2020/1/3
     */
    private boolean checkUrl(String longUrl) {
        return StringUtils.isNotEmpty(longUrl) && (longUrl.startsWith("http://") || longUrl
                .startsWith("https://"));
    }

    /**
     * Encode the leafId and add the checksum.
     */
    private String encodeLeafIdAndCheck(Long leafId) {
        // Obtain the 62-base string corresponding to the leafId.
        String leafBase62Str = baseShorterService.enCodeBase62(leafId);
        // Obtain the bit checksum value for the leafId.
        String code = bitCheckService.getCheckBitByOriginVal(String.valueOf(leafId));
        return leafBase62Str + code;
    }
}