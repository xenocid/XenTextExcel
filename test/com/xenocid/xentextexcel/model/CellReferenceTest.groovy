package com.xenocid.xentextexcel.model

import spock.lang.Specification

/**
 * Created by xenocid on 3/8/15.
 */
class CellReferenceTest extends Specification {
    def "convert string representation of cell address to coordinates"() {
        when:
            def address1 = "B3"
            def address2 = "A1"
            def address3 = "BZ75"
        then:
            CellReference.stringToCoordinates(address1) == [2, 1]
            CellReference.stringToCoordinates(address2) == [0, 0]
            CellReference.stringToCoordinates(address3) == [74, 77]
    }

    def "convert row and column into cell reference string"() {
        when:
            def pair1 = [0, 0] as int[]
            def pair2 = [10, 4] as int[]
            def pair3 = [74, 77] as int []
        then:
            CellReference.coordinatesToString(pair1) == "A1"
            CellReference.coordinatesToString(pair2) == "E11"
            CellReference.coordinatesToString(pair3) == "BZ75"
    }
}
