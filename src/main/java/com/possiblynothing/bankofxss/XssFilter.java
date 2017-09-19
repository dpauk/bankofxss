package com.possiblynothing.bankofxss;

/**
 *
 * @author david
 */
public interface XssFilter {
    public String applyFilter(String unfilteredString);
}
