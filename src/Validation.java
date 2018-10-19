public abstract class Validation {

    static final int CHECK_DIGIT_REMAINDER = 1;
    private static final int COUNTRY_CODE_LENGTH = 2;
    private static final int CHECK_DIGIT_LENGTH = 2;
    private static final int IBAN_PREFIX = COUNTRY_CODE_LENGTH + CHECK_DIGIT_LENGTH;


    protected String convertIbanToNumber(String iban) {
        String reorderedIban = iban.substring(IBAN_PREFIX) +
                iban.substring(0, IBAN_PREFIX);
        StringBuilder ibanNumber = new StringBuilder();

        for(char symbol: reorderedIban.toCharArray()){
//                All letters are converted to their numeric representation
            if(Character.isDigit(symbol)){
                ibanNumber.append(symbol);
            } else {
                ibanNumber.append(Character.getNumericValue(symbol));
            }
        }
        return String.valueOf(ibanNumber);
    }

    protected abstract boolean validateCheckDigits(String ibanNumber);

    void validate(ValidatedIban ibanObject){
        String ibanNumber = convertIbanToNumber(ibanObject.getIban());
        if (validateCheckDigits(ibanNumber)) {
            ibanObject.setValid(true);
        } else {
            ibanObject.setValid(false);
        }
    }


}
