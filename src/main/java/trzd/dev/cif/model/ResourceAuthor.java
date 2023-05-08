package trzd.dev.cif.model;

public class ResourceAuthor {
    private String external_id;
    private String name;
    private String image_url;

    public void setExternal_id(String external_id) {
        this.external_id = external_id;
    }

    public String getExternal_id() {
        return external_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getImage_url() {
        return image_url;
    }
}
