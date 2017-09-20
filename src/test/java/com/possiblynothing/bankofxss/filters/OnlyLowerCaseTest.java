package com.possiblynothing.bankofxss.filters;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OnlyLowerCaseTest {
    private XssFilter xssFilter;

    public OnlyLowerCaseTest() {
    }

    @Before
    public void setUp() throws Exception {
        xssFilter = new OnlyLowerCase();
    }

    @Test
    public void standardScriptTagsAreRemoved() throws Exception {
        assertEquals("alert(1);", xssFilter.applyFilter("<script>alert(1);</script>"));
    }

    @Test
    public void alternateCaseScriptTagsCauseXss() throws Exception {
        assertEquals("<Script>alert(1);</Script>", xssFilter.applyFilter("<Script>alert(1);</Script>"));
    }
}