package org.miejski.keepitup

import org.miejski.keepit.ExampleConfiguration
import org.miejski.keepit.KeepItApplication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.env.Environment
import spock.lang.Specification

@SpringBootTest(classes = KeepItApplication, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MainTest extends Specification {

    @Autowired
    Environment env

    @Autowired
    ExampleConfiguration configuration

    def "Example configuration"() {
        when:
        def a = 1

        then:
        a == configuration.value
    }
}
