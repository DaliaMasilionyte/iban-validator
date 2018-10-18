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
    public static final BigInteger CHECK_DIGIT_MOD = new BigInteger("97");
    public static final int CHECK_DIGIT_REMAINDER = 1;

    private DataHandler dataHandler = new DataHandler();



    public boolean isLengthValid(String Iban){
        if (Iban.length() >= IBAN_NUMBER_MIN_LENGTH &&
                Iban.length() <= IBAN_NUMBER_MAX_LENGTH){
            return true;
        } else {
            return false;
        }
    }

    public boolean isAlphanumeric(String Iban){
        Pattern p = Pattern.compile("[^a-zA-Z0-9]");
//        True if has non alphanumeric symbols
        boolean nonAlphanumeric = p.matcher(Iban).find();
        return !nonAlphanumeric;
    }

//        public BigInteger convertIbanToNumeric(Iban Iban){
        public void convertIbanToNumeric(Iban Iban){
            String iban = Iban.getIban();
            String reorderedIban = iban.substring(IBAN_PREFIX) + iban.substring(0, IBAN_PREFIX);
            String ibanNumber = "";

//          TODO: sukuriama char masyvo kopija, gal geriau paprastas for loop
//            Gal galima applyint ne visiem simboliam, o tik raidem
            for(char symbol: reorderedIban.toCharArray()){
//                All letters are converted to their numeric representation
                ibanNumber += Character.getNumericValue(symbol);
            }
            BigInteger numericIban = new BigInteger(ibanNumber);
            Iban.setNumericIban(numericIban);
//        return numericIban;
    }

    public int modulus(BigInteger numericIban){
        return (numericIban.mod(CHECK_DIGIT_MOD)).intValue();
    }

    public boolean checkDigitValidation(BigInteger numericIban){
        boolean valid = false;
        if(modulus(numericIban) == CHECK_DIGIT_REMAINDER){
            valid = true;
        }
        return valid;
    }


    public void IbanValidationService() throws IOException {

        dataHandler.selectMode();
        if(dataHandler.getMode() == 0){
            dataHandler.createOutputFile();
        }

        ArrayList<String> listOfIbans = dataHandler.readInput();

        ArrayList<Iban> ibans = new ArrayList<Iban>();
        for(String ibanString : listOfIbans) {
            Iban iban = new Iban(ibanString);
            ibans.add(iban);
        }

        for(Iban iban: ibans){
            String ibanValidity = iban.getIban() + ';';
            if(isLengthValid(iban.getIban()) && isAlphanumeric(iban.getIban())){
                convertIbanToNumeric(iban);
                if(checkDigitValidation(iban.getNumericIban())){
                    ibanValidity += "true\n";
                }else{
                    ibanValidity += "false\n";
                }
            }else{
                ibanValidity += "false\n";
            }
            if(dataHandler.getMode() == 0){
                dataHandler.writeToConsole(ibanValidity);
            } else{
                dataHandler.writeToFile(ibanValidity);
                }
        }
    }
}
