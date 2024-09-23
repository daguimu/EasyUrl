package com.dagm.shorter.enums;

import com.dagm.shorter.common.BaseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Guimu
 * @date 2019/10/07
 */
@Getter
@AllArgsConstructor
public enum ShorterTipEnum implements BaseCode {

    /**
     * Validation failed.
     */
    URL_BIT_CHECK_ERROR("9007", "Validation failed."),

    /**
     * The URL format is incorrect. Please include a protocol, such as: http://www.dagm.com or https://www.dagm.com.
     */
    URL_FORMAT_ERROR("9010", "The URL format is incorrect. Please include a protocol, such as: http://www.dagm.com or https://www.dagm.com."),
    ;
    private final String code;

    private final String msg;
}