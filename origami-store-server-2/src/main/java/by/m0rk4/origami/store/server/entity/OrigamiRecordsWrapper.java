package by.m0rk4.origami.store.server.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OrigamiRecordsWrapper {
    private final List<OrigamiRecord> origamiRecords;

    @JsonCreator
    public OrigamiRecordsWrapper(
            @JsonProperty("origamiRecords") List<OrigamiRecord> origamiRecords) {
        this.origamiRecords = origamiRecords;
    }

    public List<OrigamiRecord> getOrigamiRecords() {
        return origamiRecords;
    }
}
