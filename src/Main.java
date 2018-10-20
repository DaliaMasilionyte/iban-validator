import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

//        Select the method of validation
        Validation validation = new PieceWiseIbanValidation();
//        Validation validation = new FullIbanValidation();

        IbanValidator ibanValidator = new IbanValidator(validation);
        ibanValidator.startValidationService();

    }
}
