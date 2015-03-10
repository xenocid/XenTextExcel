package com.xenocid.xentextexcel

import org.junit.runner.RunWith
import org.spockframework.runtime.Sputnik
import spock.lang.Specification

/**
 * Created by xenocid on 3/8/15.
 */
class MainTest extends Specification {
    def "hello world from Spock"() {
        when: "adding two and two"

        then: "two and two equal four"
        2 + 2 == 4
    }
}
