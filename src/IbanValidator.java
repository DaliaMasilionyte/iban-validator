import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class IbanValidator {

    public static final int IBAN_NUMBER_MIN_LENGTH = 15;
    public static final int IBAN_NUMBER_MAX_LENGTH = 34;
    public static final int COUNTRY_CODE_LENGTH = 2;
    public static final int CHECK_DIGIT_LENGTH = 2;
    public static final int IBAN_PREFIX = COUNTRY_CODE_LENGTH + CHECK_DIGIT_LENGTH;
    public static final int CHECK_DIGIT_REMAINDER = 1;
    public static final BigInteger CHECK_DIGIT_MOD = new BigInteger("97");

    private DataHandler dataHandler = new DataHandler();

    public boolean isLengthValid(String Iban) {
        return Iban.length() >= IBAN_NUMBER_MIN_LENGTH &&
                Iban.length() <= IBAN_NUMBER_MAX_LENGTH;
    }

    public boolean isAlphanumeric(String Iban) {
        Pattern p = Pattern.compile("[^a-zA-Z0-9]");
//        True if has non alphanumeric symbols
        boolean nonAlphanumeric = p.matcher(Iban).find();
        return !nonAlphanumeric;
    }


    public void convertIbanToNumeric(Iban Iban) {
        String iban = Iban.getIban();
        String reorderedIban = iban.substring(IBAN_PREFIX) + iban.substring(0, IBAN_PREFIX);
        String ibanNumber = "";

//         sukuriama char masyvo kopija, gal geriau paprastas for loop
//            Gal galima applyint ne visiem simboliam, o tik raidem

        for(char symbol: reorderedIban.toCharArray()){
//                All letters are converted to their numeric representation
            ibanNumber += Character.getNumericValue(symbol);
        }
        BigInteger numericIban = new BigInteger(ibanNumber);
        Iban.setNumericIban(numericIban);

    }

    public int modulus(BigInteger numericIban){
        return (numericIban.mod(CHECK_DIGIT_MOD)).intValue();
    }

    public boolean checkDigitValidation(BigInteger numericIban) {
        return modulus(numericIban) == CHECK_DIGIT_REMAINDER;
    }

    public String fileResultFormation(Iban iban ){
        String result = iban.getIban() + ";";
        result += String.valueOf(iban.isValid());
        return result;
    }


    public void IbanValidationService() throws IOException {

        dataHandler.selectMode();
        do {
            ArrayList<String> listOfIbans = dataHandler.readInput();

            if (dataHandler.getMode() == 1) {
                dataHandler.createOutputFile();
            }

            ArrayList<Iban> ibans = new ArrayList();
            for (String ibanString : listOfIbans) {
                Iban iban = new Iban(ibanString);
                ibans.add(iban);
            }

            for (Iban iban : ibans) {
                if (!isLengthValid(iban.getIban()) || !isAlphanumeric(iban.getIban())) {
                    iban.setValid(false);
                } else {
                    convertIbanToNumeric(iban);
                    if (checkDigitValidation(iban.getNumericIban())) {
                        iban.setValid(true);
                    } else {
                        iban.setValid(false);
                    }
                }
                if (dataHandler.getMode() == 0) {
                    dataHandler.writeToConsole(String.valueOf(iban.isValid()));
                } else {
                    dataHandler.writeToFile(fileResultFormation(iban));
                }
            }
            if (dataHandler.getMode() == 1) {
                dataHandler.closeFile();
            }
        } while(dataHandler.getMode() == 0);
    }

}
