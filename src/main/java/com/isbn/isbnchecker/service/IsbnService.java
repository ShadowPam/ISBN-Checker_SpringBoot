package com.isbn.isbnchecker.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class IsbnService {

    public boolean checkISBN(String isbn) {
        boolean result = false;

        isbn = isbn.trim();
        if (isbn.matches("^[0-9]+X?$")) {
            result = true;
        }

        return result;
    }

    public ResponseEntity<String> resolve(String isbn) {

        int lengthOf = isbn.length();

        switch (Integer.valueOf(lengthOf)) {
            case 10:
                // Run validator for ISBN 10
                if (validateISBN10(isbn)) {
                    return ResponseEntity.status(200).body(isbn);
                } else {
                    return ResponseEntity.status(400).body("The 10 number input string is not an ISBN.");
                }

            case 13:
                // Run validator for ISBN 13
                if (validateISBN13(isbn)) {
                    return ResponseEntity.status(200).body(isbn);
                } else {
                    return ResponseEntity.status(400).body("The 13 number input string is not an ISBN.");
                }

            default:
                return ResponseEntity.status(400).body("The input is not 10 or 13 numbers long.");
        }
    }

    private boolean validateISBN10(String toValidate) {
        boolean isISBN10 = false;

        int multiplier = 10;
        int sum = 0;
        for (int i = 0; i < toValidate.length(); i++) {
            int charVal = 0;
            if (toValidate.charAt(i) == 'X') {
                charVal = 10;
            } else {
                charVal = Character.getNumericValue(toValidate.charAt(i));
            }
            sum += charVal * multiplier;
            multiplier--;
        }

        if ((sum % 11) == 0) {
            isISBN10 = true;
        }

        return isISBN10;
    }

    private boolean validateISBN13(String toValidate) {
        boolean isISBN13 = false;

        int sum = 0;
        for (int i = 0; i < toValidate.length(); i++) {
            if ((i + 1) % 2 == 0) {
                sum += Character.getNumericValue(toValidate.charAt(i)) * 3;
            } else {
                sum += Character.getNumericValue(toValidate.charAt(i));
            }
        }

        if ((sum % 10) == 0) {
            isISBN13 = true;
        }

        return isISBN13;
    }
}
