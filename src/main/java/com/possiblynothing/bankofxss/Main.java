package com.possiblynothing.bankofxss;

import com.possiblynothing.bankofxss.filters.AllScriptsRemoved;
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

        // disable the xss protection in Chrome
        before((req, res) -> res.header("X-XSS-Protection", "0"));

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
                case "medium_filter_1":
                    xssFilter = new AllScriptsRemoved();
                    username = xssFilter.applyFilter(req.queryParams(USERNAME));
                    break;
                default:
                    username = req.queryParams(USERNAME);
                    break;
            }
            model.put("username", username);
            return new ModelAndView(model, "results.hbs");
        }, new HandlebarsTemplateEngine());

        get("/help", (req, res) -> new ModelAndView(null, "help.hbs"), new HandlebarsTemplateEngine());
    }
}
