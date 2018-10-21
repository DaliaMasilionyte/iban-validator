public class Iban {

    private static final int COUNTRY_CODE_LENGTH = 2;
    private static final int CHECK_DIGIT_LENGTH = 2;
    private static final int IBAN_PREFIX =
            COUNTRY_CODE_LENGTH + CHECK_DIGIT_LENGTH;
    private final String countryCode;
    private final String checkDigits;
    private final String bban;

    public Iban(String iban) {
        this.countryCode = iban.substring(0,COUNTRY_CODE_LENGTH);
        this.checkDigits = iban.substring(CHECK_DIGIT_LENGTH, IBAN_PREFIX);
        this.bban = iban.substring(IBAN_PREFIX);

    }
    String getCountryCode() {
        return countryCode;
    }

}
