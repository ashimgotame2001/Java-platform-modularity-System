module response {
    requires lombok;
    requires spring.boot;
    requires spring.context;

    exports com.swifttech.response;
    opens com.swifttech.response;
}