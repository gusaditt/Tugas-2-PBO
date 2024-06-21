package ardit.com;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Subscriptions {

    // Variables
    private int id;
    private int customer;
    private int billing_period;
    public enum BillingPeriodUnit { MONTH, YEAR }
    private BillingPeriodUnit billing_period_unit;
    private int total_due;
    private int activated_at;
    private String current_term_start;
    private String current_term_end;
    public enum Status { ACTIVE, CANCELLED, NON_RENEWING }
    private Status status;

    // DateTimeFormatter for parsing and formatting date-time strings
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Constructors
    public Subscriptions() {}

    public Subscriptions(int id, int customer, int billing_period,Subscriptions.BillingPeriodUnit billing_period_unit, int total_due, int activated_at, String current_term_start, String current_term_end, String status) {
        this.id = id;
        this.customer = customer;
        this.billing_period = billing_period;
        this.billing_period_unit = billing_period_unit;
        this.total_due = total_due;
        this.activated_at = activated_at;
        this.current_term_start = current_term_start;
        this.current_term_end = current_term_end;
    }

    // Getter and Setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }

    public int getBilling_period() {
        return billing_period;
    }

    public void setBilling_period(int billing_period) {
        this.billing_period = billing_period;
    }

    public BillingPeriodUnit getBilling_period_unit() {
        return billing_period_unit;
    }

    public void setBilling_period_unit(String billing_period_unit) {
        this.billing_period_unit = BillingPeriodUnit.valueOf(billing_period_unit.toUpperCase());
    }

    public int getTotal_due() {
        return total_due;
    }

    public void setTotal_due(int total_due) {
        this.total_due = total_due;
    }

    public int getActivated_at() {
        return activated_at;
    }

    public void setActivated_at(int activated_at) {
        this.activated_at = activated_at;
    }

    public String getCurrent_term_start() {
        return current_term_start;
    }

    public void setCurrent_term_start(String current_term_start) {
        this.current_term_start = current_term_start;
    }

    public String getCurrent_term_end() {
        return current_term_end;
    }

    public void setCurrent_term_end(String current_term_end) {
        this.current_term_end = current_term_end;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = Status.valueOf(status.toUpperCase());
    }
}
