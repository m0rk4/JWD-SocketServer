package by.m0rk4.origami.store.server;


import by.m0rk4.origami.store.server.file.impl.FileService;
import by.m0rk4.origami.store.server.server.OrigamiStoreServer;

import java.nio.file.Paths;

public class Starter {
    private static final String FILE_DATA_PATH = "./origami.xml";

    public static void main(String[] args) {
        new OrigamiStoreServer(new FileService(Paths.get(FILE_DATA_PATH))).start();
    }
}
