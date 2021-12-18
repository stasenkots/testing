package page

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import utils.Logger
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
        Logger.info("open TopAvia page")
        driver.getElement(XPath.TopAviaPage.DEPARTURE_FIELD)
        return this
    }

    fun enterDeparture(departure: String): TopAviaPage {
        Logger.info("Enter departure - $departure")
        departureField.sendKeys(departure + "\n")
        return this
    }

    fun enterArrival(arrival: String): TopAviaPage {
        Logger.info("Enter arrival - $arrival")
        arrivalField.sendKeys(arrival)
        val prompt = driver.getElement(XPath.TopAviaPage.FIRST_ELEMENT_OF_ARRIVAL_LIST)
        prompt.click()
        return this
    }

    fun enterDate(date: LocalDate): TopAviaPage {
        Logger.info("Enter date - $date")
        dateField.click()
        selectDate(date)
        return this
    }

    fun submit(): TopAviaPage {
        Logger.info("Click Submit")
        searchButton.click()
        return this
    }

    fun openResultPage(): TopAviaResultPage {
        Logger.info("Open Result page")
        return TopAviaResultPage(driver).openPage()
    }

    fun isTicketsFound(): Boolean {
        val isTicketFound = !driver.getElement(XPath.TopAviaPage.DIALOG_SEARCH_ERROR).isDisplayed
        Logger.info("isTicketFound - $isTicketFound")
        return isTicketFound
    }

    private fun selectDate(date: LocalDate) {
        Logger.info("Select date - $date")
        val selectedDate =
            driver.getElement(XPath.TopAviaPage.getXPathForCalendarDays(date))
        selectedDate.click()
    }
}