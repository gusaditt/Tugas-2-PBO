package server;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class Response {
    // Method untuk mendapatkan response header,
    // Mengatur Content type, mengirim respon ke client dan
    // mengatur status code, response length, dan menulis respon ke output stream
    public static void getResponse(HttpExchange exchange, String jsonObject, String[] path, String table, int statusCode) throws IOException{
        OutputStream outputstream = exchange.getResponseBody();
        exchange.getResponseHeaders().set("Content-Type","application/json");
        String response = jsonObject;
        exchange.sendResponseHeaders(statusCode,response.length());
        outputstream.write(response.getBytes());
        outputstream.flush();
        outputstream.close();
    }

    // Method untuk send response
    public static void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
        OutputStream outputStream = exchange.getResponseBody();
        exchange.sendResponseHeaders(statusCode, response.length());
        outputStream.write(response.getBytes());
        outputStream.flush();
        outputStream.close();
    }
}