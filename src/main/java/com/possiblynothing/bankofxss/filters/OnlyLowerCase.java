package com.possiblynothing.bankofxss.filters;

/**
 *
 * @author david
 */
public class OnlyLowerCase implements XssFilter {

    @Override
    public String applyFilter(String unfilteredString) {
        // Test: <Script>alert(1);</Script>
        return unfilteredString.replaceAll("<\\/?script>", "");

    }

}
