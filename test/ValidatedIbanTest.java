import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatedIbanTest {
    private ValidatedIban validatedIban;

    @Test
    void checkFormatShortLength() {
        validatedIban = new ValidatedIban("A12345");
        assertFalse(validatedIban.checkFormat());
    }

    @Test
    void checkFormatLongLength() {
        validatedIban =
                new ValidatedIban("AA12345123451234512345123451234512345");
        assertFalse(validatedIban.checkFormat());
    }

    @Test
    void checkFormatNonAlphanumeric() {
        validatedIban = new ValidatedIban("AA1!345123412341234");
        assertFalse(validatedIban.checkFormat());
    }

}