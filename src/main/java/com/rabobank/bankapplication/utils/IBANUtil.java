package com.rabobank.bankapplication.utils;

import java.security.SecureRandom;
import java.util.regex.Pattern;

public class IBANUtil {

    private static final String IBAN_PATTERN = "^[A-Z]{2}\\d{2}[A-Z0-9]{12,30}$";
    private static final String COUNTRY_CODE = "NL";
    private static final String BANK_CODE = "1234"; //Which one should I use?
    private static final int IBAN_LENGTH = 18;

    private IBANUtil(){}
    public static String generateIBAN() {
        StringBuilder ibanBuilder = new StringBuilder(IBAN_LENGTH);
        ibanBuilder.append(COUNTRY_CODE);
        ibanBuilder.append("00"); // used as the check digits for initial generation
        ibanBuilder.append(BANK_CODE);

        // Generate random numbers for the rest of the IBAN
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < 10; i++) {
            int randomDigit = random.nextInt(10);
            ibanBuilder.append(randomDigit);
        }

        // Calculate the check digits
        int checkDigits = calculateCheckDigits(ibanBuilder.toString());
        String formattedCheckDigits = String.format("%02d", checkDigits);
        ibanBuilder.replace(2, 4, formattedCheckDigits);

        return ibanBuilder.toString();
    }

    public static int calculateCheckDigits(String ibanWithoutCheckDigits) {
        String rearrangedIBAN = ibanWithoutCheckDigits.substring(4) + ibanWithoutCheckDigits.substring(0, 4);
        System.out.println("rearranged IBAN" +rearrangedIBAN);
        rearrangedIBAN=rearrangedIBAN.toUpperCase();
        StringBuilder numericIBAN = new StringBuilder();
        for (char ch : rearrangedIBAN.toCharArray()) {
            if (Character.isLetter(ch)) {
                numericIBAN.append(Character.getNumericValue(ch));
            } else {
                numericIBAN.append(ch);
            }
        }
        System.out.println("After char->num transformation" +numericIBAN);
        int remainder = 0;
        for (int i = 0; i < numericIBAN.length(); i++) {
            int digit = Character.digit(numericIBAN.charAt(i), 10);
            remainder = (remainder * 10 + digit) % 97;
//            int digit = Character.getNumericValue(numericIBAN.charAt(i));
//            System.out.println("Digit:" +digit);
//            remainder = (remainder * 10 + digit) % 97;
        }
        //Optimize w ChatGPT
        System.out.println(remainder);
        return remainder;

    }

    // It takes an IBAN without the check digits, moves the first four characters to the end.
    // It then converts any letters to numeric values
    // performs a mod97 calculation on the result
    // The remainder is used to calculate the check digits (98 minus the remainder)
    // The check digits are returned as an int
    public static boolean isValidIBAN(String iban) {
        return Pattern.matches(IBAN_PATTERN, iban) && calculateCheckDigits(iban) == 1;
    }
//    If the IBAN matches the expected pattern and the check digits are correct, the method returns true
}