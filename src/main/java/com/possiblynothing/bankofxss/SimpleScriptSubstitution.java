package com.possiblynothing.bankofxss;

/**
 *
 * @author david
 */
public class SimpleScriptSubstitution implements XssFilter {

    @Override
    public String applyFilter(String unfilteredString) {
        String openingScriptFilter = unfilteredString.replace("<script>", "");
        return openingScriptFilter.replace("</script>", "");
    }

}
