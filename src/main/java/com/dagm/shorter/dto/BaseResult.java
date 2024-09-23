package com.dagm.shorter.dto;


import com.dagm.shorter.common.BaseCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import static com.dagm.shorter.enums.BaseTipCode.FAILURE;
import static com.dagm.shorter.enums.BaseTipCode.SUCCESS;

/**
 * @author Guimu
 * @date 2019/10/07
 */
@Getter
@Setter
@Accessors(chain = true)
public class BaseResult<T> {

    private Boolean success;
    private String code;
    private String msg;
    private T data;


    /**
     * Default successful BaseResult.
     *
     * @author Guimu
     * @date 2020/1/9
     */
    public static BaseResult<String> generateSuccessResult() {
        return new BaseResult<String>()
                .setSuccess(true)
                .setCode(SUCCESS.getCode())
                .setMsg(SUCCESS.getMsg());
    }

    /**
     * Construct the corresponding BaseResult based on BaseCode.
     *
     * @date 2020/1/9
     */
    public static BaseResult<String> generateFailureResult(BaseCode baseCode) {
        return new BaseResult<String>().setSuccess(false)
                .setCode(FAILURE.getCode())
                .setMsg(baseCode.getMsg());
    }

    /**
     * Construct the corresponding BaseResult based on errorMsg.
     *
     * @author Guimu
     * @date 2020/1/9
     */
    public static BaseResult<String> generateFailureResult(String errorMsg) {
        return new BaseResult<String>()
                .setSuccess(false)
                .setCode(FAILURE.getCode())
                .setMsg(errorMsg);
    }

    /**
     * Construct the corresponding BaseResult based on the data.
     *
     * @param data Data information for successful response.
     * @param <T>  t
     * @author Guimu
     * @date 2020/1/9
     */
    public static <T> BaseResult<T> generateSuccessResult(T data) {
        return new BaseResult<T>()
                .setSuccess(true).setCode(SUCCESS.getCode())
                .setMsg(SUCCESS.getMsg())
                .setData(data);
    }
}