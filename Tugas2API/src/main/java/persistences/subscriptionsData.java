package persistences;


import ardit.com.Customer;
import ardit.com.Subscriptions;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class subscriptionsData {

    // DateTimeFormatter for parsing and formatting date-time strings
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    //SELECT ALL SUBSCRIPTIONS
    public ArrayList<Subscriptions> selectAllSubscriptions() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        ArrayList<Subscriptions> listsubscriptions = new ArrayList<>();

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:subscription.db");
            System.out.println("Connected to database");

            statement = connection.prepareStatement("SELECT * FROM subscriptions");
            result = statement.executeQuery();

            while (result.next()) {
                Subscriptions subscriptions = new Subscriptions();
                subscriptions.setId(result.getInt("id"));
                subscriptions.setCustomer(result.getInt("customer"));
                subscriptions.setBilling_period(result.getInt("billing_period"));
                subscriptions.setBilling_period_unit(result.getString("billing_period_unit"));
                subscriptions.setTotal_due(result.getInt("total_due"));
                subscriptions.setActivated_at(result.getInt("actived_at"));
                subscriptions.setCurrent_term_start(result.getString("current_term_start"));
                subscriptions.setCurrent_term_end(result.getString("current_term_end"));
                subscriptions.setStatus(result.getString("status"));
                listsubscriptions.add(subscriptions);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            throw new RuntimeException(e);
        } finally {
            if (result != null) result.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return listsubscriptions;
    }
    //SELECT SORT SUBSCRIPTIONS
    public ArrayList<Subscriptions> selectSortSubscription() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        ArrayList<Subscriptions> listsubscriptions = new ArrayList<>();

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:subscription.db");
            System.out.println("Connected to database");

            statement = connection.prepareStatement("SELECT * FROM subscriptions ORDER BY current_term_end DESC");
            result = statement.executeQuery();

            while (result.next()) {
                Subscriptions subscriptions = new Subscriptions();
                subscriptions.setId(result.getInt("id"));
                subscriptions.setCustomer(result.getInt("customer"));
                subscriptions.setBilling_period(result.getInt("billing_period"));
                subscriptions.setBilling_period_unit(result.getString("billing_period_unit"));
                subscriptions.setTotal_due(result.getInt("total_due"));
                subscriptions.setActivated_at(result.getInt("actived_at"));
                subscriptions.setCurrent_term_start(result.getString("current_term_start"));
                subscriptions.setCurrent_term_end(result.getString("current_term_end"));
                subscriptions.setStatus(result.getString("status"));
                listsubscriptions.add(subscriptions);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            throw new RuntimeException(e);
        } finally {
            if (result != null) result.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return listsubscriptions;
    }
    // SELECT SUBSCRIPTIONS BY CUSTOMER ID
    public ArrayList<Subscriptions> selectSubscriptionsByCustomer(int idCustomer) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        ArrayList<Subscriptions> subscriptionList = new ArrayList<>();

        try {
            Class.forName("org.sqlite.JDBC");
            // Establish connection to SQLite database
            connection = DriverManager.getConnection("jdbc:sqlite:subscription.db");
            System.out.println("Connected to database");
            statement = connection.prepareStatement("SELECT * FROM subscriptions WHERE customer = ?");
            statement.setInt(1, idCustomer);
            result = statement.executeQuery();

            while(result.next()) {
                Subscriptions subscription = new Subscriptions();
                subscription.setId(result.getInt("id"));
                subscription.setCustomer(result.getInt("customer"));
                subscription.setBilling_period(result.getInt("billing_period"));
                subscription.setBilling_period_unit(result.getString("billing_period_unit"));
                subscription.setTotal_due(result.getInt("total_due"));
                subscription.setActivated_at(result.getInt("actived_at"));
                subscription.setCurrent_term_start(result.getString("current_term_start"));
                subscription.setCurrent_term_end(result.getString("current_term_end"));
                subscription.setStatus(result.getString("status"));
                subscriptionList.add(subscription);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            throw new RuntimeException(e);
        } finally {
            if (result != null) result.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return subscriptionList;
    }

    // SELECT SUBSCRIPTIONS BY CUSTOMER ID AND STATUS
    public ArrayList<Subscriptions> selectSubscriptionsByStatus(int idCustomer, String status) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        ArrayList<Subscriptions> subscriptionList = new ArrayList<>();

        try {
            Class.forName("org.sqlite.JDBC");
            // Establish connection to SQLite database
            connection = DriverManager.getConnection("jdbc:sqlite:subscription.db");
            System.out.println("Connected to database");
            statement = connection.prepareStatement("SELECT * FROM subscriptions WHERE customer = ? AND status = ?");
            statement.setInt(1, idCustomer);
            statement.setString(2, status.toUpperCase());
            result = statement.executeQuery();

            while(result.next()) {
                Subscriptions subscription = new Subscriptions();
                subscription.setId(result.getInt("id"));
                subscription.setCustomer(result.getInt("customer"));
                subscription.setBilling_period(result.getInt("billing_period"));
                subscription.setBilling_period_unit(result.getString("billing_period_unit"));
                subscription.setTotal_due(result.getInt("total_due"));
                subscription.setActivated_at(result.getInt("actived_at"));
                subscription.setCurrent_term_start(result.getString("current_term_start"));
                subscription.setCurrent_term_end(result.getString("current_term_end"));
                subscription.setStatus(result.getString("status"));
                subscriptionList.add(subscription);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            throw new RuntimeException(e);
        } finally {
            if (result != null) result.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return subscriptionList;
    }

    // INSERT NEW SUBSCRIPTION
    public String addNewSubscription(Subscriptions subscription) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        String response;

        try {
            Class.forName("org.sqlite.JDBC");
            // Establish connection to SQLite database
            connection = DriverManager.getConnection("jdbc:sqlite:subscription.db");
            System.out.println("Connected to database");
            statement = connection.prepareStatement("INSERT INTO subscriptions (customer, billing_period, billing_period_unit, total_due, actived_at, current_term_start, current_term_end, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setInt(1, subscription.getCustomer());
            statement.setInt(2, subscription.getBilling_period());
            statement.setString(3, subscription.getBilling_period_unit().toString());
            statement.setInt(4, subscription.getTotal_due());
            statement.setInt(5, subscription.getActivated_at());
            statement.setString(6, subscription.getCurrent_term_start());
            statement.setString(7, subscription.getCurrent_term_end());
            statement.setString(8, subscription.getStatus().toString());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                response = rowsAffected + " row(s) has been affected";
                System.out.println(response);
            } else {
                response = "No rows have been affected";
                System.out.println(response);
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            throw new RuntimeException(e);
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return response;
    }

    // UPDATE SUBSCRIPTION BASED ON ID
    public String updateSubscription(Subscriptions subscription, int idSubscription) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        String response;

        try {
            Class.forName("org.sqlite.JDBC");
            // Establish connection to SQLite database
            connection = DriverManager.getConnection("jdbc:sqlite:ecommerce.db");
            System.out.println("Connected to database");
            statement = connection.prepareStatement("UPDATE subscriptions SET billing_period = ?, billing_period_unit = ?, total_due = ?, activated_at = ?, current_term_start = ?, current_term_end = ?, status = ? WHERE id = ?");
            statement.setInt(1, subscription.getBilling_period());
            statement.setString(2, subscription.getBilling_period_unit().toString());
            statement.setInt(3, subscription.getTotal_due());
            statement.setInt(4, subscription.getActivated_at());
            statement.setString(5, subscription.getCurrent_term_start());
            statement.setString(6, subscription.getCurrent_term_end());
            statement.setString(7, subscription.getStatus().toString());
            statement.setInt(8, idSubscription);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                response = rowsAffected + " row(s) has been affected";
                System.out.println(response);
            } else {
                response = "No rows have been affected";
                System.out.println(response);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            throw new RuntimeException(e);
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return response;
    }

    // DELETE SUBSCRIPTION
    public String deleteSubscription(int idSubscription) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        PreparedStatement statement = null;
        String response;

        try {
            Class.forName("org.sqlite.JDBC");
            // Establish connection to SQLite database
            connection = DriverManager.getConnection("jdbc:sqlite:ecommerce.db");
            System.out.println("Connected to database");
            statement = connection.prepareStatement("DELETE FROM subscriptions WHERE id = ?");
            statement.setInt(1, idSubscription);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                response = rowsAffected + " row(s) have been affected";
                System.out.println(response);
            } else {
                response = "No rows have been affected";
                System.out.println(response);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            throw new RuntimeException(e);
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return response;
    }
}
