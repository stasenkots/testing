package tests

import driver.Driver
import org.junit.After
import org.junit.Before
import org.openqa.selenium.WebDriver
import utils.Logger

open class CommonConditions {
    private var _driver: WebDriver? = null
    protected val driver: WebDriver
        get() = _driver!!

    @Before
    fun setUp() {
        Logger.info("setup test")
        _driver = Driver.get()
    }

    @After
    fun stopBrowser() {
        Logger.info("end test")
        Driver.closeDriver()
    }
}