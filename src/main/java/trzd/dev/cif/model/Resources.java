package trzd.dev.cif.model;

public class Resources {
    private String external_id;
    private String thread_id;
    private String message;
    private String html_message;
    private String created_at;
    private ResourceAuthor author;
    private boolean allow_channelback;

    public Resources (String external_id, String thread_id, String message,
                      String html_message, String created_at, ResourceAuthor author,
                      boolean allow_channelback) {
        this.external_id = external_id;
        this.thread_id = thread_id;
        this.message = message;
        this.html_message = html_message;
        this.created_at = created_at;
        this.author = author;
        this.allow_channelback = allow_channelback;
    }

    public Resources() {

    }

    public void setExternal_id(String external_id) {
        this.external_id = external_id;
    }

    public String getExternal_id() {
        return external_id;
    }

    public void setThread_id(String thread_id) {
        this.thread_id = thread_id;
    }

    public String getThread_id() {
        return thread_id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setHtml_message(String html_message) {
        this.html_message = html_message;
    }

    public String getHtml_message() {
        return html_message;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setAuthor(ResourceAuthor author) {
        this.author = author;
    }

    public ResourceAuthor getAuthor() {
        return author;
    }

    public void setAllow_channelback(boolean allow_channelback) {
        this.allow_channelback = allow_channelback;
    }

    public boolean getAllow_channelback () {
        return allow_channelback;
    }
}
