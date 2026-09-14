package br.com.alura.screenmatch.service;


import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;



public class ConsultaGemini {
    private static final String API_KEY = System.getenv("GEMINI_API_KEY");
    public static String obterTraducao(String texto) {
        if (API_KEY == null || API_KEY.isBlank()) {
            throw new IllegalStateException("Variável de ambiente GEMINI_API_KEY não configurada.");
        }
        Client client = Client.builder().apiKey(API_KEY).build();

        GenerateContentResponse response =
                client.models.generateContent(
                        "gemini-3-flash-preview",
                        "Traduza para língua portuguesa o seguinte trecho da forma mais direta, não pergunte mais nada, apenas faça: " + texto,
                        null);

        return response.text();
    }
}