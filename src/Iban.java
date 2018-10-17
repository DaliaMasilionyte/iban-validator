public class Iban {

    private final String iban;
    private boolean isValid;


    public Iban(String iban) {
        this.iban = iban;
    }

    public void setValid(boolean isValid) {
        this.isValid = isValid;
    }

    public boolean getValid(){
        return isValid;
    }
}
