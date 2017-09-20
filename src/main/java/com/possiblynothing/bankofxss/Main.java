package com.possiblynothing.bankofxss;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class Main {
    private static final String USERNAME = "username";

    public static void main(String[] args) {
        staticFileLocation("/public");

        get("/", (req, res) -> new ModelAndView(null, "index.hbs"), new HandlebarsTemplateEngine());

        get("/sign-in", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String username;
            switch (req.queryParams("difficulty")) {
                case "no_filter":
                    username = req.queryParams(USERNAME);
                    break;
                case "simple_filter_1":
                    XssFilter xssFilter = new SimpleScriptSubstitution();
                    username = xssFilter.applyFilter(req.queryParams(USERNAME));
                    break;
                default:
                    username = req.queryParams(USERNAME);
                    break;
            }
            model.put("username", username);
            return new ModelAndView(model, "request-credentials.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
