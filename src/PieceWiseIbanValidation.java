public class PieceWiseIbanValidation extends Validation {

    private static final long CHECK_DIGIT_MOD = 97;
    private static final int MAX_PIECE_LENGTH = 9;

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
