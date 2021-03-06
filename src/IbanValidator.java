import java.io.IOException;
import java.util.ArrayList;


class IbanValidator {

    private static final DataHandler dataHandler = new DataHandler();
    private final Validation checkDigitValidation;


    /**
     * Class constructor with predefined method of check digit validation method.
     */
    public IbanValidator(){
        this.checkDigitValidation = new PieceWiseCheckDigitValidation();
    }
    /**
     * Class constructor specifying a check digit validation method.
     *
     * @param checkDigitValidation chosen method of validating the check digits
     */
    public IbanValidator(CheckDigitValidation checkDigitValidation){
        this.checkDigitValidation = checkDigitValidation;
    }

    /**
     * Works as a service of IBAN validation.
     * Loops if the mode is interactive.
     *
     * @throws IOException If an input or output
     *                     exception occurred
     */
    public void startValidationService() throws IOException {
        dataHandler.selectMode();
        do {
            ArrayList<String> listOfIbans = dataHandler.readInput();

            if (dataHandler.getMode() == 1) {
                dataHandler.createOutputFile();
            }
            ArrayList<ValidatedIban> ibanObjectList = new ArrayList<>();

            for (String ibanString : listOfIbans) {
                ValidatedIban validatedIban = new ValidatedIban(ibanString);
                ibanObjectList.add(validatedIban);
            }

            for (ValidatedIban ibanObject : ibanObjectList) {
//                Format validation
                if (!ibanObject.checkFormat()) {
                    ibanObject.setValid(false);

                } else {
//                    Country code validation
                    Validation countryCodeValidation = new CountryCodeValidation();
                    if(!countryCodeValidation.isValid(ibanObject)){
                        ibanObject.setValid(false);

                    }  else {
//                        Check digit validation
                        ibanObject.setValid(checkDigitValidation.isValid(ibanObject));
                    }
                }
                switch (dataHandler.getMode()){
                    case 0:
                        dataHandler.print(String.valueOf(ibanObject.isValid()));
                        break;
                    case 1:
                        dataHandler.print(ibanObject.getIban() + ";" +
                            String.valueOf(ibanObject.isValid()));
                        break;
                }
            }
            if(dataHandler.getMode() == 1){
                dataHandler.closeFile();
            }
//            If interactive mode is selected, service has to be looped
        } while(dataHandler.getMode() == 0);
    }






}
