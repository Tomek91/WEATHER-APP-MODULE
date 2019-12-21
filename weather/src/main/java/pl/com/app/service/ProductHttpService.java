package pl.com.app.service;

import pl.com.app.exception.MyException;
import pl.com.app.requests.AsyncRequest;

import java.net.ProxySelector;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class ProductHttpService {
    private static final String productsPath = "http://localhost:8080/products";

    public CompletableFuture<HttpResponse<String>> getProducts() {
        CompletableFuture<HttpResponse<String>> response = null;
        try {

            response = HttpClient
                    .newBuilder()
                    .proxy(ProxySelector.getDefault())
                    .build()
                    .sendAsync(
                            AsyncRequest.requestGet(productsPath),
                            HttpResponse.BodyHandlers.ofString()
                    );

        } catch (Exception e) {
            throw new MyException("PRODUCTS HTTP SERVICE: " + e.getMessage());
        }
        return response;
    }
}
