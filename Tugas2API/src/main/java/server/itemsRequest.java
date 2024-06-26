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


public class itemsRequest {
    CardsData cardsData = new CardsData();
    CustomerData customerData = new CustomerData();
    itemsData itemsData = new itemsData();
    shippingAddressesData shippingAddressesData = new shippingAddressesData();
    subscriptionItemsData subscriptionItemsData = new subscriptionItemsData();
    subscriptionsData subscriptionsData = new subscriptionsData();


    // GET ITEMS (Select in Database)
    public JSONObject getItems(String[] path) throws SQLException {
        JSONObject jsonSubscriptions = new JSONObject();
        JSONArray jsonSubscriptionsArray = new JSONArray();
        int idItems = 0;
        JSONObject jsonItems = null;
            if (path.length == 2) {
                jsonItems = new JSONObject();
                JSONArray jsonItemsArray = new JSONArray();
                ArrayList<Items> listItems = itemsData.selectAllItems();
                for (Items items : listItems) {
                    JSONObject jsonItemsRecord = new JSONObject();
                    jsonItemsRecord.put("id", items.getId());
                    jsonItemsRecord.put("name", items.getName());
                    jsonItemsRecord.put("price", items.getPrice());
                    jsonItemsRecord.put("type", items.getType());
                    jsonItemsRecord.put("is_active", items.getIs_active());
                    jsonItemsArray.put(jsonItemsRecord);
                }
                jsonItems.put("Items Record", jsonItemsArray);
            } else if (path.length == 3) {
                jsonItems = new JSONObject();
                idItems = Integer.valueOf(path[2]);
                Items items = itemsData.selectItemById(idItems);
                if (items.getId() != 0) {
                    JSONObject jsonItemsRecord = new JSONObject();
                    jsonItemsRecord.put("id", items.getId());
                    jsonItemsRecord.put("name", items.getName());
                    jsonItemsRecord.put("price", items.getPrice());
                    jsonItemsRecord.put("type", items.getType());
                    jsonItemsRecord.put("is_active", items.getIs_active());
                    jsonItems.put("Items Record", jsonItemsRecord);
                }
            }else if (path.length == 1){
                JSONObject jsonItem = new JSONObject();
                JSONArray jsonItemArray = new JSONArray();
                ArrayList<Items> listItems = itemsData.selectItemsByActiveStatus();
                for (Items items : listItems) {
                    JSONObject jsonItemRecord = new JSONObject();
                    jsonItemRecord.put("id", items.getId());
                    jsonItemRecord.put("name", items.getName());
                    jsonItemRecord.put("price", items.getPrice());
                    jsonItemRecord.put("type", items.getType());
                    jsonItemRecord.put("is_active", items.getIs_active());
                    jsonItemArray.put(jsonItemRecord);
                }
                jsonItem.put("Item Record", jsonItemArray);
            }
            return jsonItems;
    }


    // POST ITEMS (INSERT in Database)
    public String postItems(JSONObject jsonReqBody) throws SQLException {
        Items items = itemsParseJSONData(jsonReqBody);
        return itemsData.addNewItem(items);
    }

    // PUT ITEMS (UPDATE in database)
    public String putItems(JSONObject jsonReqBody, String[] path) throws SQLException {
        Items items = itemsParseJSONData(jsonReqBody);
        int idItems = Integer.valueOf(path[2]);
        return itemsData.updateItems(items, idItems);
    }

    private Items itemsParseJSONData(JSONObject jsonReqBody) {
        Items items = new Items();
        System.out.println("Getting data from request");
        items.setId(jsonReqBody.optInt("id"));
        items.setName(jsonReqBody.optString("name"));
        items.setPrice(jsonReqBody.optInt("price"));
        items.setType(jsonReqBody.optString("type"));
        items.setIs_active(jsonReqBody.optInt("is_active"));

        return items;
    }

    // DELETE ITEMS
    public String deleteItems(String[] path) throws SQLException, ClassNotFoundException {
        int idItems = Integer.parseInt(path[2]);
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
    public JSONObject getItemStatus() throws SQLException {
        JSONObject jsonItem = new JSONObject();
        JSONArray jsonItemArray = new JSONArray();
        ArrayList<Items> listItems = itemsData.selectItemsByActiveStatus();
        for (Items items : listItems) {
            JSONObject jsonItemRecord = new JSONObject();
            jsonItemRecord.put("id", items.getId());
            jsonItemRecord.put("name", items.getName());
            jsonItemRecord.put("price", items.getPrice());
            jsonItemRecord.put("type", items.getType());
            jsonItemRecord.put("is_active", items.getIs_active());
            jsonItemArray.put(jsonItemRecord);
        }
        jsonItem.put("Item Record", jsonItemArray);
        return jsonItem;
    }
}

