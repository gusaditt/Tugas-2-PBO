package server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;

import java.io.*;
import java.sql.SQLException;

public class Server implements HttpHandler {
    Response response = new Response();
    CustomerRequest customerRequest = new CustomerRequest();
    CardsRequest cardsRequest = new CardsRequest();
    itemsRequest itemsRequest = new itemsRequest();
    shippingAddressesRequest shippingAddressesRequest = new shippingAddressesRequest();
    subscriptionItemsRequest subscriptionItemsRequest = new subscriptionItemsRequest();
    subscriptionsRequest subscriptionsRequest = new subscriptionsRequest();
    String responses = null;

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String[] path = exchange.getRequestURI().getPath().split("/");
        String query = exchange.getRequestURI().getQuery();

        if ("GET".equals(exchange.getRequestMethod())) {
            if (path.length <= 1) {
                response.sendResponse(exchange, 200, "Database Connected!");
            }
            if ("customers".equals(path[1])) {
                JSONObject jsonCustomer = null;
                try {
                    jsonCustomer = customerRequest.getCustomer(path);
                    if (jsonCustomer != null)
                        response.getResponse(exchange, jsonCustomer.toString(), path, "Customer", 200);
                    else {
                        response.sendResponse(exchange, 404, "Not Found");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else if ("subscriptions".equals(path[1])) {
                JSONObject jsonSubscriptions = null;
                try {
                    jsonSubscriptions = subscriptionsRequest.getSubscriptions(query, path);
                    if (jsonSubscriptions != null)
                        response.getResponse(exchange, jsonSubscriptions.toString(), path, "subscriptions", 200);
                    else {
                        response.sendResponse(exchange, 404, "Not Found");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else if ("items".equals(path[1])) {
                JSONObject jsonItems = null;
                try {
                    jsonItems = itemsRequest.getItems(path);
                    if (jsonItems != null)
                        response.getResponse(exchange, jsonItems.toString(), path, "items", 200);
                    else {
                        response.sendResponse(exchange, 404, "Not Found");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }else if ("items?is_active=true".equals(path[1])) {
                JSONObject jsonItem = null;
                try {
                    jsonItem = itemsRequest.getItemStatus();
                    if (jsonItem != null)
                        response.getResponse(exchange, jsonItem.toString(), path, "items", 200);
                    else {
                        response.sendResponse(exchange, 404, "Not Found");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                responses = "404 ENTITY NOT FOUND";
                response.sendResponse(exchange, 400, responses);
            }
        } else if ("POST".equals(exchange.getRequestMethod())) {
            // POST
            if ("customers".equals(path[1])) {
                JSONObject jsonReqBody = parseRequestBody(exchange.getRequestBody());
                try {
                    responses = customerRequest.postCust(jsonReqBody);
                    response.sendResponse(exchange, 200, responses);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else if ("items".equals(path[1])) {
                JSONObject jsonReqBody = parseRequestBody(exchange.getRequestBody());
                try {
                    responses = itemsRequest.postItems(jsonReqBody);
                    response.sendResponse(exchange, 200, responses);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }else if ("subscriptions".equals(path[1])) {
                JSONObject jsonReqBody = parseRequestBody(exchange.getRequestBody());
                try {
                    responses = subscriptionsRequest.postSubscriptions(jsonReqBody);
                    response.sendResponse(exchange, 200, responses);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                responses = "404 ENTITY NOT FOUND";
                response.sendResponse(exchange, 400, responses);
            }
        }
        else if ("PUT".equals(exchange.getRequestMethod())) {
            if ("customers".equals(path[1])) {
                JSONObject jsonReqBody = parseRequestBody(exchange.getRequestBody());
                try {
                    responses = customerRequest.putCust(jsonReqBody, path);
                    response.sendResponse(exchange, 400, responses);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else if ("items".equals(path[1])) {
                JSONObject jsonReqBody = parseRequestBody(exchange.getRequestBody());
                try {
                    responses = itemsRequest.putItems(jsonReqBody, path);
                    response.sendResponse(exchange, 400, responses);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                responses = "404 ENTITY NOT FOUND";
                response.sendResponse(exchange, 400, responses);
            }
        }
        else if ("DELETE".equals(exchange.getRequestMethod())) {
            // DELETE
            if ("items".equals(path[1])) {
                try {
                    responses = itemsRequest.deleteItems(path);
                    response.sendResponse(exchange, 400, responses);
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }else if ("cards".equals(path[3])) {
                try {
                    responses = cardsRequest.deleteCards(path);
                    response.sendResponse(exchange, 400, responses);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                responses = "404 ENTITY NOT FOUND";
                response.sendResponse(exchange, 400, responses);
            }
        } else { //untuk request method yang tidak disupport
            handleUnsupportedMethod(exchange);
        }
    }

    private void handleUnsupportedMethod (HttpExchange exchange) throws IOException {
        responses = "Request method tidak ada.";
        response.sendResponse(exchange, 405, responses);
    }

    private JSONObject parseRequestBody(InputStream requestBody) throws IOException {
        byte[] requestBodyBytes = requestBody.readAllBytes();
        String requestBodyString = new String(requestBodyBytes);
        return new JSONObject(requestBodyString);
    }
}
