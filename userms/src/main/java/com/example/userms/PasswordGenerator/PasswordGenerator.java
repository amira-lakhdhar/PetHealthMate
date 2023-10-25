package com.example.userms.PasswordGenerator;

import java.security.SecureRandom;
import java.util.Random;

public class PasswordGenerator {
    private static final String UPPERCASE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE_CHARS = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";

    private static final String VALID_CHARS = UPPERCASE_CHARS + LOWERCASE_CHARS + NUMBERS;

    private final Random random = new SecureRandom();

    public String generateValidPassword(int minLength, int maxLength, boolean requireUpperCase, boolean requireLowerCase) {
        int passwordLength = random.nextInt(maxLength - minLength + 1) + minLength;

        StringBuilder password = new StringBuilder();

        // Ensure the password contains at least one uppercase character if required
        if (requireUpperCase) {
            char uppercaseChar = UPPERCASE_CHARS.charAt(random.nextInt(UPPERCASE_CHARS.length()));
            password.append(uppercaseChar);
        }

        // Ensure the password contains at least one lowercase character if required
        if (requireLowerCase) {
            char lowercaseChar = LOWERCASE_CHARS.charAt(random.nextInt(LOWERCASE_CHARS.length()));
            password.append(lowercaseChar);
        }

        // Generate the remaining characters for the password
        int remainingLength = passwordLength - password.length();
        for (int i = 0; i < remainingLength; i++) {
            char randomChar = VALID_CHARS.charAt(random.nextInt(VALID_CHARS.length()));
            password.append(randomChar);
        }

        // Shuffle the characters in the password randomly
        char[] passwordChars = password.toString().toCharArray();
        for (int i = 0; i < passwordChars.length; i++) {
            int randomIndex = random.nextInt(passwordChars.length);
            char temp = passwordChars[i];
            passwordChars[i] = passwordChars[randomIndex];
            passwordChars[randomIndex] = temp;
        }

        return new String(passwordChars);
    }
}
