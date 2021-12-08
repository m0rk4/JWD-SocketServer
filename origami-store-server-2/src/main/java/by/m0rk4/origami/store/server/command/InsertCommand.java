package by.m0rk4.origami.store.server.command;

import by.m0rk4.origami.store.server.file.FileApi;
import by.m0rk4.origami.store.server.entity.OrigamiRecord;
import by.m0rk4.origami.store.server.exception.FileException;

import java.io.PrintWriter;

public class InsertCommand implements Command {
    private final PrintWriter writer;
    private final FileApi fileApi;
    private final OrigamiRecord record;

    public InsertCommand(PrintWriter writer, FileApi fileApi, OrigamiRecord record) {
        this.writer = writer;
        this.fileApi = fileApi;
        this.record = record;
    }

    @Override
    public void execute() {
        try {
            fileApi.insertRecord(record);
            writer.println("New record inserted!");
        } catch (FileException e) {
            writer.println("New record insertion failed: " + e.getMessage());
        }
    }
}
