package com.xenocid.xentextexcel.model

import spock.lang.Specification

/**
 * Created by xenocid on 3/9/15.
 */
class InsertRowsAndColumnsTest extends Specification {
    def "Insert row after and shift existing values down"() {
        given:
            Spreadsheet spreadsheet = new Spreadsheet()
            Sheet activeSheet = spreadsheet.addSheet();
            activeSheet.setCell("A1", 1)
            activeSheet.setCell("A2", 2)
            activeSheet.setCell("A3", 3)
        when:
            activeSheet.insertRowAfter(0)
            activeSheet.setCell("A2", 77)
        then:
            activeSheet.getCell("A1").getValue().getIntValue() == 1
            activeSheet.getCell("A2").getValue().getIntValue() == 77
            activeSheet.getCell("A3").getValue().getIntValue() == 2
            activeSheet.getCell("A4").getValue().getIntValue() == 3
    }

    def "Insert row before and shift existing values down"() {
        given:
            Spreadsheet spreadsheet = new Spreadsheet()
            Sheet activeSheet = spreadsheet.addSheet();
            activeSheet.setCell("A1", 1)
            activeSheet.setCell("A2", 2)
            activeSheet.setCell("A3", 3)
        when:
            activeSheet.insertRowBefore(0)
            activeSheet.setCell("A1", 77)
        then:
            activeSheet.getCell("A1").getValue().getIntValue() == 77
            activeSheet.getCell("A2").getValue().getIntValue() == 1
            activeSheet.getCell("A3").getValue().getIntValue() == 2
            activeSheet.getCell("A4").getValue().getIntValue() == 3
    }

    def "Insert column before and shift existing values right"() {
        given:
            Spreadsheet spreadsheet = new Spreadsheet()
            Sheet activeSheet = spreadsheet.addSheet();
            activeSheet.setCell("A2", 1)
            activeSheet.setCell("B2", 2)
            activeSheet.setCell("F2", 3)
        when:
            activeSheet.insertColumnBefore(1)
            activeSheet.setCell("A1", 77)
        then:
            activeSheet.getCell("A1").getValue().getIntValue() == 77
            activeSheet.getCell("A2").getValue().getIntValue() == 1
            activeSheet.getCell("C2").getValue().getIntValue() == 2
            activeSheet.getCell("G2").getValue().getIntValue() == 3
    }

    def "Insert column after and shift existing values right"() {
        given:
            Spreadsheet spreadsheet = new Spreadsheet()
            Sheet activeSheet = spreadsheet.addSheet();
            activeSheet.setCell("A2", 1)
            activeSheet.setCell("B2", 2)
            activeSheet.setCell("F2", 3)
        when:
            activeSheet.insertColumnAfter(0)
            activeSheet.setCell("B2", 77)
        then:
            activeSheet.getCell("A2").getValue().getIntValue() == 1
            activeSheet.getCell("B2").getValue().getIntValue() == 77
            activeSheet.getCell("C2").getValue().getIntValue() == 2
            activeSheet.getCell("G2").getValue().getIntValue() == 3
    }
}
