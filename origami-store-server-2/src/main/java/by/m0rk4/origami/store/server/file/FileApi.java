package by.m0rk4.origami.store.server.file;


import by.m0rk4.origami.store.server.entity.OrigamiRecord;
import by.m0rk4.origami.store.server.exception.FileException;

import java.util.Optional;

public interface FileApi {
    /**
     * Inserts specific record into file
     *
     * @param record record to insert
     */
    void insertRecord(OrigamiRecord record) throws FileException;

    /**
     * Gets record by name
     *
     * @param name name of the record
     * @return record with the given name
     */
    Optional<OrigamiRecord> getRecordByName(String name) throws FileException;

    /**
     * Changes record by given one
     *
     * @param record record to be changed
     */
    void changeRecord(OrigamiRecord record) throws FileException;
}
