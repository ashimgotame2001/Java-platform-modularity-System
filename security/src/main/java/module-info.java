module security {
    requires spring.security.core;
    requires spring.context;
    requires spring.security.config;
    requires spring.security.web;
    requires lombok;
    requires spring.webmvc;


    requires repository;
    requires entity;
    requires config;

    exports com.swifttech.security;
    opens com.swifttech.security;
}