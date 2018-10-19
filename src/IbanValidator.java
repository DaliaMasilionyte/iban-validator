import java.io.IOException;
import java.util.ArrayList;

public class IbanValidator {

    private DataHandler dataHandler = new DataHandler();


    public void startValidationService() throws IOException {
        dataHandler.selectMode();
        do {
            ArrayList<String> listOfIbans = dataHandler.readInput();

            if (dataHandler.getMode() == 1) {
                dataHandler.createOutputFile();
            }
            ArrayList<ValidatedIban> ibanObjectList = new ArrayList();

//            Create ValidatedIban objects
            for (String ibanString : listOfIbans) {
                ValidatedIban validatedIban = new ValidatedIban(ibanString);
                ibanObjectList.add(validatedIban);
            }

            for (ValidatedIban ibanObject : ibanObjectList) {
                if (!ibanObject.checkFormat()) {
                    ibanObject.setValid(false);
                } else {

//                    Create PieceWiseIbanValidation object or
//                    FullIbanValidation object
                    Validation pieceWiseIbanValidation = new PieceWiseIbanValidation();
                    pieceWiseIbanValidation.validate(ibanObject);

                }
                if (dataHandler.getMode() == 0) {
                    dataHandler.print(
                            String.valueOf(ibanObject.isValid()));
                } else {
                    dataHandler.print(ibanObject.getIban() + ";" +
                            String.valueOf(ibanObject.isValid()));
                }
            }
            if (dataHandler.getMode() == 1) {
                dataHandler.closeFile();
            }
//            If interactive mode is selected, service has to be looped
        } while(dataHandler.getMode() == 0);
    }

}
