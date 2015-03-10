package com.xenocid.xentextexcel.model

import spock.lang.Specification

/**
 * Created by xenocid on 3/9/15.
 */
class ExpressionSolverTest extends Specification {

    def "calculate basic arithmetic expressions"() {
        given:
            ExpressionSolver solver = new ExpressionSolver()
        when:
            double result = solver.solveExpression(expression)
        then:
            assert result == expected
        where:
            expression                    || expected
            "723"                         || 723
            "3+2"                         || 5
            "7-3"                         || 4
            "7-11"                        || -4
            "2*3.5"                       || 7
            "   16 / 4"                   || 4
            "2 / 4"                       || 0.5
            "3 + 2 + 1"                   || 6
            "7 % 3"                       || 1
            "3 + 2   * 4"                 || 11
            ".5 * 0.5"                    || 0.25
            "4.14 - 1.0"                  || 3.14
            "-3.2"                        || -3.2
            "2*3*4"                       || 24
            "3^2 + 5"                     || 14
            "(2 + 3) * 2"                 || 10
            "(4^(5-3))*(99 + -1 * 100)"   || -16
    }

    def "calculate expressions with cell references"() {
        given:
            Spreadsheet spreadsheet = new Spreadsheet()
            Sheet activeSheet = spreadsheet.getSheet(0);
            activeSheet.setCell("A1", 2)
            activeSheet.setCell("A2", 4.5)
            activeSheet.setCell("B4", 8)
            ExpressionSolver solver = new ExpressionSolver(spreadsheet);
        when:
            double result = solver.solveExpression(expression)
        then:
            assert result == expected
        where:
            expression                    || expected
            "2 * A1"                      || 4
            "A1 + A2 * B4"                || 38
    }
}
