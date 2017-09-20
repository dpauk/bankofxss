package com.possiblynothing.bankofxss.filters;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AllScriptsRemovedTest {
    private XssFilter xssFilter;

    public AllScriptsRemovedTest() {
    }

    @Before
    public void setUp() throws Exception {
        xssFilter = new AllScriptsRemoved();
    }

    @Test
    public void standardScriptTagsAreRemoved() throws Exception {
        assertEquals("alert(1);", xssFilter.applyFilter("<script>alert(1);</script>"));
    }

    @Test
    public void alternateCaseScriptTagsAreRemoved() throws Exception {
        assertEquals("alert(1);", xssFilter.applyFilter("<Script>alert(1);</Script>"));
    }

    @Test
    public void embeddedScriptTagsAreRemoved() throws Exception {
        assertEquals("alert(1);", xssFilter.applyFilter("<scr<script>ipt>alert(1);</scr</script>ipt>"));
    }

    @Test
    public void onmouseoverCausesXss() throws Exception {
        assertEquals("<div onmouseover=\"javascript:alert(1);\">test</div>", xssFilter.applyFilter("<div onmouseover=\"javascript:alert(1);\">test</div>"));
    }


}