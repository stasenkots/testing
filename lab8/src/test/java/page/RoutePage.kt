package page

import conditions.PageLoadedCondition
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import utils.getElement
import utils.getElements
import java.time.Duration

class RoutePage(driver: WebDriver) : AbstractPage(driver) {

    init {
        WebDriverWait(
            driver,
            Duration.ofSeconds(10)
        ).until(PageLoadedCondition())
    }

    private val destinationLocator = "//*[@class=\"sch-table__station train-to-name\"]"
    private val sourceLocator = "//*[@class=\"sch-table__station train-from-name\"]"

    fun getDestinations(): List<String> {
        return driver.getElements(By.xpath(destinationLocator)).map { dest -> dest.text }
    }

    fun getSources(): List<String> {
        return driver.getElements(By.xpath(sourceLocator)).map { dest -> dest.text }
    }
}