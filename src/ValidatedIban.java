import java.util.regex.Pattern;

public class ValidatedIban {

    public static final int IBAN_NUMBER_MIN_LENGTH = 15;
    public static final int IBAN_NUMBER_MAX_LENGTH = 34;
    private final String iban;
    private boolean valid;


    public ValidatedIban(String iban) {
        this.iban = iban;
    }

    public String getIban() {
        return iban;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public boolean isValid(){
        return valid;
    }
    public boolean checkFormat() {
        Pattern p = Pattern.compile("[^a-zA-Z0-9]");
        return !p.matcher(iban).find() &&
                iban.length() >= IBAN_NUMBER_MIN_LENGTH &&
                iban.length() <= IBAN_NUMBER_MAX_LENGTH;
    }


}
