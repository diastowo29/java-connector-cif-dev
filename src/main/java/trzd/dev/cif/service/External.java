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
    public HttpResponse<String> replyExternal (Map<String, Object> map, String auth) throws IOException, URISyntaxException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(map);
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(apis()))
                .header("authorization", auth)
                .header("content-type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        return HttpClient.newHttpClient()
                .send(httpRequest,
                        HttpResponse.BodyHandlers.ofString());
    }

    public String apis () {
        String host = "";
        String endpoint = "/api/v2/any_channel/push";
        return "https://" + host + ".anydomain.com/" + endpoint;
    }
}