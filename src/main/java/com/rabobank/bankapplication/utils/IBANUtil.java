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
        ibanBuilder.append("00");
        ibanBuilder.append(BANK_CODE);
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < 10; i++) {
            int randomDigit = random.nextInt(10);
            ibanBuilder.append(randomDigit);
        }
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
        }
        System.out.println(remainder);
        return remainder;

    }
    public static boolean isValidIBAN(String iban) {
        return Pattern.matches(IBAN_PATTERN, iban) && calculateCheckDigits(iban) == 1;
    }
}