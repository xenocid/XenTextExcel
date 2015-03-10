package com.xenocid.xentextexcel.model;

import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xenocid on 3/8/15.
 */
public class CellReference {
    private static final String CELLREF_REGEX = "(?<letters>[A-Z]+)(?<numbers>[0-9]+)";
    public static final String  CELLREF_CG_LETTERS = "letters";
    public static final String  CELLREF_CG_NUMBERS = "numbers";
    public static final int     ALPHABET_LENGTH = 26;

    public CellReference() {

    }

    public static boolean isValidCellReference(String cellRefStr) {
        Pattern pattern = Pattern.compile(CELLREF_REGEX);
        Matcher matcher = pattern.matcher(cellRefStr);
        return matcher.matches();
    }

    public static String coordinatesToString(int row, int column) {
        int remainder = column;
        StringBuilder builder = new StringBuilder();
        do {
            char letter = (char)('A' + remainder % ALPHABET_LENGTH);
            builder.append(letter);
            if (remainder / ALPHABET_LENGTH == 0) {
                remainder = remainder / ALPHABET_LENGTH;
            } else {
                remainder = remainder / ALPHABET_LENGTH - 1;
            }
        } while(remainder != 0);

        return builder.reverse().toString() + Integer.toString(row + 1);
    }

    public static String coordinatesToString(int[] coordinates) {
        return coordinatesToString(coordinates[0], coordinates[1]);
    }

    public static int[] stringToCoordinates(String cellRefStr) {
        Pattern pattern = Pattern.compile(CELLREF_REGEX);
        Matcher matcher = pattern.matcher(cellRefStr);
        if (matcher.matches()) {
            String letters = matcher.group(CELLREF_CG_LETTERS);
            String numbers = matcher.group(CELLREF_CG_NUMBERS);

            int column = 0;
            letters = new StringBuilder(letters).reverse().toString();
            for(int i = 0, base = 1; i < letters.length(); i++, base *= ALPHABET_LENGTH) {
                char letter = letters.charAt(i);
                int diff = letter - 'A';
                if (i == 0) {
                    column += diff;
                } else {
                    column += (diff + 1) * base;
                }
            }

            return new int[]{Integer.parseInt(numbers) - 1, column};

        } else {
            throw new InputMismatchException("Not a cell reference");
        }
    }
}
