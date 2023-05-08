package trzd.dev.cif.model;

public class PushMessage {
    private String id;
    private String text;
    private PushMessageAuthor from;

    private PushMessageFiles[] files;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setFrom(PushMessageAuthor from) {
        this.from = from;
    }

    public PushMessageAuthor getFrom() {
        return from;
    }

    public void setFiles(PushMessageFiles[] files) {
        this.files = files;
    }

    public PushMessageFiles[] getFiles() {
        return files;
    }
}
