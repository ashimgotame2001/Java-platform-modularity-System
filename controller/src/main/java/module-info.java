module controller {
    requires lombok;
    requires spring.boot;
    requires spring.web;
    requires spring.context;

    opens com.swifttech.controller to spring.core, spring.beans, spring.context;
    exports com.swifttech.controller;

    requires service;
    requires response;
    requires entity;

    requires spring.beans;

}