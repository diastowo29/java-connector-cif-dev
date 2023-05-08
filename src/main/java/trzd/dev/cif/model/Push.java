package trzd.dev.cif.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Push {
    @NotNull(message = "Required field(s) is missing : instance_id")
    private String instance_id;
    private PushMessage message;
    private PushMessageAuthor customer;

    public void setCustomer(PushMessageAuthor customer) {
        this.customer = customer;
    }

    public PushMessageAuthor getCustomer() {
        return customer;
    }

    public String getInstance_id() {
        return instance_id;
    }

    public void setInstance_id(String instance_id) {
        this.instance_id = instance_id;
    }

    public PushMessage getMessage() {
        return message;
    }

    public void setMessage(PushMessage message) {
        this.message = message;
    }
}
