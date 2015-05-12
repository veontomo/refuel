package com.veontomo.refuel;

import android.content.ContentValues;

import org.json.JSONObject;

/**
 * Interface to save content of Active Records.
 * @since 0.1
 * @author veontomo@gmail.com
 */
public interface IStorage {

    /**
     * Saves record in the target with information stored in data.
     *
     * Returns true in case of success and false  otherwise.
     * @param target table name or file name
     * @param data updated data
     * @return id number under which the record is saved, or null in case of failure
     * @since 0.1
     */
    public Long save(String target, ContentValues data);

    /**
     * Updates record identified by a number id in the target with information stored in data.
     *
     * Returns true in case of success and false  otherwise.
     * @param target table name or file name
     * @param id record number to be updated
     * @param data updated data
     * @return true in case of success and false otherwise.
     * @since 0.1
     */
    Boolean update(String target, Long id, ContentValues data);
}
