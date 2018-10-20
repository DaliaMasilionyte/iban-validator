import java.math.BigInteger;


public class FullIbanValidation extends Validation {

    private static final BigInteger CHECK_DIGIT_MOD = new BigInteger("97");

    @Override
    protected boolean validateCheckDigits(String ibanNumber) {
        BigInteger numericIban = new BigInteger(ibanNumber);
        return (numericIban.mod(CHECK_DIGIT_MOD)).intValue() ==
                CHECK_DIGIT_REMAINDER;
    }
}
