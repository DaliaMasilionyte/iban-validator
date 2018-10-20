import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


class DataHandler {

    private static final String OUTPUT_FILE_EXTENSION = ".out";
    private Integer mode;
    private String inputFileName;
    private final BufferedReader userInputReader =
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

    public ArrayList<String> readInput() throws IOException {
        ArrayList<String> allIbans = new ArrayList<>();
        String iban;
        switch(mode) {
            case 0:
                System.out.println("Įveskite IBAN.");
                iban = userInputReader.readLine();
                iban = iban.toUpperCase();
                allIbans.add(iban);
                break;
            case 1:
                System.out.println("Įveskite kelią iki failo.");
                this.inputFileName = userInputReader.readLine();
                try {
                    BufferedReader fileReader =
                            new BufferedReader(new FileReader(inputFileName));
                    File inputFile = new File(inputFileName);
                    if(inputFile.length() == 0){
                        throw new Exception("Failas tuščias");
                    }
                    while((iban = fileReader.readLine()) != null){
                        iban = iban.toUpperCase();
                        allIbans.add(iban);
                    }
                    break;
                } catch(java.io.IOException error) {
                    System.err.println("Failas nerastas.");
                    System.exit(1);
                } catch (Exception e) {
                    System.err.println("Failas tuščias.");
                    System.exit(1);
                }
        }
        return allIbans;
    }

    public void createOutputFile() throws IOException {
        if (inputFileName.contains(".")) {
            inputFileName = inputFileName.substring(0,
                    inputFileName.lastIndexOf('.'));
        }
        String outputFile = inputFileName + OUTPUT_FILE_EXTENSION;
        this.writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(outputFile), StandardCharsets.UTF_8));
    }

    public void print(String line){
        switch(mode) {
            case 0:
                System.out.println(line);
                break;
            case 1:
                try {
                    writer.write(line + "\n");
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public void closeFile() throws IOException {
        writer.close();
    }

}
