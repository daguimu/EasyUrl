package com.dagm.shorter.service;

/**
 * @author Guimu
 * @date  2020/01/03
 */
public interface BaseShorterService {

    /**
     * Perform short code encryption on the leafId.
     *
     * @author Guimu
     * @date 2020/1/3
     */
    String enCodeBase62(long leafId);

    /**
     * Perform short code decryption on the code.
     *
     * @author Guimu
     * @date  2020/1/3
     */
    long deCodeBase62(String code);

}
