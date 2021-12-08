package by.m0rk4.origami.store.server.server;

import by.m0rk4.origami.store.server.file.FileApi;
import by.m0rk4.origami.store.server.command.Command;
import by.m0rk4.origami.store.server.transformator.CommandTransformator;
import by.m0rk4.origami.store.server.transformator.impl.CommandTransformatorImpl;
import by.m0rk4.origami.store.server.transformator.model.TransformatorContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ClientJob extends Thread {
    private final PrintWriter printWriter;
    private final BufferedReader bufferedReader;
    private final CommandTransformator commandTransformator;

    public ClientJob(PrintWriter printWriter, BufferedReader bufferedReader, FileApi fileApi) {
        this.printWriter = printWriter;
        this.bufferedReader = bufferedReader;
        this.commandTransformator = new CommandTransformatorImpl(
                new TransformatorContext(
                        fileApi,
                        printWriter
                )
        );
    }

    @Override
    public void run() {
        try {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Command command = this.commandTransformator.transform(line);
                if (command == null) {
                    printWriter.println("Can't understand your query...");
                } else {
                    command.execute();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
