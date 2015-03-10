package com.xenocid.xentextexcel.model

import spock.lang.Specification

/**
 * Created by xenocid on 3/8/15.
 */
class CellValueTest extends Specification {
    def "GetType"() {
        when:
            CellValue nullValue = new CellValue()
            CellValue intValue = new CellValue(23)
            CellValue dblValue = new CellValue(2.0f)
            CellValue strValue = new CellValue("test")
        then:
            nullValue.getType() == CellValue.CellValueType.CVT_NONE;
            intValue.getType() == CellValue.CellValueType.CVT_INTEGER;
            dblValue.getType() == CellValue.CellValueType.CVT_DOUBLE;
            strValue.getType() == CellValue.CellValueType.CVT_STRING;
    }

    def "GetIntValue"() {
        when:
            int number = 23
            CellValue intValue = new CellValue(number)
        then:
            intValue.getIntValue() == number
    }

    def "GetDoubleValue"() {
        when:
            double number = 2.0f
            CellValue dblValue = new CellValue(number)
        then:
            dblValue.getDoubleValue() == number
    }

    def "GetStringValue"() {
        when:
            String string = "Klaatu barada nikto"
            CellValue strValue = new CellValue(string)
        then:
            strValue.getStringValue() == string
    }

    def "SetType"() {
        when:
            CellValue.CellValueType type = CellValue.CellValueType.CVT_FORMULA
            CellValue nullValue = new CellValue()
            nullValue.setType(type)
        then:
            nullValue.getType() == type;
    }

    def "SetIntValue"() {
        when:
            int number = 723
            CellValue nullValue = new CellValue()
            nullValue.setIntValue(number)
        then:
            nullValue.getType() == CellValue.CellValueType.CVT_INTEGER
            nullValue.getIntValue() == number
    }

    def "SetDoubleValue"() {
        when:
            double number = 7.23
            CellValue nullValue = new CellValue()
            nullValue.setDoubleValue(number)
        then:
            nullValue.getType() == CellValue.CellValueType.CVT_DOUBLE
            nullValue.getDoubleValue() == number
    }

    def "SetStringValue"() {
        when:
            String str = "Klaatu barada nikto"
            CellValue nullValue = new CellValue()
            nullValue.setStringValue(str)
        then:
            nullValue.getType() == CellValue.CellValueType.CVT_STRING
            nullValue.getStringValue() == str
    }

    def "setFormulaValue"() {
        when:
            String formula = "A2+B3*2"
            CellValue nullValue = new CellValue()
            nullValue.setFormulaValue(formula)
        then:
            nullValue.getType() == CellValue.CellValueType.CVT_FORMULA
            nullValue.getFormulaValue() == formula
    }

    def "Test copy constructor"() {
        when:
            String str = "Klaatu barada nikto"
            CellValue src = new CellValue(str)
            CellValue dst = new CellValue(src)
        then:
            dst.getType() == src.getType()
            dst.getStringValue() == src.getStringValue()
    }
}
