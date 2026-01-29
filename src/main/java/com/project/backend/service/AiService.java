package com.project.backend.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AiService {

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    // اسم الموديل اللي بتستخدميه
    private static final String MODEL = "gemini-2.5-flash";

    public String askGemini(String message) {
        try {
            HttpClient client = HttpClient.newHttpClient();

            JSONObject part = new JSONObject();
            part.put("text", message);

            JSONObject contentObj = new JSONObject();
            contentObj.put("parts", new JSONArray().put(part));

            JSONObject bodyObj = new JSONObject();
            bodyObj.put("contents", new JSONArray().put(contentObj));

            String url = "https://generativelanguage.googleapis.com/v1beta/models/"
                         + MODEL + ":generateContent?key=" + geminiApiKey;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(bodyObj.toString()))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // طباعة الرد كامل
            System.out.println("Gemini raw response: " + response.body());

            JSONObject respJson = new JSONObject(response.body());

            // استخراج النص الأول من الرد
            if (respJson.has("candidates")) {
                JSONObject first = respJson.getJSONArray("candidates").getJSONObject(0);
                JSONObject content = first.getJSONObject("content");
                if (content.has("parts")) {
                    return content.getJSONArray("parts").getJSONObject(0).getString("text");
                }
            }

            return "لم يتم العثور على رد صالح من AI: " + response.body();

        } catch (Exception e) {
            e.printStackTrace();
            return "حدث خطأ أثناء الاتصال بالـ AI";
        }
    }
}
