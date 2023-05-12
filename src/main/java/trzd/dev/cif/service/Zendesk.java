package trzd.dev.cif.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class Zendesk {
    public HttpResponse<String> pushZendesk (Map<String, Object> map, String host, String auth)
            throws IOException, URISyntaxException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(map);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(apis(host)))
                .header("authorization", auth)
                .header("content-type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        return HttpClient.newHttpClient()
                .send(request,
                        HttpResponse.BodyHandlers.ofString());
    }

    public String apis (String host) {
        String endpoint = "/api/v2/any_channel/push";
        return "https://" + host + ".zendesk.com/" + endpoint;
    }
}