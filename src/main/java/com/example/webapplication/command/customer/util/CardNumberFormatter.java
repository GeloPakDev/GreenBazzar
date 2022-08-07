package com.example.webapplication.command.customer.util;

public final class CardNumberFormatter {
    private CardNumberFormatter() {
    }

    public static int cardNumberFormatter(String cardNumber) {
        int numberLength = cardNumber.length();
        StringBuilder withoutSpacings = new StringBuilder();

        for (int i = 0; i < numberLength; i++) {
            char letter = cardNumber.charAt(i);
            if (letter != 32) {
                withoutSpacings.append(letter);
            }
        }
        return Integer.parseInt(String.valueOf(withoutSpacings));
    }

    public static int expirationDateFormatter(String expirationDate) {
        int expirationDateLength = expirationDate.length();

        StringBuilder withoutSlashBuilder = new StringBuilder();

        for (int i = 0; i < expirationDateLength; i++) {
            char letter = expirationDate.charAt(i);
            if (letter != 47) {
                withoutSlashBuilder.append(letter);
            }
        }
        return Integer.parseInt(String.valueOf(withoutSlashBuilder));
    }
}
