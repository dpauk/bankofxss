package com.possiblynothing.bankofxss;

import com.possiblynothing.bankofxss.filters.OnlyLowerCase;
import com.possiblynothing.bankofxss.filters.SimpleScriptSubstitution;
import com.possiblynothing.bankofxss.filters.XssFilter;
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
            XssFilter xssFilter;
            switch (req.queryParams("difficulty")) {
                case "no_filter":
                    username = req.queryParams(USERNAME);
                    break;
                case "simple_filter_1":
                    xssFilter = new OnlyLowerCase();
                    username = xssFilter.applyFilter(req.queryParams(USERNAME));
                    break;
                case "simple_filter_2":
                    xssFilter = new SimpleScriptSubstitution();
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
