package com.possiblynothing.bankofxss;

/**
 *
 * @author david
 */
public interface XssFilter {
    String applyFilter(String unfilteredString);
}
