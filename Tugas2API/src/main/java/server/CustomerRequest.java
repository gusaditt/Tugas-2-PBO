package server;

import ardit.com.Cards;
import ardit.com.Customer;
import org.json.JSONArray;
import org.json.JSONObject;
import persistences.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerRequest{
    CardsData cardsData = new CardsData();
    CustomerData customerData = new CustomerData();
    itemsData itemsData = new itemsData();
    shippingAddressesData shippingAddressesData = new shippingAddressesData();
    subscriptionItemsData subscriptionItemsData = new subscriptionItemsData();
    subscriptionsData subscriptionsData = new subscriptionsData();

    // GET USER (Select in Database)
    public static JSONObject getCustomer(String[] path) throws SQLException {
        int idUser = 0;
        JSONObject jsonUser = null;
        if (path.length == 2) {
            jsonUser = new JSONObject();
            JSONArray jsonUserArray = new JSONArray();
            ArrayList<Customer> listUsers = CustomerData.selectAllCustomers();
            for (Customer user : listUsers) {
                JSONObject jsonUserRecord = new JSONObject();
                jsonUserRecord.put("id", user.getId());
                jsonUserRecord.put("email", user.getEmail());
                jsonUserRecord.put("first_name", user.getFirst_name());
                jsonUserRecord.put("last_name", user.getLast_name());
                jsonUserRecord.put("phone_number", user.getPhone_number());
                jsonUserArray.put(jsonUserRecord);
            }
            jsonUser.put("User Record", jsonUserArray);
        }
        else if (path.length == 3){
            jsonUser = new JSONObject();
            idUser = Integer.valueOf(path[2]);
            Users user = userDAO.selectUserById(idUser);
            JSONArray jsonAddressesArray = new JSONArray();
            ArrayList<Addresses> listAddresses = addressDAO.selectAddressesByUser(idUser);
            if (user.getId() != 0) {
                JSONObject jsonUserRecord = new JSONObject();
                jsonUserRecord.put("id", user.getId());
                jsonUserRecord.put("first_name", user.getFirst_name());
                jsonUserRecord.put("last_name", user.getLast_name());
                jsonUserRecord.put("email", user.getEmail());
                jsonUserRecord.put("phone_number", user.getPhone_number());
                for (Addresses address : listAddresses) {
                    JSONObject jsonAddressRecord = new JSONObject();
                    jsonAddressRecord.put("user", address.getUser());
                    jsonAddressRecord.put("type", address.getType());
                    jsonAddressRecord.put("line1", address.getLine1());
                    jsonAddressRecord.put("line2", address.getLine2());
                    jsonAddressRecord.put("city", address.getCity());
                    jsonAddressRecord.put("province", address.getProvince());
                    jsonAddressRecord.put("postcode", address.getPostcode());
                    jsonAddressesArray.put(jsonAddressRecord);
                }
                jsonUser.put("User Record", jsonUserRecord);
                jsonUser.put("Addresses Record", jsonAddressesArray);
            }
        } else if (path.length == 4) {
            if ("products".equals(path[3])) {
                jsonUser = new JSONObject();
                JSONObject jsonProduct = new JSONObject();
                JSONArray jsonProductArray = new JSONArray();
                ArrayList<Products> listProducts = productDAO.selectMultiple(path[2]);
                for (Products product : listProducts) {
                    JSONObject jsonProductRecord = new JSONObject();
                    jsonProductRecord.put("id", product.getId());
                    jsonProductRecord.put("seller", product.getSeller());
                    jsonProductRecord.put("title", product.getTitle());
                    jsonProductRecord.put("description", product.getDescription());
                    jsonProductRecord.put("price", product.getPrice());
                    jsonProductRecord.put("stock", product.getStock());
                    jsonProductArray.put(jsonProductRecord);
                }
                jsonProduct.put("Product Record", jsonProductArray);
                jsonUser.put("Seller's Product Record", jsonProduct);
            } else if ("orders".equals(path[3])) {
                jsonUser = new JSONObject();
                JSONObject jsonOrders = new JSONObject();
                idUser = Integer.valueOf(path[2]);
                JSONArray jsonOrdersArray = new JSONArray();
                ArrayList<Orders> listOrders = orderDAO.selectOrderById("buyer", idUser);
                for (Orders order : listOrders) {
                    JSONObject jsonOrderRecord = new JSONObject();
                    jsonOrderRecord.put("id", order.getId());
                    jsonOrderRecord.put("buyer", order.getBuyer());
                    jsonOrderRecord.put("note", order.getNote());
                    jsonOrderRecord.put("total", order.getTotal());
                    jsonOrderRecord.put("discount", order.getDiscount());
                    jsonOrderRecord.put("is_paid", order.getIs_paid());
                    jsonOrdersArray.put(jsonOrderRecord);
                }
                jsonOrders.put("Order Record", jsonOrdersArray);
                jsonUser.put("Buyer's Order Record", jsonOrders);
            } else if ("reviews".equals(path[3])) {
                jsonUser = new JSONObject();
                JSONObject jsonReviews = new JSONObject();
                idUser = Integer.valueOf(path[2]);
                JSONArray jsonReviewsArray = new JSONArray();
                ArrayList<Reviews> listReviews = reviewDAO.selectReviewById(idUser);
                for (Reviews review : listReviews) {
                    JSONObject jsonReviewRecord = new JSONObject();
                    jsonReviewRecord.put("order", review.getOrder());
                    jsonReviewRecord.put("star", review.getStar());
                    jsonReviewRecord.put("description", review.getDescription());
                    jsonReviewsArray.put(jsonReviewRecord);
                }
                jsonReviews.put("Review Record", jsonReviewsArray);
                jsonUser.put("Buyer's Reviews Record", jsonReviews);
            }
        }
        return jsonUser;
    }

    // POST USER (INSERT in Database)
    public String postUsers(JSONObject jsonReqBody) throws SQLException {
        Users user = userParseJSONData(jsonReqBody);
        return userDAO.addNewUser(user);
    }

    // PUT USER (UPDATE in database)
    public String putUsers (JSONObject jsonReqBody, String[] path) throws SQLException {
        Users user = userParseJSONData(jsonReqBody);
        int idUser = Integer.valueOf(path[2]);

        return userDAO.updateUser(user, idUser);
    }
    private Users userParseJSONData (JSONObject jsonReqBody) {
        Users user = new Users();
        System.out.println("Getting data from request");
        user.setId(jsonReqBody.optInt("id"));
        user.setFirst_name(jsonReqBody.optString("first_name"));
        user.setLast_name(jsonReqBody.optString("last_name"));
        user.setEmail(jsonReqBody.optString("email"));
        user.setPhone_number(jsonReqBody.optString("phone_number"));
        user.setType(jsonReqBody.optString("type"));

        return user;
    }
    // DELETE USER
    public String deleteUser(String[] path) throws SQLException, ClassNotFoundException {
        int idUser = Integer.valueOf(path[2]);
        return userDAO.deleteUser(idUser);
    }
}