package ru.job4j.io;

import org.checkerframework.checker.units.qual.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    static final String HELLO = "^.+\\?msg=Hello\\s.+$";
    static final String EXIT = "^.+\\?msg=Exit\\s.+$";
    static final String WHAT = "^.+\\?msg=What\\s.+$";
    static final String ANSWER = "HTTP/1.1 200 OK\r\n\r\n";

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {

                    String str = in.readLine();

                    if (str.matches(HELLO)) {
                        out.write(ANSWER.getBytes());
                        out.write("Hello".getBytes());
                    } else if (str.matches(EXIT)) {
                        out.write(ANSWER.getBytes());
                        out.write("Server shut down".getBytes());
                        server.close();
                    } else if (str.matches(WHAT)) {
                        out.write(ANSWER.getBytes());
                        out.write("What".getBytes());
                    } else {
                        out.write(ANSWER.getBytes());
                        out.write(str.getBytes());
                    }
                    out.flush();
                }
            }
        }
    }
}
