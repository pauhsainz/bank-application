package com.rabobank.bankapplication;

import com.rabobank.bankapplication.utils.IBANUtil;

public class IBANUtilTest {

    public static void main(String[] args) {
        String generatedIBAN = IBANUtil.generateIBAN();
        System.out.println("Generated IBAN: " + generatedIBAN);

        boolean isValid = IBANUtil.isValidIBAN(generatedIBAN);
        System.out.println("Is the generated IBAN valid? " + isValid);

        String invalidIBAN = "NL43INGB0670388459"; // Invalid IBAN with only 10 digits
        System.out.println("What about an invalid IBAN " + invalidIBAN);
        isValid = IBANUtil.isValidIBAN(invalidIBAN);
        System.out.println("Is the invalid IBAN valid? " + isValid);
    }
}

