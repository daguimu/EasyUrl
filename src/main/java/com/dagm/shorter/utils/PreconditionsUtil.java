package com.dagm.shorter.utils;

import com.dagm.shorter.common.BaseCode;
import com.dagm.shorter.exceptions.CommonException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author guimu
 */
@Slf4j
public class PreconditionsUtil {

    /**
     * Expression validation.
     *
     * @param expression Expression to be validated.
     * @param code Response code.
     */
    public static void checkArgument(boolean expression, BaseCode code) {
        if (!expression) {
            log.info("Throw business exception, code: [{}], errMsg: [{}]", code.getCode(), code.getMsg());
            throw new CommonException(code.getCode(), String.valueOf(code.getMsg()));
        }
    }
}