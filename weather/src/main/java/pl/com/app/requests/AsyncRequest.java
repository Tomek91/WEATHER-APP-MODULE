package pl.com.app.requests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.Duration;


public class AsyncRequest {

    public static HttpRequest requestGet(final String path) throws URISyntaxException {
        return HttpRequest.newBuilder()
                .uri(new URI(path))
                .version(HttpClient.Version.HTTP_2)
                .headers("K1", "V1", "K2", "V2")
                .timeout(Duration.ofSeconds(10)) // HttpTimeoutException
                .GET()
                .build();
    }

    public static <T> HttpRequest requestPost(final String path, T body) throws URISyntaxException, IOException {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(body);

        return HttpRequest.newBuilder()
                .uri(new URI(path))
                .version(HttpClient.Version.HTTP_2)
                .header("content-type", "application/json;charset=UTF-8")
                .timeout(Duration.ofSeconds(10)) // HttpTimeoutException
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
    }
}
