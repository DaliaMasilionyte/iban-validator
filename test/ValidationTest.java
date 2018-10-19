import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidationTest {

    private static final Validation pieceWiseIbanValidation =
            new PieceWiseIbanValidation();
    private static final Validation fullIbanValidation =
            new FullIbanValidation();
    private static final String validIban = "LT517044077788877777";
    private static final String validIbanNumber = "7044077788877777212951";

    @Test
    void convertIbanToNumber() {
        assertEquals(pieceWiseIbanValidation.convertIbanToNumber(validIban),
                validIbanNumber);
    }
    @Test
    void validateCheckDigitsPieceWise() {
        assertTrue(pieceWiseIbanValidation.validateCheckDigits(validIbanNumber));
    }
    @Test
    void validateCheckDigitsInvalidPieceWise() {
        assertFalse(pieceWiseIbanValidation.validateCheckDigits("1245445455"));
    }

    @Test
    void validateCheckDigitsFull() {
        assertTrue(fullIbanValidation.validateCheckDigits(validIbanNumber));
    }
    @Test
    void validateCheckDigitsInvalidFull() {
        assertFalse(fullIbanValidation.validateCheckDigits("1245445455"));
    }


}