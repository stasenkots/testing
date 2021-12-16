package page

import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.PageFactory
import utils.Logger

abstract class AbstractPage(protected val driver: WebDriver) {
    init {
        PageFactory.initElements(driver, this)
    }
}