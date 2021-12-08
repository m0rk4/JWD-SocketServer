package by.m0rk4.origami.store.server.file.impl;


import by.m0rk4.origami.store.server.entity.OrigamiRecord;
import by.m0rk4.origami.store.server.entity.OrigamiRecordsWrapper;
import by.m0rk4.origami.store.server.exception.FileException;
import by.m0rk4.origami.store.server.file.FileApi;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

/**
 * Thread-safe file service
 */
public class FileService implements FileApi {
    private final Path filePath;
    private final ObjectMapper xmlMapper = new XmlMapper();

    public FileService(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public void insertRecord(OrigamiRecord record) throws FileException {
        try {
            OrigamiRecordsWrapper origamiRecordsWrapper =
                    xmlMapper.readValue(filePath.toFile(), OrigamiRecordsWrapper.class);
            List<OrigamiRecord> origamiRecords = origamiRecordsWrapper.getOrigamiRecords();
            origamiRecords.add(record);
            xmlMapper.writeValue(filePath.toFile(), origamiRecordsWrapper);
        } catch (IOException e) {
            throw new FileException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<OrigamiRecord> getRecordByName(String name) throws FileException {
        try {
            OrigamiRecordsWrapper origamiRecordsWrapper =
                    xmlMapper.readValue(filePath.toFile(), OrigamiRecordsWrapper.class);
            List<OrigamiRecord> origamiRecords = origamiRecordsWrapper.getOrigamiRecords();
            return origamiRecords.stream()
                    .filter(record -> record.getName().equals(name))
                    .findFirst();
        } catch (IOException e) {
            throw new FileException(e.getMessage(), e);
        }
    }

    @Override
    public void changeRecord(OrigamiRecord origamiRecord) throws FileException {
        try {
            OrigamiRecordsWrapper origamiRecordsWrapper =
                    xmlMapper.readValue(filePath.toFile(), OrigamiRecordsWrapper.class);
            List<OrigamiRecord> origamiRecords = origamiRecordsWrapper.getOrigamiRecords();
            int index = origamiRecords.indexOf(origamiRecord);
            if (index != -1) {
                origamiRecords.set(index, origamiRecord);
            }
            xmlMapper.writeValue(filePath.toFile(), origamiRecordsWrapper);
        } catch (IOException e) {
            throw new FileException(e.getMessage(), e);
        }
    }
}


