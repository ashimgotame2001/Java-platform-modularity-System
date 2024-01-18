module config {
    requires spring.boot.starter.web;
    requires spring.security.config;
    requires spring.security.core;
    requires spring.security.web;
    requires spring.boot.starter.security;
    requires spring.security.crypto;
    requires spring.context;
    requires lombok;
    requires spring.web;
    requires org.apache.tomcat.embed.core;
    requires spring.core;
    requires spring.beans;
    requires jjwt.api;
    requires jjwt.impl;
    requires jjwt.jackson;
    requires org.mapstruct;


    requires repository;
    requires spring.webmvc;
    requires entity;
    requires exceptions;

    exports com.swifttech.config;
    opens com.swifttech.config ;
}