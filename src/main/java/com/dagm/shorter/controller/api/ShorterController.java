package com.dagm.shorter.controller.api;

import com.dagm.shorter.config.ShorterConfig;
import com.dagm.shorter.dto.BaseResult;
import com.dagm.shorter.req.AddShortRecReq;
import com.dagm.shorter.service.ShorterService;
import com.dagm.shorter.utils.PreconditionsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

import static com.dagm.shorter.enums.BaseErrorCode.PARAM_ERROR;


/**
 * @author Guimu
 * @date 2019/10/07
 */
@RestController
@RequestMapping(value = "/api")
@Slf4j
public class ShorterController {


    @Resource
    private ShorterService shorterService;

    @Resource
    private ShorterConfig shorterConfig;

    @PostMapping(value = "simplify")
    public BaseResult<String> simplify(@RequestBody @Valid AddShortRecReq shortRecReq) {
        return BaseResult.generateSuccessResult(shorterService.toBeShorter(shortRecReq.getUrl(),shortRecReq.getExpireTimes()));
    }

    @PostMapping(value = "restore")
    public BaseResult<String> restore(@RequestBody @Valid AddShortRecReq recReq) {
        // 去除前后的空格
        recReq.setUrl(recReq.getUrl().trim());
        int len = shorterConfig.getBaseUrl().length();
        PreconditionsUtil.checkArgument(recReq.getUrl().length() > len, PARAM_ERROR);
        String shortUrl = recReq.getUrl().substring(len);
        String url = shorterService.backToLongStr(shortUrl);
        return BaseResult.generateSuccessResult(url);
    }
}