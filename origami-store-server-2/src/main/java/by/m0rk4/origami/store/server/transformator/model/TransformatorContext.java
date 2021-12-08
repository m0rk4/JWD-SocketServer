package by.m0rk4.origami.store.server.transformator.model;

import by.m0rk4.origami.store.server.file.FileApi;

import java.io.PrintWriter;

public class TransformatorContext {
    private final FileApi fileApi;
    private final PrintWriter writer;

    public TransformatorContext(FileApi fileApi, PrintWriter writer) {
        this.fileApi = fileApi;
        this.writer = writer;
    }

    public FileApi getFileApi() {
        return fileApi;
    }

    public PrintWriter getWriter() {
        return writer;
    }
}
