package com.xenocid.xentextexcel.model


import spock.lang.Specification

/**
 * Created by xenocid on 3/9/15.
 */

class SimpleScenariosTest extends Specification {
    def "Assign value to a spreadsheet cell and read it back"() {
        when:
            Spreadsheet spreadsheet = new Spreadsheet()
            Sheet activeSheet = spreadsheet.addSheet()
            activeSheet.getCell("A1").setValue("42")
        then:
            activeSheet.getCell("A1").getValue().getStringValue() == "42"
    }

    def "Re-assign value to a spreadsheet cell"() {
        when:
            Spreadsheet spreadsheet = new Spreadsheet()
            Sheet activeSheet = spreadsheet.addSheet()
            activeSheet.getCell("CZ109").setValue(1.01)
            activeSheet.getCell("CZ109").setValue(3.14)
        then:
            activeSheet.getCell("CZ109").getValue().getDoubleValue() == 3.14
    }
}
