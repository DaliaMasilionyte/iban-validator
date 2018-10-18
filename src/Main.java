import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        IbanValidator ibanValidator = new IbanValidator();
        ibanValidator.startValidationService();

    }
}
