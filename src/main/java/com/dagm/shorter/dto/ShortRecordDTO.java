package com.dagm.shorter.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author Guimu
 * @date 2020/01/03
 */
@Getter
@Setter
@Accessors(chain = true)
public class ShortRecordDTO {

    private Long id;
    private String shouterStr;
    private String originalUrl;

    /**
     * Validity expiration time (in milliseconds).
     */
    private Long expires;
}