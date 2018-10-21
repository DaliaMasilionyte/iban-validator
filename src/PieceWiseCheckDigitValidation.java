/**
 * Check digit piece-wise validation using following algorithm.
 * @see <a href="https://en.wikipedia.org/wiki/International_Bank_Account_Number#Modulo_operation_on_IBAN">Modulo operation on IBAN</a>.
 * Uses less memory than full number validation, therefore the performance is better.
 */

public class PieceWiseCheckDigitValidation extends CheckDigitValidation {

    private static final long CHECK_DIGIT_MOD = 97;
    private static final int MAX_PIECE_LENGTH = 9;

    /**
     * Validates the check digits using piece-wise validation as defined in the link above.
     *
     * @param ibanNumber numeric reordered IBAN
     * @return validity of the IBAN
     */
    @Override
    protected boolean validateCheckDigits(String ibanNumber) {
        boolean stop = false;
        long ibanNumberPiece;
        long remainder = 0;

        while(!stop){
            if(ibanNumber.length() < MAX_PIECE_LENGTH){
                stop = true;
                ibanNumberPiece = Long.valueOf(ibanNumber);
            } else {
                ibanNumberPiece = Long.valueOf(ibanNumber.substring(0,MAX_PIECE_LENGTH));
                ibanNumber = ibanNumber.substring(MAX_PIECE_LENGTH);
            }
            remainder = ibanNumberPiece % CHECK_DIGIT_MOD;
            ibanNumber = String.valueOf(remainder) + ibanNumber;
        }
        return (remainder == CHECK_DIGIT_REMAINDER);
    }
}
