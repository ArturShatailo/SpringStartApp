package com.training.springstart.service;

import javax.servlet.http.HttpServletResponse;

public interface CookieFactory {

    /**
     * Creates new cookie record according to received data and adds created Cookie object into response
     * object of servlet.
     *
     * @param R HttpServletResponse object where Cookie object will be added
     * @param n name of cookie record to be created
     * @param v value of cookie record to be created
     * @param d integer value of maximum age of cookie record that will be created in seconds.
     */
    void setCookie(HttpServletResponse R, String n, String v, int d);

}
