package server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;

import java.io.*;
import java.sql.SQLException;

public class Server implements HttpHandler {
    Response response = new Response();
    CardsRequest cardsRequest = new CardsRequest();
    itemsRequest itemsRequest = new itemsRequest();
    shippingAddressesRequest shippingAddressesRequest = new shippingAddressesRequest();
    subscriptionItemsRequest subscriptionItemsRequest = new subscriptionItemsRequest();
    subscriptionsRequest subscriptionsRequest = new subscriptionsRequest();
    String response = null;
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String[] path = exchange.getRequestURI().getPath().split("/");
        String query = exchange.getRequestURI().getQuery();

        if ("GET".equals(exchange.getRequestMethod())) {
            if (path.length <= 1) {
                Response.sendResponse(exchange, 200, "Hello there!");
            }
            if ("customer".equals(path[1])) {
                JSONObject jsonUser = null;
                try {
                    jsonUser = CustomerRequest.getCustomer(path);
                    if (jsonUser != null)
                        Response.getResponse(exchange, jsonUser.toString(), path, "Customer", 200);
                    else {
                        Response.sendResponse(exchange, 404, "Not Found");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else if ("products".equals(path[1])) {
                JSONObject jsonProduct = null;
                try {
                    jsonProduct = productReqHandler.getProduct(path);
                    if (jsonProduct != null)
                        responseHandler.getResponse(exchange, jsonProduct.toString(), path, "products", 200);
                    else {
                        responseHandler.sendResponse(exchange, 404, "Not Found");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else if ("orders".equals(path[1])) {
                JSONObject jsonOrders = null;
                try {
                    jsonOrders = orderReqHandler.getOrder(path);
                    if (jsonOrders != null) {
                        responseHandler.getResponse(exchange, jsonOrders.toString(), path, "orders", 200);
                    }
                    else {
                        responseHandler.sendResponse(exchange, 404, "Not Found");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } else if ("POST".equals(exchange.getRequestMethod())) {
            // POST
            if ("users".equals(path[1])) {
                JSONObject jsonReqBody = parseRequestBody(exchange.getRequestBody());
                try {
                    response = userReqHandler.postUsers(jsonReqBody);
                    responseHandler.sendResponse(exchange, 200, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else if ("addresses".equals(path[1])) {
                JSONObject jsonReqBody = parseRequestBody(exchange.getRequestBody());
                try {
                    response = addressRequestHandler.postAddresses(jsonReqBody);
                    responseHandler.sendResponse(exchange, 200, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else if ("products".equals(path[1])) {
                JSONObject jsonReqBody = parseRequestBody(exchange.getRequestBody());
                try {
                    response = productReqHandler.postProduct(jsonReqBody);
                    responseHandler.sendResponse(exchange, 200, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else if ("orders".equals(path[1])) {
                JSONObject jsonReqBody = parseRequestBody(exchange.getRequestBody());
                try {
                    response = orderReqHandler.postOrders(jsonReqBody);
                    responseHandler.sendResponse(exchange, 200, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else if ("order_details".equals(path[1])) {
                JSONObject jsonReqBody = parseRequestBody(exchange.getRequestBody());
                try {
                    response = orderReqHandler.postOrderDetails(jsonReqBody);
                    responseHandler.sendResponse(exchange, 200, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else if ("reviews".equals(path[1])) {
                JSONObject jsonReqBody = parseRequestBody(exchange.getRequestBody());
                try {
                    response = reviewReqHandler.postReview(jsonReqBody);
                    responseHandler.sendResponse(exchange, 200, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                response = "404 ENTITY NOT FOUND";
                responseHandler.sendResponse(exchange, 400, response);
            }
        } else if ("PUT".equals(exchange.getRequestMethod())) {
            if ("users".equals(path[1])) {
                JSONObject jsonReqBody = parseRequestBody(exchange.getRequestBody());
                try {
                    response = userReqHandler.putUsers(jsonReqBody, path);
                    responseHandler.sendResponse(exchange, 200, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else if ("addresses".equals(path[1])) {
                JSONObject jsonReqBody = parseRequestBody(exchange.getRequestBody());
                try {
                    response = addressRequestHandler.putAddress(jsonReqBody, path);
                    responseHandler.sendResponse(exchange, 200, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else if ("products".equals(path[1])) {
                JSONObject jsonReqBody = parseRequestBody(exchange.getRequestBody());
                try {
                    response = productReqHandler.putProduct(jsonReqBody, path);
                    responseHandler.sendResponse(exchange, 200, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else if ("orders".equals(path[1])) {
                JSONObject jsonReqBody = parseRequestBody(exchange.getRequestBody());
                try {
                    response = orderReqHandler.putOrder(jsonReqBody, path);
                    responseHandler.sendResponse(exchange, 200, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else if ("order_details".equals(path[1])) {
                JSONObject jsonReqBody = parseRequestBody(exchange.getRequestBody());
                try {
                    response = orderReqHandler.putOrderDetails(jsonReqBody, path);
                    responseHandler.sendResponse(exchange, 200, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else if ("reviews".equals(path[1])) {
                JSONObject jsonReqBody = parseRequestBody(exchange.getRequestBody());
                try {
                    response = reviewReqHandler.putReview(jsonReqBody, path);
                    responseHandler.sendResponse(exchange, 200, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                response = "404 ENTITY NOT FOUND";
                responseHandler.sendResponse(exchange, 400, response);
            }
        } else if ("DELETE".equals(exchange.getRequestMethod())) {
            // DELETE
            if ("users".equals(path[1])) {
                try {
                    response = userReqHandler.deleteUser(path);
                    responseHandler.sendResponse(exchange, 200, response);
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } else if ("addresses".equals(path[1])) {
                try {
                    response = addressRequestHandler.deleteAddress(path);
                    responseHandler.sendResponse(exchange, 200, response);
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } else if ("products".equals(path[1])) {
                try {
                    response = productReqHandler.deleteProduct(path);
                    responseHandler.sendResponse(exchange, 200, response);
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } else if ("orders".equals(path[1])) {
                try {
                    response = orderReqHandler.deleteOrders(path);
                    responseHandler.sendResponse(exchange, 200, response);
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

            } else if ("order_details".equals(path[1])) {
                try {
                    response = orderReqHandler.deleteOrderDetails(path);
                    responseHandler.sendResponse(exchange, 200, response);
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } else if ("reviews".equals(path[1])) {
                try {
                    response = reviewReqHandler.deleteReview(path);
                    responseHandler.sendResponse(exchange, 200, response);
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } else {
                response = "404 ENTITY NOT FOUND";
                responseHandler.sendResponse(exchange, 400, response);
            }
        } else { //untuk request method yang tidak disupport
            handleUnsupportedMethod(exchange);
        }
    }

    private void handleUnsupportedMethod (HttpExchange exchange) throws IOException {
        response = "RequestHandler method tidak didukung/tidak ada.";
        responseHandler.sendResponse(exchange, 405, response);
    }

    private JSONObject parseRequestBody(InputStream requestBody) throws IOException {
        byte[] requestBodyBytes = requestBody.readAllBytes();
        String requestBodyString = new String(requestBodyBytes);
        return new JSONObject(requestBodyString);
    }

}