module entity {
    requires lombok;
    requires jakarta.persistence;
    requires spring.data.jpa;
    requires spring.data.commons;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires org.hibernate.orm.core;
    requires org.mapstruct;
    requires spring.context;
    requires spring.security.core;
    requires com.fasterxml.jackson.annotation;
    requires spring.beans;

    exports com.swifttech.entity;
    exports com.swifttech.entity.mapper;
    exports com.swifttech.entity.enumuration;
    exports com.swifttech.entity.dto.request;
    exports com.swifttech.entity.dto.response;




    opens com.swifttech.entity;
    opens com.swifttech.entity.mapper;
    opens com.swifttech.entity.enumuration;
    opens com.swifttech.entity.dto.request;
    opens com.swifttech.entity.dto.response;





}