/**
 * <code>abstract</code> class responsible for
 * check digit validation using modulo-97 operation.
 * Full algorithm defined at:
 * @see <a href="https://en.wikipedia.org/wiki/International_Bank_Account_Number#Validating_the_IBAN">Validating the IBAN</a>.
 *
 */

abstract class CheckDigitValidation {

    static final int CHECK_DIGIT_REMAINDER = 1;
    private static final int COUNTRY_CODE_LENGTH = 2;
    private static final int CHECK_DIGIT_LENGTH = 2;
    private static final int IBAN_PREFIX =
            COUNTRY_CODE_LENGTH + CHECK_DIGIT_LENGTH;

    /**
     * Converts the IBAN to reordered numeric representation for
     * further validation.
     * Second and third steps from the IBAN validation algorithm
     * as defined in the link above.
     *
     * @param iban original IBAN
     * @return string representation of reordered IBAN
     */
    String convertIbanToNumber(String iban) {
        String reorderedIban = iban.substring(IBAN_PREFIX) +
                iban.substring(0, IBAN_PREFIX);
        StringBuilder ibanNumber = new StringBuilder();

        for(char symbol: reorderedIban.toCharArray()){
            if(Character.isDigit(symbol)){
                ibanNumber.append(symbol);
            } else {
//          Letters are converted to their numeric representation
                ibanNumber.append(Character.getNumericValue(symbol));
            }
        }
        return String.valueOf(ibanNumber);
    }

    /**
     * Validates check digits.
     * Implementation:
     * @see PieceWiseCheckDigitValidation#validateCheckDigits(String)
     * @see FullCheckDigitValidation#validateCheckDigits(String)
     * @param ibanNumber numeric reordered IBAN
     * @return
     */
    protected abstract boolean validateCheckDigits(String ibanNumber);

    /**
     * Validates the given IBAN.
     * Calls conversion to numeric method.
     * @see CheckDigitValidation#convertIbanToNumber(String)
     * Validates the check digits.
     * @see CheckDigitValidation#validateCheckDigits(String)
     *
     * @param ibanObject ValidatedIban class object
     */
    boolean isValid(ValidatedIban ibanObject){
        String ibanNumber = convertIbanToNumber(ibanObject.getIban());
        return validateCheckDigits(ibanNumber);
    }


}
