package com.myapp;

import com.google.gson.Gson;
import java.util.List;
import java.util.Map;
import java.util.Random;
import static spark.Spark.*;

public class App {
    private static final List<String> QUOTES = List.of(
        "Simplicity is the soul of efficiency.",
        "Make it work, make it right, make it fast.",
        "Code is like humor. When you have to explain it, it's bad.",
        "First, solve the problem. Then, write the code.",
        "Talk is cheap. Show me the code."
    );

    public static void main(String[] args) {
        port(8080);
        Gson gson = new Gson();
        Random rand = new Random();

        // Root endpoint
        get("/", (req, res) -> {
            res.type("application/json");
            String quote = QUOTES.get(rand.nextInt(QUOTES.size()));
            return gson.toJson(Map.of(
                "123", "STU-2026-001",
                "Janiya", "Your Name",
                "message", quote
            ));
        });

        // Health endpoint
        get("/health", (req, res) -> {
            res.type("application/json");
            return gson.toJson(Map.of("status", "ok"));
        });

        System.out.println("✅ Quote API started on http://localhost:8080");
    }
}