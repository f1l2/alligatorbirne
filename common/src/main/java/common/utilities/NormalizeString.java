package common.utilities;

public class NormalizeString {

    /**
     * Central place for normalization of string.
     * 
     * At the moment only conversion to lower case is applied.
     * 
     * @param string
     * @return
     */

    public static String normalize(String string) {
        return string.toLowerCase();
    }

}
