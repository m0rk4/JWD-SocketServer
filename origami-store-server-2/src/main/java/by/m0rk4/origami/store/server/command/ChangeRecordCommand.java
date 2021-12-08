package by.m0rk4.origami.store.server.command;


import by.m0rk4.origami.store.server.entity.OrigamiRecord;
import by.m0rk4.origami.store.server.exception.FileException;
import by.m0rk4.origami.store.server.file.FileApi;

import java.io.PrintWriter;

public class ChangeRecordCommand implements Command {
    private final PrintWriter writer;
    private final FileApi fileApi;
    private final OrigamiRecord newOrigamiRecord;

    public ChangeRecordCommand(PrintWriter writer, FileApi fileApi, OrigamiRecord newOrigamiRecord) {
        this.writer = writer;
        this.fileApi = fileApi;
        this.newOrigamiRecord = newOrigamiRecord;
    }

    @Override
    public void execute() {
        try {
            fileApi.changeRecord(newOrigamiRecord);
            writer.println("Record replaced successfully.");
        } catch (FileException e) {
            writer.println("Record replacing failed with: " + e.getMessage());
        }
    }
}
