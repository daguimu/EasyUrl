package com.dagm.shorter.configuration;

import com.dagm.shorter.config.ShorterConfig;
import com.dagm.shorter.service.impl.SnowflakeIdGenImpl;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.stream.Stream;

/**
 * @author Guimu
 * @date 2020/01/02
 */
@Configuration
@Slf4j
public class ShorterConfiguration {

    @Resource
    private ShorterConfig shorterConfig;

    /**
     * Get the base conversion collection.
     *
     * @author Guimu
     * @date 2020/1/3
     */
    @Bean(name = "bitMap")
    public BiMap<Integer, String> createBitMap() {
        BiMap<Integer, String> biMap = HashBiMap.create(62);
        String[] biArray = shorterConfig.getBitStr().split("");
        Stream.iterate(0, x -> x + 1).limit(62).forEach(el -> biMap.put(el, biArray[el]));
        return biMap;
    }

    /**
     * Get the reversed base conversion collection.
     *
     * @author Guimu
     * @date 2020/1/3
     */
    @Bean(name = "inverseBitMap")
    public BiMap<String, Integer> createInverseBitMap() {
        return this.createBitMap().inverse();
    }


    /**
     * Return the distributed ID generator.
     *
     * @author Guimu
     * @date 2024/9/23
     */
    @Bean(name = "snowflakeIdGenerator")
    public SnowflakeIdGenImpl snowflakeIdGenerator() {
        return new SnowflakeIdGenImpl(0, 0);
    }
}