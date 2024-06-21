package ardit.com;

public class Cards {

    // Variabel
    private int id;
    private int customer;
    public enum CardType { VISA, MASTERCARD }
    private CardType card_type;
    private String masked_number;
    private int expiry_month;
    private int expiry_year;
    public enum Status { VALID, EXPIRED, EXPIRING }
    private Status status;
    private int is_primary;

    // Konstruktor
    public Cards() {}

    public Cards(int id, int customer, String card_type, String masked_number, int expiry_month, int expiry_year, String status, int is_primary) {
        this.id = id;
        this.customer = customer;
        this.card_type = CardType.valueOf(card_type.toUpperCase());
        this.masked_number = masked_number;
        this.expiry_month = expiry_month;
        this.expiry_year = expiry_year;
        this.status = Status.valueOf(status.toUpperCase());
        this.is_primary = is_primary;
    }

    // Metode Getter dan Setter
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

    public String getCard_type() {
        return card_type.name(); // Mengubah enum menjadi String
    }

    public void setCard_type(String card_type) {
        this.card_type = CardType.valueOf(card_type.toUpperCase());
    }

    public String getMasked_number() {
        return masked_number;
    }

    public void setMasked_number(String masked_number) {
        this.masked_number = masked_number;
    }

    public int getExpiry_month() {
        return expiry_month;
    }

    public void setExpiry_month(int expiry_month) {
        this.expiry_month = expiry_month;
    }

    public int getExpiry_year() {
        return expiry_year;
    }

    public void setExpiry_year(int expiry_year) {
        this.expiry_year = expiry_year;
    }

    public String getStatus() {
        return status.name(); // Mengubah enum menjadi String
    }

    public void setStatus(String status) {
        this.status = Status.valueOf(status.toUpperCase());
    }

    public int getIs_primary() {
        return is_primary;
    }

    public void setIs_primary(int is_primary) {
        this.is_primary = is_primary;
    }
}
