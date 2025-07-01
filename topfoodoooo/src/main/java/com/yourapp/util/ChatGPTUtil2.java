package com.yourapp.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public class ChatGPTUtil2 {
    public static String askGpt(String prompt, String apiKey) throws IOException {
        URL url = new URL("https://api.openai.com/v1/chat/completions");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + "apiKey");
        con.setDoOutput(true);

        String jsonInput = """
        {
          "model": "gpt-4o",
          "messages": [{"role": "user", "content": "%s"}]
        }
        """.formatted(prompt);

        try (OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInput.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
            return br.lines().collect(Collectors.joining());
        }
    }
    
}
