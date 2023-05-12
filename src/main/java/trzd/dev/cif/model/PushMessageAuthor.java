package trzd.dev.cif.model;

public class PushMessageAuthor {
    private String id;
    private String username;

    public PushMessageAuthor (String id, String username) {
        this.id = id;
        this.username = username;
    }

    public PushMessageAuthor () {

    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
