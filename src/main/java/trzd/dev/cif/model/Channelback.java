package trzd.dev.cif.model;

public class Channelback {

    private String content;
    private PushMessageAuthor to;
    private String[] files;

    public Channelback (String content, PushMessageAuthor to, String[] files) {
        this.content = content;
        this.to = to;
        this.files = files;
    }

    public Channelback () {

    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setTo(PushMessageAuthor to) {
        this.to = to;
    }

    public PushMessageAuthor getTo() {
        return to;
    }

    public void setFiles(String[] files) {
        this.files = files;
    }

    public String[] getFiles() {
        return files;
    }
}
