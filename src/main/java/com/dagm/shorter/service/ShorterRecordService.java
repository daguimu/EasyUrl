package com.dagm.shorter.service;

import com.dagm.shorter.dto.ShortRecordDTO;

/**
 * @author Guimu
 * @date 2020/01/03
 */
public interface ShorterRecordService {

    /**
     * Add short link record to the database.
     *
     * @author Guimu
     * @date 2020/1/3
     */
    boolean addRecord(ShortRecordDTO shortRecordDto);

    /**
     * Verify the validity of the link record corresponding to the leafId.
     *
     * @author Guimu
     * @date 2020/1/4
     */
    String getRecordByLeafId(Long leafId);
}
