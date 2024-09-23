package com.dagm.shorter.service;

/**
 * @author Guimu
 * @date 2020/01/03
 */
public interface ShorterService {

    /**
     * Convert the original long URL.
     *
     * @param longStr
     * @return java.lang.String
     * @author Guimu
     * @date 2024/9/23
     */
    String toBeShorter(String longStr,Integer expireSeconds);

    /**
     * Restore the short URL to the original URL.
     *
     * @param shortStr
     * @return java.lang.String
     * @author Guimu
     * @date 2024/9/23
     */
    String backToLongStr(String shortStr);
}
