package api;

import okhttp3.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ExchangeAPI {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/key/latest/USD";
    private static final OkHttpClient client = new OkHttpClient();

    
  
    public static void main(String[] args) {
        try {
            String jsonResponse = sendRequest();
            parseJson(jsonResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String sendRequest() throws IOException {
        Request request = new Request.Builder()
                .url(API_URL)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Erro na API " + response.code());
            }
            return response.body().string();
        }
    }
    public static void parseJson(String jsonResponse) throws IOException {
        System.out.println(jsonResponse);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonResponse);
        
        System.out.println("Chaves disponíveis no JSON: " + rootNode.fieldNames());

        if (rootNode.has("conversion_rates")) {
            JsonNode conversionRates = rootNode.get("conversion_rates");

            System.out.println("Moedas disponíveis: " + conversionRates.fieldNames());

            if (conversionRates.has("BRL")) {
                double taxaBRL = conversionRates.get("BRL").asDouble();
                System.out.println(taxaBRL);
            } else {
                System.out.println("Erro: BRL não encontrado em conversion_rates");
            }
        } else {
            System.out.println("Erro: conversion_rates não encontrado no JSON");
        }
    }

    public static Double getExchangeRate(String Moeda1, String Moeda2) throws IOException {
        String jsonResponse = sendRequest();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonResponse);

        if (rootNode.has("conversion_rates") && rootNode.get("conversion_rates").has(Moeda2)) {
            return rootNode.get("conversion_rates").get(Moeda2).asDouble();
        } else {
            System.out.println("Erro ao obter taxa de câmbio!");
            return 0.0;
        }
    }

}
