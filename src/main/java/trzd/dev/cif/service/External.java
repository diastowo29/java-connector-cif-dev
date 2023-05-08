package trzd.dev.cif.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class External {
    private String endpoint = "/api/v2/any_channel/push";
    public HttpResponse<String> replyExternal (Map<String, Object> map, String host, String auth) throws IOException, URISyntaxException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(map);
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(apis(host)))
                .header("authorization", auth)
                .header("content-type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(httpRequest,
                        HttpResponse.BodyHandlers.ofString());
        return response;
    }

    public String apis (String host) {
        return "https://" + host + ".anydomain.com/" + endpoint;
    }
}