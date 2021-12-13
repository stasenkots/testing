package page

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
        driver.getElement(XPath.TopAviaPage.DEPARTURE_FIELD)
        return this
    }

    fun enterDeparture(departure: String): TopAviaPage {
        departureField.sendKeys(departure + "\n")
        return this
    }

    fun enterArrival(arrival: String): TopAviaPage {
        arrivalField.sendKeys(arrival)
        val prompt = driver.getElement(XPath.TopAviaPage.FIRST_ELEMENT_OF_ARRIVAL_LIST)
        prompt.click()
        return this
    }

    fun enterDate(date: LocalDate): TopAviaPage {
        dateField.click()
        selectDate(date)
        return this
    }

    fun submit(): TopAviaPage {
        searchButton.click()
        return this
    }

    fun getResultPage(): TopAviaResultPage {
        return TopAviaResultPage(driver)
    }

    fun isTicketsFound(): Boolean {
        return driver.getElement(XPath.TopAviaPage.DIALOG_SEARCH_ERROR).isDisplayed
    }

    private fun selectDate(date: LocalDate) {
        val selectedDate =
            driver.getElement(XPath.TopAviaPage.getXPathForCalendarDays(date))
        selectedDate.click()
    }
}