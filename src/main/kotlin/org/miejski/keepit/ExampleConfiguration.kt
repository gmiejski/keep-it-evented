package org.miejski.keepit

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "example")
class ExampleConfiguration {

    var value: Int = 0

}