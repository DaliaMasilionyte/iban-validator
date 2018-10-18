import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {

        IbanValidator ibanValidator = new IbanValidator();
        ibanValidator.IbanValidationService();
//        InputReader inputReader = new InputReader();
//
//        inputReader.selectMode();
//        ArrayList<String> listOfIbans = inputReader.readInput();
//
//        for(String iban : listOfIbans) {
//            System.out.println(iban);
//        }
//
//

    }
}
