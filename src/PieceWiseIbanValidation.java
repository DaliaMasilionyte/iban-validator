public class PieceWiseIbanValidation extends Validation {

//    dar neaisku ar int
    private static final int CHECK_DIGIT_MOD = 97;

    @Override
    protected boolean validateCheckDigits(String ibanNumber) {
        return false;
    }


}
