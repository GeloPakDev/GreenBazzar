package com.example.webapplication.controller.filter.xss;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

/**
 * The wrapper overrides the getParameterValues(), getParameter()
 * and getHeader() methods to execute the filtering before returning
 * the desired field to the caller. The actual XSS checking and
 * striping is performed in the stripXSS() private method.
 */
public class XssRequestWrapper extends HttpServletRequestWrapper {
    private static final String CLOSE_TAG = ">";
    private static final String OPEN_TAG = "<";
    private static final String EMPTY_STRING = "";

    public XssRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        if (values == null) {
            return new String[0];
        }
        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = stripXss(values[i]);
        }
        return encodedValues;
    }

    @Override
    public String getParameter(String parameter) {
        return stripXss(super.getParameter(parameter));
    }

    @Override
    public String getHeader(String name) {
        return stripXss(super.getHeader(name));
    }

    private String stripXss(String value) {
        if (value == null) {
            return null;
        }
        return value.replace(OPEN_TAG, EMPTY_STRING).replace(CLOSE_TAG, EMPTY_STRING);
    }
}
