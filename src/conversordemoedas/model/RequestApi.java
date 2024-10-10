package conversordemoedas.model;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class RequestApi {
    private final String apiKey;

    public RequestApi(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getRequest(HashMap<String, String> pairBases) {
        URI uri = URI.create("https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/"
                + pairBases.keySet().toArray()[0] + "/" + pairBases.values().toArray()[0]);

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(uri).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            System.out.println("Erro! Não foi possível estabelecer uma conexão com a API." +
                    " Tente novamente mais tarde.");
            return "";
        }
    }
}
