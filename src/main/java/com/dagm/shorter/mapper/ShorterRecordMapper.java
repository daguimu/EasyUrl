package com.dagm.shorter.mapper;

import com.dagm.shorter.model.ShorterRecordPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Guimu
 * @date 2019/07/25
 */
@Mapper
public interface ShorterRecordMapper {

    int addRecord(@Param("record") ShorterRecordPO record);


    ShorterRecordPO getShorterRecById(@Param("leafId") Long leafId);
}