package by.m0rk4.origami.store.server.server;

import by.m0rk4.origami.store.server.file.FileApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class OrigamiStoreServer {
    private static final int PORT = 1111;
    private final FileApi fileApi;

    public OrigamiStoreServer(FileApi fileApi) {
        this.fileApi = fileApi;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();

                PrintWriter clientWriter =
                        new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader clientReader =
                        new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));

                new ClientJob(clientWriter, clientReader, fileApi)
                        .start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
