import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Class responsible for handling the input and output.
 */
class DataHandler {

    private static final String OUTPUT_FILE_EXTENSION = ".out";
    private Integer mode;
    private String inputFileName;
    private final BufferedReader userInputReader =
            new BufferedReader(new InputStreamReader(System.in));
    private Writer writer;

    /**
     * Defines the mode of the program for reading input and writing output.
     * @see DataHandler#readInput()
     * @see DataHandler#print(String)
     *
     * @throws IOException If an input or output
     *                     exception occurred
     */
    void selectMode() throws IOException {
        while(mode == null){
            System.out.println("Pasirinkite programos veikimo rėžimą: " +
                    "0 - interaktyvus veikimas; 1 - skaitymas iš failo.");
            String input = this.userInputReader.readLine();
            if(input.matches("[01]")){
                this.mode = Integer.parseInt(input);
            }
        }
    }

    int getMode() {
        return mode;
    }

    /**
     * Reads the input and retrieves IBANs from it.
     * Works in two modes which were selected.
     * @see DataHandler#selectMode()
     *
     * @return IBANS from input
     * @throws IOException If an input or output
     *                     exception occurred
     */
    ArrayList<String> readInput() throws IOException {
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

    /**
     * Creates output file with defined extension.
     *
     * @throws IOException If output exception occurred
     */
    void createOutputFile() throws IOException {
        if (inputFileName.contains(".")) {
            inputFileName = inputFileName.substring(0,
                    inputFileName.lastIndexOf('.'));
        }
        String outputFile = inputFileName + OUTPUT_FILE_EXTENSION;
        this.writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(outputFile), StandardCharsets.UTF_8));
    }

    /**
     * Prints either to standard output or file defined by the mode of program.
     * @see DataHandler#selectMode()
     *
     * @param line output to be printed
     */
    void print(String line){
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

    /**
     * Closes the output stream.
     *
     * @throws IOException If output exception occurred
     */
    void closeFile() throws IOException {
        writer.close();
    }

}
