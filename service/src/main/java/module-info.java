module service {
    requires lombok;
    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires spring.context;
    requires spring.web;
    requires spring.core;
    requires spring.beans;
    requires spring.security.crypto;
    requires spring.security.core;


    requires repository;
    requires entity;
    requires response;
    requires exceptions;
    requires config;
    requires utils;


    opens com.swifttech.service;
    exports com.swifttech.service to controller;

    opens com.swifttech.service.impl;
    exports com.swifttech.service.impl to controller;
}