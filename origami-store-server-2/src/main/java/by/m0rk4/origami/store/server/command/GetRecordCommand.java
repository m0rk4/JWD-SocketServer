package by.m0rk4.origami.store.server.command;

import by.m0rk4.origami.store.server.entity.OrigamiRecord;
import by.m0rk4.origami.store.server.exception.FileException;
import by.m0rk4.origami.store.server.file.FileApi;

import java.io.PrintWriter;
import java.util.Optional;

public class GetRecordCommand implements Command {
    private final PrintWriter writer;
    private final FileApi fileApi;
    private final String name;

    public GetRecordCommand(PrintWriter writer, FileApi fileApi, String name) {
        this.writer = writer;
        this.fileApi = fileApi;
        this.name = name;
    }

    @Override
    public void execute() {
        try {
            Optional<OrigamiRecord> recordByName = fileApi.getRecordByName(name);
            if (recordByName.isEmpty()) {
                writer.println("No records by name " + name + " found!");
                return;
            }
            writer.println("Record found: " + recordByName.get());
        } catch (FileException e) {
            writer.println("Fetching of the record failed with " + e.getMessage());
        }
    }
}
