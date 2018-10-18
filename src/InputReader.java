import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputReader {

    private int mode;
    private BufferedReader userInputReader =
            new BufferedReader(new InputStreamReader(System.in));

    public InputReader(){

    }

    public void selectMode() throws IOException {
        System.out.println("Pasirinkite programos veikimo rėžimą: " +
                "0 - interaktyvus veikimas; 1 - skaitymas iš failo.");
        String input = this.userInputReader.readLine();
        if(input.matches("0|1")){
            this.mode = Integer.parseInt(input);
        }
    }

    public int getMode() {
        return mode;
    }

        public String readInput() throws IOException {
        switch(this.mode) {
            case 0:
                String iban = this.userInputReader.readLine();
                return iban;
            case 1:
                String fileName = this.userInputReader.readLine();
                try {
                    BufferedReader fileReader =
                            new BufferedReader(new FileReader(fileName));
                    String line;
                    while((line = fileReader.readLine()) != null){
                        line = fileReader.readLine();
                    }



                } catch(java.io.IOException error)
                    {
                        System.err.println("Failas nerastas");
                }

        }
    }

}
