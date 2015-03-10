package com.xenocid.xentextexcel.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by xenocid on 3/9/15.
 */
public class ExpressionSolver {
    private Spreadsheet spreadsheet;
    private String expr;
    private char   currentChar;
    private int    index;

    public ExpressionSolver() {
        super();
        reset();
    }

    public ExpressionSolver(Spreadsheet spreadsheet) {
        super();
        reset();
        this.spreadsheet = spreadsheet;
    }

    public Spreadsheet getSpreadsheet() {
        return spreadsheet;
    }

    public void setSpreadsheet(Spreadsheet spreadsheet) {
        this.spreadsheet = spreadsheet;
    }

    public double solveExpression(String expr) {
        reset();
        this.expr = expr;
        this.currentChar = expr.charAt(index);
        return roundOff(parseExpression(), 2);
    }

    private double roundOff(double result, int decimalNumbers) {
        return (new BigDecimal(result).setScale(decimalNumbers, RoundingMode.HALF_EVEN)).doubleValue();
    }

    private boolean consumeCharacter() {
        currentChar = (++index < expr.length()) ? expr.charAt(index) : '\n';
        return currentChar != '\n';
    }

    private void consumeSpace() {
        boolean notEndOfLine = index < expr.length();
        while(notEndOfLine && currentChar == ' ') {
            notEndOfLine = consumeCharacter();
        }
    }

    private double parseNumber() {
        double lvalue = 0.f;
        boolean negate = false;
        consumeSpace();

        if(currentChar == '(') {
            consumeCharacter();
            lvalue = parseExpression();
            if (currentChar == ')') {
                consumeCharacter();
            }
        } else {
            if (currentChar == '-' || currentChar == '+') {
                negate = currentChar == '-';
                consumeCharacterAndSpace();
            }
            StringBuilder accumulator = new StringBuilder();

            if(isCapitalLetter(currentChar)) {
                while (isCapitalLetter(currentChar) || Character.isDigit(currentChar)) {
                    accumulator.append(currentChar);
                    consumeCharacter();
                }

                if (accumulator.length() == 0) {
                    throw new RuntimeException("Unexpected: " + currentChar);
                }

                if(spreadsheet != null) {
                    Sheet sheet = spreadsheet.getSheet(0);
                    CellValue cellValue = sheet.getCell(accumulator.toString()).getValue();
                    switch(cellValue.getType()) {
                        case CVT_DOUBLE:
                        case CVT_FORMULA:
                            lvalue = cellValue.getDoubleValue();
                            break;
                        case CVT_INTEGER:
                            lvalue = cellValue.getIntValue();
                            break;
                        default:
                            throw new RuntimeException("Referenced cell is not a numeric value");
                    }
                } else {
                    throw new RuntimeException("Expression uses a cell reference, but spreadsheet is not supplied");
                }
            } else {
                while ("1234567890.".indexOf(currentChar) != -1) {
                    accumulator.append(currentChar);
                    consumeCharacter();
                }

                if (accumulator.length() == 0) {
                    throw new RuntimeException("Unexpected: " + currentChar);
                }
                lvalue = Double.parseDouble(accumulator.toString());
            }
        }
        consumeSpace();
        if (currentChar == '^') {
            consumeCharacter();
            lvalue = Math.pow(lvalue, parseNumber());
        }
        if (negate) {
            lvalue *= -1;
        }

        return lvalue;
    }

    private double parseTerm() {
        double lvalue = parseNumber();
        consumeSpace();
        while(true) {
            char operation = currentChar;
            switch(operation) {
                case '*':
                    consumeCharacterAndSpace();
                    lvalue = lvalue * parseNumber();
                    break;
                case '/':
                    consumeCharacterAndSpace();
                    lvalue = lvalue / parseNumber();
                    break;
                case '%':
                    consumeCharacterAndSpace();
                    lvalue = lvalue % parseNumber();
                    break;
                default:
                    return lvalue;
            }
        }
    }

    private double parseFactor() {
        double lvalue = parseTerm();
        consumeSpace();
        while(true) {
            char operation = currentChar;
            switch(operation) {
                case '+':
                    consumeCharacterAndSpace();
                    lvalue = lvalue + parseTerm();
                    break;
                case '-':
                    consumeCharacterAndSpace();
                    lvalue = lvalue - parseTerm();
                    break;
                default:
                    return lvalue;
            }
        }
    }

    private double parseExpression() {
        return parseFactor();
    }

    private void consumeCharacterAndSpace() {
        consumeCharacter();
        consumeSpace();
    }

    private boolean isCapitalLetter(char ch) {
        return (ch >= 'A' && ch <= 'Z');
    }

    private void reset() {
        expr = "";
        index = 0;
        currentChar = '\n';
    }
}
