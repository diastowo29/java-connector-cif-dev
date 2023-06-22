package trzd.dev.cif.model;

public class DiscussData {
    public String id;
    public Integer product_id;
    public Integer shop_id;
    public Integer user_id;
    public String message;
    public Object create_time;

    public String getId() {
        return id;
    }

    public Integer getShop_id() {
        return shop_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public String getMessage() {
        return message;
    }

    public Object getCreate_time() {
        return create_time;
    }
}
