import org.junit.jupiter.api.Test;
import regex.Regex;

import static org.junit.jupiter.api.Assertions.*;

public class RegexTest {

    @Test
    public void testNigeriaNumber() {

        String number = "08012345910";
        String number2 = "07055667789";
        assertTrue(Regex.isPhoneNumberValid(number));
        assertTrue(Regex.isNumberValid(number2));

    }

    @Test
    public void testEmailValidation(){

        String email = "edwardmichael953@gmail.com";
        String email1 = "Silver69@semicolon.africa";
        String email2 = "-------@gmail.com";
        assertTrue(Regex.isEmailValid(email));
        assertTrue(Regex.isEmailValid(email1));
        assertFalse(Regex.isEmailValid(email2));

    }

}
