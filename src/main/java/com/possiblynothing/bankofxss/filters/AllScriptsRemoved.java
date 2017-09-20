package com.possiblynothing.bankofxss.filters;

/**
 *
 * @author david
 */
public class AllScriptsRemoved implements XssFilter {

    @Override
    public String applyFilter(String unfilteredString) {
        // Test: <div onmouseover="javascript:alert(1);">test</div>
        String findPattern = "(?i).*<\\/?script>.*";
        String replacePattern = "(?i)<\\/?script>";

        while (unfilteredString.matches(findPattern)) {
            unfilteredString = unfilteredString.replaceAll(replacePattern, "");
        }

        return unfilteredString;

    }

}
