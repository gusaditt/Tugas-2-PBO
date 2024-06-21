package server;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class Response {

    public void getResponse(HttpExchange exchange, String jsonObject, String[] path, String table, int statusCode) throws IOException{
        OutputStream outputstream = exchange.getResponseBody();
        exchange.getResponseHeaders().set("Content-Type","application/json");
        String response = jsonObject;
        exchange.sendResponseHeaders(statusCode,response.length());
        outputstream.write(response.getBytes());
        outputstream.flush();
        outputstream.close();
    }

    public void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
        byte[] responseBytes = response.getBytes("UTF-8");
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8"); // Tambahkan Content-Type header
        exchange.sendResponseHeaders(statusCode, responseBytes.length);
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(responseBytes);
        outputStream.flush();
        outputStream.close();
    }
}
