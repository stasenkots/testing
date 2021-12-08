package page

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import utils.getElement
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class TopAviaResultPage(driver: WebDriver) : AbstractPage(driver) {

    fun waitUntilPageLoaded(): TopAviaResultPage{
        driver.getElement(By.xpath(XPath.TopAviaResultPage.SOURCE))
        return this
    }

    fun getDate(): LocalDate {
        val dateOutput =
            driver.getElement(By.xpath(XPath.TopAviaResultPage.DATE))
                .text
                .substringBefore(',')
                .plus(" 1970")
        val formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale("ru"))
        return LocalDate.parse(dateOutput, formatter)
    }

    fun getSource(): String {
        return driver.getElement(By.xpath(XPath.TopAviaResultPage.SOURCE)).text
    }

    fun getDestination(): String {
        return driver.getElement(By.xpath(XPath.TopAviaResultPage.DESTINATION)).text
    }
}