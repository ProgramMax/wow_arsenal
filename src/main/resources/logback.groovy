import ch.qos.logback.classic.encoder.PatternLayoutEncoder 
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.FileAppender

import static ch.qos.logback.classic.Level.DEBUG


logger("com.foo", DEBUG, ["CONSOLE", "FILE"], false)

appender("FILE", FileAppender) {
  file = "testFile.log"
  append = false
  encoder(PatternLayoutEncoder) {
      pattern = "%date{HH:mm:ss.SSS} - %7level [%30.40logger{5}] - %msg%n"
  }
}

appender("CONSOLE", ConsoleAppender) {
  encoder(PatternLayoutEncoder) {
      pattern = "%date{HH:mm:ss.SSS} - %7level [%30.40logger{5}] - %msg%n"
  }
}

root(DEBUG, ["FILE", "CONSOLE"])
