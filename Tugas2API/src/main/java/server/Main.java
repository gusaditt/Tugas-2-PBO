package server;

import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.HttpServer;
public class Main {
    public static void main(String[] args) throws IOException{
        try {
            HttpServer httpServer = HttpServer.create(new InetSocketAddress("localhost", 9057), 0);
            httpServer.createContext("/", new Server());
            httpServer.setExecutor(Executors.newSingleThreadExecutor());
            httpServer.start();
            System.out.println("Listening to Port 9057");
        } catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
