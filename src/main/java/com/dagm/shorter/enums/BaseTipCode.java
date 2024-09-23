package com.dagm.shorter.enums;

import com.dagm.shorter.common.BaseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Guimu
 */
@AllArgsConstructor
@Getter
public enum BaseTipCode implements BaseCode {
    /**
     * Operation successful.
     */
    SUCCESS("0000", "Operation successful."),
    /**
     * 操作失败
     */
    FAILURE("9999", "Operation failed."),
    ;
    private final String code;
    private final String msg;
}