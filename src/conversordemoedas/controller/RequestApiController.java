package conversordemoedas.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import conversordemoedas.model.RequestApi;

import java.util.HashMap;
import java.util.Properties;

public class RequestApiController {
    public double getExchangeRate(HashMap<String, String> pairBases, String apiKey) {
        try {
            RequestApi requestApi = new RequestApi(apiKey);
            String json = requestApi.getRequest(pairBases);
            Gson gson = new GsonBuilder().create();
            Properties fields = gson.fromJson(json, Properties.class);
            return Double.parseDouble(fields.getProperty("conversion_rate"));
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Erro! Entrada do par de códigos bases inválida! ");
            return 0;
        }
    }
}
