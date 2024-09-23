package com.dagm.shorter.exceptions;

import com.dagm.shorter.common.BaseCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Guimu
 * @date 2019/10/07
 */
@Getter
@Setter
public class CommonException extends RuntimeException {
    private String code;
    private String message;

    public CommonException() {
    }

    public CommonException(String code, String message) {
        super("code:" + code + " message:" + message);
        this.code = code;
        this.message = message;
    }

    public CommonException(BaseCode commonCodeEnum) {
        super("code:" + commonCodeEnum.getCode() + " message:" + commonCodeEnum.getMsg());
        this.code = commonCodeEnum.getCode();
        this.message = commonCodeEnum.getMsg();
    }
}