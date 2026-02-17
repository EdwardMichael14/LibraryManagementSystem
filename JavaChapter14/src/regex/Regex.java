package regex;

public class Regex {

    public static boolean isPhoneNumberValid(String phoneNumber) {
        String pattern = "(080\\d{8})|(\\+235-*[0-9]{8})";
        return phoneNumber.matches(pattern);
    }
    public static boolean isNumberValid(String phoneNumber) {
        String pattern = "(070\\d{8})|(\\+235-*[0-9]{8})";
        return phoneNumber.matches(pattern);
    }
    public static boolean isEmailValid(String email) {
        String emailPattern = "^[A-Za-z0-9][A-Za-z0-9+_.-]*@[A-Za-z0-9.-]+$";
        ;
        return email.matches(emailPattern);
    }
}
