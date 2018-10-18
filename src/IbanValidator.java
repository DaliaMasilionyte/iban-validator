import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

public class IbanValidator {


    public static final int COUNTRY_CODE_LENGTH = 2;
    public static final int CHECK_DIGIT_LENGTH = 2;
    public static final int IBAN_PREFIX = COUNTRY_CODE_LENGTH + CHECK_DIGIT_LENGTH;
    public static final int CHECK_DIGIT_REMAINDER = 1;
    public static final BigInteger CHECK_DIGIT_MOD = new BigInteger("97");

    private DataHandler dataHandler = new DataHandler();



    public void convertIbanToNumeric(ValidatedIban ibanObject) {
        String iban = ibanObject.getIban();
        String reorderedIban = iban.substring(IBAN_PREFIX) + iban.substring(0, IBAN_PREFIX);
        String ibanNumber = "";

//         sukuriama char masyvo kopija, gal geriau paprastas for loop
//            Gal galima applyint ne visiem simboliam, o tik raidem

        for(char symbol: reorderedIban.toCharArray()){
//                All letters are converted to their numeric representation
            ibanNumber += Character.getNumericValue(symbol);
        }
        BigInteger numericIban = new BigInteger(ibanNumber);
        ibanObject.setNumericIban(numericIban);

    }

    public int modulus(BigInteger numericIban){
        return (numericIban.mod(CHECK_DIGIT_MOD)).intValue();
    }

    public boolean checkDigitValidation(BigInteger numericIban) {
        return modulus(numericIban) == CHECK_DIGIT_REMAINDER;
    }

    public String fileResultFormation(ValidatedIban ibanObject ){
        return ibanObject.getIban() + ";" +
                String.valueOf(ibanObject.isValid());
    }


    public void IbanValidationService() throws IOException {

        dataHandler.selectMode();
        do {
            ArrayList<String> listOfIbans = dataHandler.readInput();

            if (dataHandler.getMode() == 1) {
                dataHandler.createOutputFile();
            }

            ArrayList<ValidatedIban> ibanObjectList = new ArrayList();
            for (String ibanString : listOfIbans) {
                ValidatedIban validatedIban = new ValidatedIban(ibanString);
                ibanObjectList.add(validatedIban);
            }

            for (ValidatedIban ibanObject : ibanObjectList) {
                if (!ibanObject.checkFormat()) {
                    ibanObject.setValid(false);
                } else {
                    convertIbanToNumeric(ibanObject);
                    if (checkDigitValidation(ibanObject.getNumericIban())) {
                        ibanObject.setValid(true);
                    } else {
                        ibanObject.setValid(false);
                    }
                }
                if (dataHandler.getMode() == 0) {
                    dataHandler.writeToConsole(
                            String.valueOf(ibanObject.isValid()));
                } else {
                    dataHandler.writeToFile(fileResultFormation(ibanObject));
                }
            }
            if (dataHandler.getMode() == 1) {
                dataHandler.closeFile();
            }
        } while(dataHandler.getMode() == 0);
    }

}
