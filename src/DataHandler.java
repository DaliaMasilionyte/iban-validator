import java.io.*;
import java.util.ArrayList;


public class DataHandler {

    private static final String OUTPUT_FILE_EXTENSION = ".out";
    private Integer mode;
    private String inputFile;
    private String outputFile;
    private BufferedReader userInputReader =
            new BufferedReader(new InputStreamReader(System.in));
    private Writer writer;


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

    public String normalize(String string){
        string = string.toUpperCase();
        return string;
    }

    public ArrayList<String> readInput() throws IOException {
        ArrayList<String> allIbans = new ArrayList();
        String iban;
        switch(mode) {
            case 0:
                System.out.println("Įveskite IBAN.");
                iban = this.userInputReader.readLine();
                iban = normalize(iban);
                allIbans.add(iban);
                break;
            case 1:
                System.out.println("Įveskite kelią iki failo.");
                this.inputFile = this.userInputReader.readLine();
                try {
                    BufferedReader fileReader =
                            new BufferedReader(new FileReader(inputFile));
                    while((iban = fileReader.readLine()) != null){
                        iban = normalize(iban);
                        allIbans.add(iban);
                    }
                    break;

                } catch(java.io.IOException error) {
                    System.err.println("Failas nerastas.");
                }
        }
        return allIbans;
    }

    public void writeToConsole(String line){
        System.out.println(line);
    }

    public void createOutputFile() throws IOException {
        if (inputFile.contains(".")) {
            inputFile = inputFile.substring(0, inputFile.lastIndexOf('.'));
        }
        this.outputFile = inputFile + OUTPUT_FILE_EXTENSION;
        this.writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(outputFile), "utf-8"));

    }

    public void writeToFile(String line) throws IOException {
        writer.write(line + "\n");

    }
    public void closeFile() throws IOException {
        writer.close();
    }

}
