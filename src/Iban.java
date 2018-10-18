import java.math.BigInteger;

public class Iban {

    private final String iban;
    private BigInteger numericIban;
    private boolean valid;


    public Iban(String iban) {
        this.iban = iban;
    }

    public String getIban() {
        return iban;
    }

    public BigInteger getNumericIban() {
        return numericIban;
    }

    public void setNumericIban(BigInteger numericIban) {
        this.numericIban = numericIban;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public boolean isValid(){
        return valid;
    }



}
