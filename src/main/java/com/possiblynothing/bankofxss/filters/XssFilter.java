package com.possiblynothing.bankofxss.filters;

/**
 *
 * @author david
 */
public interface XssFilter {
    String applyFilter(String unfilteredString);
}
