module exceptions {
    requires lombok;
    requires spring.context;
    requires spring.web;

    exports com.swifttech.exceptions;
    opens com.swifttech.exceptions;
}