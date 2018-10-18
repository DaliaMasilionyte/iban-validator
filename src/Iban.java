public class Iban {

    private final String iban;
    private boolean valid;


    public Iban(String iban) {
        this.iban = iban;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public boolean isValid(){
        return valid;
    }
}
