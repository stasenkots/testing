package utils

import driver.SystemProperty
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

object Logger {

    private val logger: Logger?

    init {
        val isEnabled = System.getProperty(SystemProperty.ENABLE_LOGGING).toBoolean()
        logger = if (isEnabled) {
            LogManager.getRootLogger()
        } else null
    }

    fun info(info: String) {
        logger?.info(info)
    }

}