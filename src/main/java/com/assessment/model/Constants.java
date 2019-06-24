package com.assessment.model;

/**
 * General constants for use across the entire project.
 */
public class Constants {

    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5*60*60;
    public static final String SIGNING_KEY = "devassess427r";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String AUTHORITIES_KEY = "scopes";
    
    /**
     * The version of the REST APIs
     */
    public static final String API_VERSION_ONE = "v1";
    
    /**
     * The root of all REST API URLs
     */
    public static final String API_PREFIX = "/api/" + API_VERSION_ONE;
}
