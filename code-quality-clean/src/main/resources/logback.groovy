import ch.qos.logback.classic.AsyncAppender
import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.classic.filter.ThresholdFilter
import ch.qos.logback.core.util.FileSize

def ENCODER_PATTERN = "%d{HH:mm:ss.SSS} [%thread] %highlight(%-5level) %logger{36} - %msg%n"
def ENCODER_FILE_PATTERN = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"

statusListener(NopStatusListener)
appender("CONSOLE", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = ENCODER_PATTERN
    }

    filter(ThresholdFilter) {
        level = INFO
    }
}
appender("FILE", RollingFileAppender) {
    filter(ThresholdFilter) {
        level = INFO
    }
    append = true
    encoder(PatternLayoutEncoder) {
        pattern = ENCODER_FILE_PATTERN
    }

    rollingPolicy(TimeBasedRollingPolicy) {
        fileNamePattern = "logs/app.%d{yyyy-MM-dd}.%i.log.gz"
        maxHistory = 90
        timeBasedFileNamingAndTriggeringPolicy(SizeAndTimeBasedFNATP) {
            maxFileSize = FileSize.valueOf("1gb")
        }
    }
}
appender("FILE_DEBUG", RollingFileAppender) {
    append = true
    encoder(PatternLayoutEncoder) {
        pattern = ENCODER_FILE_PATTERN
    }

    rollingPolicy(TimeBasedRollingPolicy) {
        fileNamePattern = "logs/app-debug.%d{yyyy-MM-dd}.%i.log.gz"
        maxHistory = 90
        timeBasedFileNamingAndTriggeringPolicy(SizeAndTimeBasedFNATP) {
            maxFileSize = FileSize.valueOf("1gb")
        }
    }
}
appender("ASYNC", AsyncAppender) {
    appenderRef("FILE")
}
appender("ASYNC_DEBUG", AsyncAppender) {
    appenderRef("FILE_DEBUG")
}

def APPENDERS = ["ASYNC", "ASYNC_DEBUG", "CONSOLE"]
logger("org.springframework", DEBUG, APPENDERS)
logger("com.itechart", DEBUG, APPENDERS)

