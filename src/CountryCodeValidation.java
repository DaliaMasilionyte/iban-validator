import java.util.Locale;
import java.util.SortedSet;
import java.util.TreeSet;

public class CountryCodeValidation {

    private final SortedSet<String> isoCountries;

    public CountryCodeValidation(){
        this.isoCountries = new TreeSet<>();
        for(String countryCode: Locale.getISOCountries()){
            isoCountries.add(countryCode);
        }
    }

    boolean isValid(Iban iban){
        return isoCountries.contains(iban.getCountryCode());
    }

}
