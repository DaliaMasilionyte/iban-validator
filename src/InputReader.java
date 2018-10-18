import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class InputReader {

    private Integer mode;
    private BufferedReader userInputReader =
            new BufferedReader(new InputStreamReader(System.in));


    public void selectMode() throws IOException {
        while(mode == null){
            System.out.println("Pasirinkite programos veikimo rėžimą: " +
                    "0 - interaktyvus veikimas; 1 - skaitymas iš failo.");
            String input = this.userInputReader.readLine();
            if(input.matches("[01]")){
                this.mode = Integer.parseInt(input);
            }
        }
    }

    public int getMode() {
        return mode;
    }

        public ArrayList<String> readInput() throws IOException {
        ArrayList<String> allIbans = new ArrayList<String>();
        switch(this.mode) {
            case 0:
                System.out.println("Įveskite IBAN.");
                String iban = this.userInputReader.readLine();
                allIbans.add(iban);
                break;
            case 1:
                System.out.println("Įveskite kelią iki failo.");
                String fileName = this.userInputReader.readLine();
                try {
                    BufferedReader fileReader =
                            new BufferedReader(new FileReader(fileName));
                    String line;
                    while((line = fileReader.readLine()) != null){
                        allIbans.add(line);
                    }
                    break;

                } catch(java.io.IOException error) {
                        System.err.println("Failas nerastas.");
                }

        }
        return allIbans;
    }

}
