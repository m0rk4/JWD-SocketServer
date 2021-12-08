package by.m0rk4.origami.store.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final String HOST = "localhost";
    private static final int PORT = 1111;
    private static final Scanner SCANNER = new Scanner(System.in);

    public void start() {
        try (Socket socket = new Socket(HOST, PORT)) {
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (SCANNER.hasNextLine()) {
                String line = SCANNER.nextLine();
                writer.println(line);
                String response = reader.readLine();
                System.out.println(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
