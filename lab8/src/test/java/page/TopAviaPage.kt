package page

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import utils.getElement
import java.time.LocalDate

class TopAviaPage(driver: WebDriver) : AbstractPage(driver) {

    @FindBy(xpath = XPath.TopAviaPage.DEPARTURE_FIELD)
    private lateinit var departureField: WebElement

    @FindBy(xpath = XPath.TopAviaPage.ARRIVAL_FIELD)
    private lateinit var arrivalField: WebElement

    @FindBy(xpath = XPath.TopAviaPage.DATE_FIELD)
    private lateinit var dateField: WebElement

    @FindBy(xpath = XPath.TopAviaPage.SEARCH_BUTTON)
    private lateinit var searchButton: WebElement

    fun openPage(): TopAviaPage {
        driver.getElement(By.xpath(XPath.TopAviaPage.DEPARTURE_FIELD))
        return this
    }

    fun enterDeparture(departure: String): TopAviaPage {
        departureField.sendKeys(departure + "\n")
        val prompt = driver.getElement(By.xpath(XPath.TopAviaPage.FIRST_ELEMENT_OF_DEPARTURE_LIST))
        prompt.click()
        return this
    }

    fun enterArrival(arrival: String): TopAviaPage {
        arrivalField.sendKeys(arrival)
        val prompt = driver.getElement(By.xpath(XPath.TopAviaPage.FIRST_ELEMENT_OF_ARRIVAL_LIST))
        prompt.click()
        return this
    }

    fun enterDate(date: LocalDate): TopAviaPage {
        dateField.click()
        selectDate(date)
        return this
    }

    fun submit(): TopAviaResultPage {
        searchButton.click()
        return TopAviaResultPage(driver)
    }

    private fun selectDate(date: LocalDate) {
        val selectedDate =
            driver.getElement(By.xpath(XPath.TopAviaPage.getXPathForCalendarDays(date)))
        selectedDate.click()
    }
}