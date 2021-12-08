package by.m0rk4.origami.store.server.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class OrigamiRecord {
    private final String name;
    private final int stepsCount;

    @JsonCreator
    public OrigamiRecord(
            @JsonProperty("name") String name,
            @JsonProperty("stepsCount") int stepsCount) {
        this.name = name;
        this.stepsCount = stepsCount;
    }

    public String getName() {
        return name;
    }

    public int getStepsCount() {
        return stepsCount;
    }

    @Override
    public String toString() {
        return "OrigamiRecord{" +
                "name='" + name + '\'' +
                ", stepsCount=" + stepsCount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrigamiRecord that = (OrigamiRecord) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
