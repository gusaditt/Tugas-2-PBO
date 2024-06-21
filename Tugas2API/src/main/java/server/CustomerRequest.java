package server;

import ardit.com.Cards;
import ardit.com.Customer;
import ardit.com.ShippingAddresses;
import ardit.com.Subscriptions;
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

    // GET CUSTOMER (Select in Database)
    public JSONObject getCustomer(String[] path) throws SQLException {
        int idCustomer = 0;
        JSONObject jsonCustomer = null;
        if (path.length == 2) {
            jsonCustomer = new JSONObject();
            JSONArray jsonCustomerArray = new JSONArray();
            ArrayList<Customer> listCustomer = customerData.selectAllCustomers();
            for (Customer customer : listCustomer) {
                JSONObject jsonCustomerRecord = new JSONObject();
                jsonCustomerRecord.put("id", customer.getId());
                jsonCustomerRecord.put("email", customer.getEmail());
                jsonCustomerRecord.put("first_name", customer.getFirst_name());
                jsonCustomerRecord.put("last_name", customer.getLast_name());
                jsonCustomerRecord.put("phone_number", customer.getPhone_number());
                jsonCustomerArray.put(jsonCustomerRecord);
            }
            jsonCustomer.put("Customer Record", jsonCustomerArray);
        }
        else if (path.length == 3){
            jsonCustomer = new JSONObject();
            idCustomer = Integer.valueOf(path[2]);
            Customer customer = customerData.selectCustomerById(idCustomer);
            JSONArray jsonShippingAddressesArray = new JSONArray();
            ArrayList<ShippingAddresses> listAddresses = shippingAddressesData.selectAddressesByCustomer(idCustomer);
            if (customer.getId() != 0) {
                JSONObject jsonCustomerRecord = new JSONObject();
                jsonCustomerRecord.put("id", customer.getId());
                jsonCustomerRecord.put("first_name", customer.getFirst_name());
                jsonCustomerRecord.put("last_name", customer.getLast_name());
                jsonCustomerRecord.put("email", customer.getEmail());
                jsonCustomerRecord.put("phone_number", customer.getPhone_number());
                for (ShippingAddresses address : listAddresses) {
                    JSONObject jsonShippingAddressesRecord = new JSONObject();
                    jsonShippingAddressesRecord.put("id", address.getId());
                    jsonShippingAddressesRecord.put("customer", address.getCustomer());
                    jsonShippingAddressesRecord.put("title", address.getTitle());
                    jsonShippingAddressesRecord.put("line1", address.getLine1());
                    jsonShippingAddressesRecord.put("line2", address.getLine2());
                    jsonShippingAddressesRecord.put("city", address.getCity());
                    jsonShippingAddressesRecord.put("province", address.getProvince());
                    jsonShippingAddressesRecord.put("postcode", address.getPostcode());
                    jsonShippingAddressesArray.put(jsonShippingAddressesRecord);
                }
                jsonCustomer.put("Customer Record", jsonCustomerRecord);
                jsonCustomer.put("Shipping Addresses Record", jsonShippingAddressesArray);
            }
        } else if (path.length == 4) {
            if ("cards".equals(path[3])) {
                idCustomer = Integer.valueOf(path[2]);
                jsonCustomer = new JSONObject();
                JSONObject jsonCards = new JSONObject();
                JSONArray jsonCardsArray = new JSONArray();
                ArrayList<Cards> listCards = cardsData.selectCardsByCustomer(idCustomer);
                for (Cards cards : listCards) {
                    JSONObject jsonCardsRecord = new JSONObject();
                    jsonCardsRecord.put("id", cards.getId());
                    jsonCardsRecord.put("customer", cards.getCustomer());
                    jsonCardsRecord.put("card", cards.getCard_type());
                    jsonCardsRecord.put("masked_number", cards.getMasked_number());
                    jsonCardsRecord.put("expiry_month", cards.getExpiry_month());
                    jsonCardsRecord.put("expiry_year", cards.getExpiry_year());
                    jsonCardsRecord.put("status", cards.getStatus());
                    jsonCardsRecord.put("is_primary", cards.getIs_primary());
                    jsonCardsArray.put(jsonCardsRecord);
                }
                jsonCards.put("Cards Record", jsonCardsArray);
                jsonCustomer.put("Customer Card Record", jsonCards);
            } else if ("subscriptions".equals(path[3])) {
                jsonCustomer = new JSONObject();
                JSONObject jsonSubscriptions = new JSONObject();
                idCustomer = Integer.valueOf(path[2]);
                JSONArray jsonSubscriptionsArray = new JSONArray();
                ArrayList<Subscriptions> listSubscriptions= subscriptionsData.selectSubscriptionsByCustomer(idCustomer);
                for (Subscriptions subscriptions : listSubscriptions) {
                    JSONObject jsonSubscriptionsRecord = new JSONObject();
                    jsonSubscriptionsRecord.put("id", subscriptions.getId());
                    jsonSubscriptionsRecord.put("billing_period", subscriptions.getBilling_period());
                    jsonSubscriptionsRecord.put("billing_period_unit", subscriptions.getBilling_period_unit());
                    jsonSubscriptionsRecord.put("total_due", subscriptions.getTotal_due());
                    jsonSubscriptionsRecord.put("actived_at", subscriptions.getActivated_at());
                    jsonSubscriptionsRecord.put("current_term_start", subscriptions.getCurrent_term_start());
                    jsonSubscriptionsRecord.put("current_term_end", subscriptions.getCurrent_term_end());
                    jsonSubscriptionsRecord.put("status", subscriptions.getStatus());
                    jsonSubscriptionsArray.put(jsonSubscriptionsRecord);
                }
                jsonSubscriptions.put("Subscriptions Record", jsonSubscriptionsArray);
                jsonCustomer.put("Customer Subscriptions Record", jsonSubscriptions);
            } else if ("subscriptions?subscriptions_status=active".equals(path[2])) {
                jsonCustomer = new JSONObject();
                JSONObject jsonSubscriptions = new JSONObject();
                idCustomer = Integer.valueOf(path[1]);
                JSONArray jsonSubscriptionsArray = new JSONArray();
                ArrayList<Subscriptions> listSubscriptions= subscriptionsData.selectSubscriptionsByStatus(idCustomer, "active");
                for (Subscriptions subscriptions : listSubscriptions) {
                    JSONObject jsonSubscriptionsRecord = new JSONObject();
                    jsonSubscriptionsRecord.put("id", subscriptions.getId());
                    jsonSubscriptionsRecord.put("customer", subscriptions.getCustomer());
                    jsonSubscriptionsRecord.put("billing_period", subscriptions.getBilling_period());
                    jsonSubscriptionsRecord.put("billing_period_unit", subscriptions.getBilling_period_unit());
                    jsonSubscriptionsRecord.put("total_due", subscriptions.getTotal_due());
                    jsonSubscriptionsRecord.put("actived_at", subscriptions.getActivated_at());
                    jsonSubscriptionsRecord.put("current_term_start", subscriptions.getCurrent_term_start());
                    jsonSubscriptionsRecord.put("current_term_end", subscriptions.getCurrent_term_end());
                    jsonSubscriptionsRecord.put("status", subscriptions.getStatus());
                    jsonSubscriptionsArray.put(jsonSubscriptionsRecord);
                }
                jsonSubscriptions.put("Subscriptions Record", jsonSubscriptionsArray);
                jsonCustomer.put("Customer Subscriptions Record", jsonSubscriptions);
            }else if ("subscriptions?subscriptions_status=cancelled".equals(path[2])) {
                jsonCustomer = new JSONObject();
                JSONObject jsonSubscriptions = new JSONObject();
                idCustomer = Integer.valueOf(path[1]);
                JSONArray jsonSubscriptionsArray = new JSONArray();
                ArrayList<Subscriptions> listSubscriptions= subscriptionsData.selectSubscriptionsByStatus(idCustomer, "cancelled");
                for (Subscriptions subscriptions : listSubscriptions) {
                    JSONObject jsonSubscriptionsRecord = new JSONObject();
                    jsonSubscriptionsRecord.put("id", subscriptions.getId());
                    jsonSubscriptionsRecord.put("customer", subscriptions.getCustomer());
                    jsonSubscriptionsRecord.put("billing_period", subscriptions.getBilling_period());
                    jsonSubscriptionsRecord.put("billing_period_unit", subscriptions.getBilling_period_unit());
                    jsonSubscriptionsRecord.put("total_due", subscriptions.getTotal_due());
                    jsonSubscriptionsRecord.put("actived_at", subscriptions.getActivated_at());
                    jsonSubscriptionsRecord.put("current_term_start", subscriptions.getCurrent_term_start());
                    jsonSubscriptionsRecord.put("current_term_end", subscriptions.getCurrent_term_end());
                    jsonSubscriptionsRecord.put("status", subscriptions.getStatus());
                    jsonSubscriptionsArray.put(jsonSubscriptionsRecord);
                }
                jsonSubscriptions.put("Subscriptions Record", jsonSubscriptionsArray);
                jsonCustomer.put("Customer Subscriptions Record", jsonSubscriptions);
            }else if ("subscriptions?subscriptions_status=non-renewing".equals(path[2])) {
                jsonCustomer = new JSONObject();
                JSONObject jsonSubscriptions = new JSONObject();
                idCustomer = Integer.valueOf(path[2]);
                JSONArray jsonSubscriptionsArray = new JSONArray();
                ArrayList<Subscriptions> listSubscriptions= subscriptionsData.selectSubscriptionsByStatus(idCustomer, "non-renewing");
                for (Subscriptions subscriptions : listSubscriptions) {
                    JSONObject jsonSubscriptionsRecord = new JSONObject();
                    jsonSubscriptionsRecord.put("id", subscriptions.getId());
                    jsonSubscriptionsRecord.put("customer", subscriptions.getCustomer());
                    jsonSubscriptionsRecord.put("billing_period", subscriptions.getBilling_period());
                    jsonSubscriptionsRecord.put("billing_period_unit", subscriptions.getBilling_period_unit());
                    jsonSubscriptionsRecord.put("total_due", subscriptions.getTotal_due());
                    jsonSubscriptionsRecord.put("actived_at", subscriptions.getActivated_at());
                    jsonSubscriptionsRecord.put("current_term_start", subscriptions.getCurrent_term_start());
                    jsonSubscriptionsRecord.put("current_term_end", subscriptions.getCurrent_term_end());
                    jsonSubscriptionsRecord.put("status", subscriptions.getStatus());
                    jsonSubscriptionsArray.put(jsonSubscriptionsRecord);
                }
                jsonSubscriptions.put("Subscriptions Record", jsonSubscriptionsArray);
                jsonCustomer.put("Customer Subscriptions Record", jsonSubscriptions);
            }
        }
        return jsonCustomer;
    }

    // POST CUSTOMERS (INSERT in Database)
    public String postCust(JSONObject jsonReqBody) throws SQLException {
        Customer customer = userParseJSONData(jsonReqBody);
        return customerData.addNewCustomer(customer);
    }

    // PUT CUSTOMERS (UPDATE in database)
    public String putCust(JSONObject jsonReqBody, String[] path) throws SQLException {
        Customer customer = userParseJSONData(jsonReqBody);
        int idCustomer = Integer.valueOf(path[2]);
        return customerData.updateCustomer(customer);
    }
    private Customer userParseJSONData (JSONObject jsonReqBody) {
        Customer customer = new Customer();
        System.out.println("Getting data from request");
        customer.setId(jsonReqBody.optInt("id"));
        customer.setFirst_name(jsonReqBody.optString("first_name"));
        customer.setLast_name(jsonReqBody.optString("last_name"));
        customer.setEmail(jsonReqBody.optString("email"));
        customer.setPhone_number(jsonReqBody.optString("phone_number"));

        return customer;
    }
    // DELETE CUSTOMERS
    public String deleteCust(String[] path) throws SQLException, ClassNotFoundException {
        int idCustomer = Integer.valueOf(path[2]);
        return customerData.deleteCustomer(idCustomer);
    }
}
