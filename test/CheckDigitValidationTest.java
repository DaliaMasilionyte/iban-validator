import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckDigitValidationTest {

    private static final CheckDigitValidation PIECE_WISE_CHECK_DIGIT_VALIDATION =
            new PieceWiseCheckDigitValidation();
    private static final CheckDigitValidation FULL_CHECK_DIGIT_VALIDATION =
            new FullCheckDigitValidation();
    private static final String VALID_IBAN = "LT517044077788877777";
    private static final String VALID_IBAN_NUMBER = "7044077788877777212951";

    @Test
    void convertIbanToNumber() {
        assertEquals(PIECE_WISE_CHECK_DIGIT_VALIDATION.convertIbanToNumber(VALID_IBAN),
                VALID_IBAN_NUMBER);
    }
    @Test
    void validateCheckDigitsPieceWise() {
        assertTrue(PIECE_WISE_CHECK_DIGIT_VALIDATION.validateCheckDigits(VALID_IBAN_NUMBER));
    }
    @Test
    void validateCheckDigitsInvalidPieceWise() {
        assertFalse(PIECE_WISE_CHECK_DIGIT_VALIDATION.validateCheckDigits("1245445455"));
    }

    @Test
    void validateCheckDigitsFull() {
        assertTrue(FULL_CHECK_DIGIT_VALIDATION.validateCheckDigits(VALID_IBAN_NUMBER));
    }
    @Test
    void validateCheckDigitsInvalidFull() {
        assertFalse(FULL_CHECK_DIGIT_VALIDATION.validateCheckDigits("1245445455"));
    }


}