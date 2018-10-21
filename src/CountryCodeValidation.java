import java.util.Locale;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Checks if the IBAN contains a valid ISO country code.
 */
class CountryCodeValidation implements Validation {

    private final SortedSet<String> isoCountries;

    public CountryCodeValidation(){
        this.isoCountries = new TreeSet<>();
        for(String countryCode: Locale.getISOCountries()){
            isoCountries.add(countryCode);
        }
    }

    public boolean isValid(ValidatedIban validatedIban){
        return isoCountries.contains(validatedIban.getCountryCode());
    }

}
