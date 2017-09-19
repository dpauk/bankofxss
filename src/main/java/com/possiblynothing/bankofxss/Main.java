package com.possiblynothing.bankofxss;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        staticFileLocation("/public");

        get("/", (req, res) -> new ModelAndView(null, "index.hbs"), new HandlebarsTemplateEngine());

        get("/sign-in", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("username", req.queryParams("username"));
            return new ModelAndView(model, "request-credentials.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
