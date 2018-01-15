package rivm

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class DbSpec extends Specification implements DomainUnitTest<Db> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
}
