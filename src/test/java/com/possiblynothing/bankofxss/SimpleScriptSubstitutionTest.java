/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.possiblynothing.bankofxss;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author david
 */
public class SimpleScriptSubstitutionTest {
    private XssFilter xssFilter;
    
    public SimpleScriptSubstitutionTest() {
    }
    
    @Before
    public void setUp() throws Exception {
        xssFilter = new SimpleScriptSubstitution();
    }
    
    @Test
    public void standardScriptTagsAreRemoved() throws Exception {  
        assertEquals("alert(1);", xssFilter.applyFilter("<script>alert(1);</script>"));
    }
    
    @Test
    public void embeddedScriptTagsCauseXss() throws Exception {  
        assertEquals("<script>alert(1);</script>", xssFilter.applyFilter("<scr<script>ipt>alert(1);</scr</script>ipt>"));
    }
}
