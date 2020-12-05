package by.vasiliuk.project.controller.validator;

public class ValidationProvider {

    public static final String VALID_USERNAME_REGEXP = "[a-zA-Z0-9/-]+([ ]*[a-zA-Z0-9/-]+)";
    public static final String VALID_EMAIL_REGEXP = "\\w{3,}@\\w+\\.[a-z]{2,}";
    public static final String VALID_PASSWORD_REGEXP = "[a-zA-Z0-9/-]+([0-9]*[a-zA-Z0-9/-]+)";
    private ValidationProvider() {}
}
