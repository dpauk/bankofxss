package com.possiblynothing.bankofxss;

/**
 *
 * @author david
 */
public class SimpleScriptSubstitution implements XssFilter {

    @Override
    public String applyFilter(String unfilteredString) {
        // Test: <scr<script>ipt>alert(1);</scr</script>ipt>
        return unfilteredString.replaceAll("<\\/?script>", "");

    }

}
