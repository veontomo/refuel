package com.veontomo.refuel;

import java.util.HashMap;

/**
 * Interface for objects that can be saved.
 * Created by Andrea on 12/05/2015.
 */
public interface ISavable {
    /**
     * Returns key-value pairs where key is a name of attribute to save and
     * value is a type of the key.
     * @return key-value string-valued pairs
     * @since 0.1
     */
    HashMap<String, String> getStructure();
}
