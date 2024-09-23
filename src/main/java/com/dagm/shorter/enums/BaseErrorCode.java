package com.dagm.shorter.enums;

import com.dagm.shorter.common.BaseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Guimu
 */
@AllArgsConstructor
@Getter
public enum BaseErrorCode implements BaseCode {

    /**
     * Parameter error.
     */
    PARAM_ERROR("9001", "Parameter error."),
    ;

    private final String code;
    private final String msg;

}