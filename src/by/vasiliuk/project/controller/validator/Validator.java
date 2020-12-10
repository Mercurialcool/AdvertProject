package by.vasiliuk.project.controller.validator;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static by.vasiliuk.project.controller.validator.ValidationProvider.*;

public class Validator {
    private static final int MAX_USERNAME_SIZE = 30;
    private static final int MIN_USERNAME_SIZE = 8;
    private static final int MAX_EMAIL_SIZE = 40;
    private static final int MIN_EMAIL_SIZE = 10;
    private static final int MAX_PASSWORD_SIZE = 40;
    private static final int MIN_PASSWORD_SIZE = 8;
    private static final long ONE_HOUR = TimeUnit.HOURS.toMillis(1);
    private static final long ONE_YEAR = TimeUnit.DAYS.toMillis(365);

    public static boolean isValidName(String username){
        return username.matches(VALID_USERNAME_REGEXP) && (username.length() <= MAX_USERNAME_SIZE
                && username.length() >= MIN_USERNAME_SIZE);
    }

    public static boolean isValidEmail(String email){
        return email.length() <= MAX_EMAIL_SIZE
                && (email.length() >= MIN_EMAIL_SIZE && email.matches(VALID_EMAIL_REGEXP));
    }

    public static boolean isValidPassword(String password){
        return password.matches(VALID_PASSWORD_REGEXP)&& (password.length() <= MAX_PASSWORD_SIZE
                &&password.length() >= MIN_PASSWORD_SIZE);
    }

    public boolean isValidTime(Date startDate, Date endDate) {
        Date currentDate = new Date();

        if(currentDate.getTime() - startDate.getTime() > ONE_HOUR){
            return false;
        }
        if(startDate.after(endDate)){ return false;
        }
        if(endDate.getTime() > startDate.getTime() + ONE_YEAR){
            return false;
        }
        return true;
    }
}