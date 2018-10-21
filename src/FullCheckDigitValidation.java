import java.math.BigInteger;

/**
 * BigInteger class allows allocating as much memory as needed,
 * therefore full number can be validated in one step.
 */


public class FullCheckDigitValidation extends CheckDigitValidation {

    private static final BigInteger CHECK_DIGIT_MOD = new BigInteger("97");

    /**
     * Validates the check digits using one step validation.
     *
     * @param ibanNumber numeric reordered IBAN
     * @return validity of the IBAN
     */
    @Override
    protected boolean validateCheckDigits(String ibanNumber) {
        BigInteger numericIban = new BigInteger(ibanNumber);
        return (numericIban.mod(CHECK_DIGIT_MOD)).intValue() ==
                CHECK_DIGIT_REMAINDER;
    }
}
