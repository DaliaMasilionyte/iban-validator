import java.io.IOException;

class Main {

    public static void main(String[] args) throws IOException {

//        Select the method of checkDigitValidation
        CheckDigitValidation checkDigitValidation = new PieceWiseCheckDigitValidation();
//        CheckDigitValidation checkDigitValidation = new FullCheckDigitValidation();

        IbanValidator ibanValidator = new IbanValidator(checkDigitValidation);
        ibanValidator.startValidationService();

    }
}
