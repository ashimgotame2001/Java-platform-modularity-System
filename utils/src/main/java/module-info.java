module utils {
    exports com.swifttech.utils;
    opens com.swifttech.utils to service;

    requires spring.boot;
    requires spring.context;
    requires lombok;

    requires entity;
    requires repository;
    requires exceptions;
    requires spring.web;

}