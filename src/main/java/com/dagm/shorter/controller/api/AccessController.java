/*
 * Copyright (c) 2020 dagm.com
 * All rights reserved.
 *
 */
package com.dagm.shorter.controller.api;

import com.dagm.shorter.service.ShorterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Guimu
 * @date 2020/01/04
 */
@RestController
@Slf4j
public class AccessController {

    @Resource
    private ShorterService shorterService;


    /**
     * @param shortCode : Unique code corresponding to the short link.
     * @author Guimu
     * @date 2024/9/23
     */
    @GetMapping(value = "/{shortCode:[0-9a-zA-Z]+}")
    public void accessUrl(@PathVariable(value = "shortCode") String shortCode,
                          HttpServletResponse response) throws IOException {
        String url = shorterService.backToLongStr(shortCode);
        response.sendRedirect(url);
    }
}