package trzd.dev.cif.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import trzd.dev.cif.model.*;
import trzd.dev.cif.records.Textify;
import trzd.dev.cif.service.External;
import trzd.dev.cif.service.Zendesk;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Controller
@SpringBootApplication
@RequestMapping("/api/v1/cif")
public class CifController {

    String cifPrefix = "cif_";

    Zendesk zendesk = new Zendesk();
    External external = new External();

    @RequestMapping(value = "/manifest")
    ResponseEntity<Object> manifest (HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> urlMap = new HashMap<>();
        urlMap.put("admin_ui", "https://" + request.getServerName() + "/api/v1/cif/");
        urlMap.put("channelback_url", "https://" + request.getServerName() + "/api/v1/cif/channelback");
        map.put("name", Textify.integrationName);
        map.put("author", Textify.integrationAuthor);
        map.put("version", "v1");
        map.put("push_client_id", Textify.integrationPushClientId);
        map.put("channelback_files", true);
        map.put("create_followup_tickets", false);
        map.put("urls", urlMap);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    String admin (Model model) {
        Integration integration = new Integration();
        integration.setName("TRZD Account");
        integration.setPushId("44fbf723-27e5-4437-ae53-41d34d533267");
        integration.setPushToken("b37a46924de6eeb1667fd00889c814ba4757f06f61f0b5bb919c92a0b9f1ceb4");
        model.addAttribute("integration", integration);
        return "admin";
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.POST,
            consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE })
    ResponseEntity<Object> confirmation (Integration form) {
        Map<String, Object> map = new HashMap<>();
        map.put("admin", form);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @RequestMapping(value = "/push/chat/{instance_id}", method = RequestMethod.POST,
            consumes = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<Object> chatPush (@PathVariable("instance_id") String instance_id,
                                 @Valid @RequestBody TokoPush push,
                                 @RequestHeader Map<String, String> headers) {
        int statusCode = 200;
        SimpleDateFormat rfc3339Formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        String time = rfc3339Formatter.format(Calendar.getInstance().getTime());

        Map<String, Object> map = new HashMap<>();
        Resources rsc = new Resources();

        ResourceAuthor rscAuthor = new ResourceAuthor();
        rscAuthor.setName(push.getFull_name());
        rscAuthor.setExternal_id(
                cifPrefix + "user_"
                        + push.getBuyer_id());
        rscAuthor.setDetails(
                cifPrefix + "user_"
                        + push.getBuyer_id());

        rsc.setAllow_channelback(true);
        rsc.setAuthor(rscAuthor);
        rsc.setMessage(push.getMessage());
        rsc.setThread_id(
                cifPrefix + "thread_"
                        + push.getBuyer_id());
        rsc.setExternal_id(
                cifPrefix + "tickets_"
                        + push.getMsg_id());
        rsc.setCreated_at(time);


        map.put("external_resources", new Resources[] { rsc });
        map.put("instance_push_id", instance_id);

//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            HttpResponse<String> zdResponse = zendesk.pushZendesk(map, "pdi-rokitvhelp",
//                    headers.get("authorization").toString());
//            statusCode = zdResponse.statusCode();
//            map = objectMapper.readValue(zdResponse.body(), Map.class);
//        } catch (IOException | URISyntaxException | InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        return new ResponseEntity<>(map, HttpStatusCode.valueOf(statusCode));
    }

    @RequestMapping(value = "/push/discuss/{instance_id}", method = RequestMethod.POST,
            consumes = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<Object> discussPush (@PathVariable("instance_id") String instance_id,
                                 @Valid @RequestBody DiscussPush push,
                                 @RequestHeader Map<String, String> headers) {
        int statusCode = 200;
        SimpleDateFormat rfc3339Formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        String time = rfc3339Formatter.format(Calendar.getInstance().getTime());

        Map<String, Object> map = new HashMap<>();
        Resources rsc = new Resources();

        ResourceAuthor rscAuthor = new ResourceAuthor();
        rscAuthor.setName("- discussion buyer name -");
        rscAuthor.setExternal_id(
                cifPrefix + "user_"
                        + push.getDiscussion_data().getUser_id());
        rscAuthor.setDetails(
                cifPrefix + "user_"
                        + push.getDiscussion_data().getUser_id());

        rsc.setAllow_channelback(true);
        rsc.setAuthor(rscAuthor);
        rsc.setMessage(push.getDiscussion_data().getMessage());
        rsc.setThread_id(
                cifPrefix + "thread_discuss_"
                        + push.getDiscussion_data().getUser_id());
        rsc.setExternal_id(
                cifPrefix + "tickets_"
                        + push.getDiscussion_data().getId());
        rsc.setCreated_at(time);


        map.put("external_resources", new Resources[] { rsc });
        map.put("instance_push_id", instance_id);

//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            HttpResponse<String> zdResponse = zendesk.pushZendesk(map, "pdi-rokitvhelp",
//                    headers.get("authorization").toString());
//            statusCode = zdResponse.statusCode();
//            map = objectMapper.readValue(zdResponse.body(), Map.class);
//        } catch (IOException | URISyntaxException | InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        return new ResponseEntity<>(map, HttpStatusCode.valueOf(statusCode));
    }

    @RequestMapping(value = "/channelback", method = RequestMethod.POST)
    ResponseEntity<Object> channelback (@RequestParam Map<String, Object> param) {
        int statusCode = 200;
        String[] files = new String[] { param.get("file_urls[]").toString() };
        Map<String, Object> map = new HashMap<>();
        PushMessageAuthor to = new PushMessageAuthor("this-is-newid",
                "this-is-newname");
        Channelback channelback = new Channelback(param.get("message").toString(),
                to, files);
        map.put("channelback", channelback);

//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            HttpResponse<String> zdResponse = external.replyExternal(map, "dummy-token-auth");
//            statusCode = zdResponse.statusCode();
//            map = objectMapper.readValue(zdResponse.body(), Map.class);
//        } catch (IOException | URISyntaxException | InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        return new ResponseEntity<>(map, HttpStatusCode.valueOf(statusCode));
    }
}