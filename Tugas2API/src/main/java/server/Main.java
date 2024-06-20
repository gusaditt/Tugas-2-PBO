package server;

import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import static sun.net.www.protocol.http.AuthCacheValue.Type;

public class Main {
    public static void main(String[] args) throws IOException{
        try {
            HttpServer httpServer = HttpServer.create(new InetSocketAddress("localhost", 9057), 0);
            // context untuk endpoint "", dgn class untuk handle HTTP RequestHandler
            httpServer.createContext("/", new Server());
            httpServer.setExecutor(Executors.newSingleThreadExecutor());
            httpServer.start();
            System.out.println("Listening to Port 9057");
        } catch (Exception exception){
            exception.printStackTrace();
        }
    }
}