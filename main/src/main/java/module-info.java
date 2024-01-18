module main {
    requires entity;
    requires controller;
    requires service;
    requires repository;
    requires config;
    requires security;
    requires exceptions;
    requires utils;

    exports com.swifttech.main;
    opens com.swifttech.main;

    requires spring.core;
    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires spring.beans;
    requires spring.context;
    requires spring.data.jpa;
    requires spring.web;
    requires spring.jdbc;
    requires jakarta.persistence;
    requires org.apache.tomcat.embed.core;
    requires spring.boot.starter.jdbc;
    requires spring.data.commons;
    requires org.hibernate.orm.core;
    requires spring.boot.starter.web;

    requires liquibase.core;

    requires io.swagger.v3.core;
    requires io.swagger.v3.oas.annotations;

}