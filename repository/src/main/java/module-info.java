module repository {
    exports com.swifttech.repository;
    opens com.swifttech.repository;

    requires lombok;
    requires spring.data.jpa;
    requires spring.web;
    requires spring.context;
    requires spring.boot;
    requires spring.data.commons;

    requires entity;
}