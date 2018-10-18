import java.math.BigDecimal;
import java.util.regex.Pattern;

public class IbanValidator {

    public static final int IBAN_NUMBER_MIN_LENGTH = 15;
    public static final int IBAN_NUMBER_MAX_LENGTH = 34;
    public static final int COUNTRY_CODE_LENGTH = 2;
    public static final int CHECK_DIGIT_LENGTH = 2;

    public InputReader inputReader = new InputReader();


    public boolean isLengthValid(String Iban){
        if (Iban.length() >= IBAN_NUMBER_MIN_LENGTH &&
                Iban.length() <= IBAN_NUMBER_MAX_LENGTH){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isAlphanumeric(String Iban){
        Pattern p = Pattern.compile("[^a-zA-Z0-9]");
//        True if has non alphanumeric symbols
        boolean nonAlphanumeric = p.matcher(Iban).find();
        return !nonAlphanumeric;
    }

    public BigDecimal convertStringToBigDecimal(String Iban){
        BigDecimal numericIban;



        return numericIban;
    }


    public boolean checkDigitValidation(BigDecimal numericIban){
        boolean valid;



        return valid;
    }

}
