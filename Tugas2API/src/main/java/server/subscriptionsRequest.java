package server;

import java.util.Map;
import java.util.HashMap;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import ardit.com.Cards;
import ardit.com.Customer;
import ardit.com.Items;
import ardit.com.Subscriptions;
import org.json.JSONArray;
import org.json.JSONObject;
import persistences.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class subscriptionsRequest {
    CardsData cardsData = new CardsData();
    CustomerData customerData = new CustomerData();
    itemsData itemsData = new itemsData();
    shippingAddressesData shippingAddressesData = new shippingAddressesData();
    subscriptionItemsData subscriptionItemsData = new subscriptionItemsData();
    subscriptionsData subscriptionsData = new subscriptionsData();

    // GET SUBSCRIPTIONS (Select in Database)
    public JSONObject getSubscriptions(String query,String[] path) throws SQLException {
        JSONObject jsonSubscriptions = new JSONObject();
        JSONArray jsonSubscriptionsArray = new JSONArray();

        if (query != null && !query.isEmpty()) {
            // Parsing query parameters
            Map<String, String> queryParams = parseQueryParams(query);

            String sortBy = queryParams.get("sort_by");
            String sortType = queryParams.get("sort_type");

            if ("current_term_end".equals(sortBy) && "desc".equals(sortType)) {
                // Fetch sorted subscriptions
                ArrayList<Subscriptions> listSubscriptions = subscriptionsData.selectSortSubscription();
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
            } else {
                throw new IllegalArgumentException("Invalid query parameters");
            }
        } else {
            // Fetch all subscriptions if no query parameters are provided
            ArrayList<Subscriptions> listSubscriptions = subscriptionsData.selectAllSubscriptions();
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
        }

        jsonSubscriptions.put("Subscriptions Record", jsonSubscriptionsArray);
        return jsonSubscriptions;
    }
    // POST SUBSCRIPTIONS (INSERT in Database)
    public String postSubscriptions(JSONObject jsonReqBody) throws SQLException {
        Subscriptions subscriptions = userParseJSONData(jsonReqBody);
        return subscriptionsData.addNewSubscription(subscriptions);
    }
    // PUT SUBSCRIPTIONS (UPDATE in database)
    public String putSubscriptions (JSONObject jsonReqBody, String[] path) throws SQLException {
        Subscriptions subscriptions = userParseJSONData(jsonReqBody);
        int idSubscriptions = Integer.valueOf(path[2]);
        return subscriptionsData.updateSubscription(subscriptions,idSubscriptions);
    }
    private Subscriptions userParseJSONData (JSONObject jsonReqBody) {
        Subscriptions subscriptions = new Subscriptions();
        System.out.println("Getting data from request");
        subscriptions.setId(jsonReqBody.optInt("id"));
        subscriptions.setCustomer(jsonReqBody.optInt("customer"));
        subscriptions.setBilling_period(jsonReqBody.optInt("billing_period"));
        subscriptions.setBilling_period_unit(jsonReqBody.optString("billing_period_unit"));
        subscriptions.setTotal_due(jsonReqBody.optInt("total_due"));
        subscriptions.setActivated_at(jsonReqBody.optInt("actived_at"));
        subscriptions.setCurrent_term_start(jsonReqBody.optString("current_term_start"));
        subscriptions.setCurrent_term_end(jsonReqBody.optString("current_term_end"));
        subscriptions.setStatus(jsonReqBody.optString("status"));
        return subscriptions;
    }
    // DELETE SUBSCRIPTIONS
    public String deleteCust(String[] path) throws SQLException, ClassNotFoundException {
        int idItems = Integer.valueOf(path[2]);
        return itemsData.deleteItem(idItems);
    }
    private Map<String, String> parseQueryParams(String query) {
        Map<String, String> queryPairs = new HashMap<>();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            queryPairs.put(
                    URLDecoder.decode(pair.substring(0, idx), StandardCharsets.UTF_8),
                    URLDecoder.decode(pair.substring(idx + 1), StandardCharsets.UTF_8)
            );
        }
        return queryPairs;
    }
}
