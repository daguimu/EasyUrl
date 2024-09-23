package com.dagm.shorter.service;

/**
 * @author Guimu
 * @date  2020/01/04
 */
public interface BitCheckService {

    /**
     * Obtain the bit checksum based on the ciphertext value.
     *
     * @author Guimu
     * @date  2020/1/4
     */
    String getCheckBitByOriginVal(String originVal);
}
