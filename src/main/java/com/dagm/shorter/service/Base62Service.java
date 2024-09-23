package com.dagm.shorter.service;

import com.google.common.collect.BiMap;

/**
 * @author Guimu
 * @date  2020/01/04
 */
public interface Base62Service {

    String enBase62(long val, BiMap<Integer, String> biMap);

    long deBase62(String en62Str, BiMap<String, Integer> inverseBitMap);
}
