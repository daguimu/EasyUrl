package com.dagm.shorter.req;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author Guimu
 * @date 2020/01/03
 */
@Getter
@Setter
@Accessors(chain = true)
public class AddShortRecReq {

    @NotNull(message = "URL cannot be empty.")
    private String url;

    @Min(30)
    @Max(30 * 365 * 24 * 60 * 60)
    private Integer expireTimes;
}