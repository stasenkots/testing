package page

import model.Flight
import org.openqa.selenium.WebDriver
import utils.getDayAndMonth
import utils.getElement
import java.time.LocalDate

class TopAviaResultPage(driver: WebDriver) : AbstractPage(driver) {

    fun waitUntilPageLoaded(): TopAviaResultPage {
        driver.getElement(XPath.TopAviaResultPage.DEPARTURE)
        return this
    }

    fun getFlight(): Flight {
        val date = getDate()
        val departure = getDeparture()
        val arrival = getArrival()

        return Flight(departure, arrival, date)
    }

    private fun getDate(): LocalDate {
        val dateInput = driver.getElement(XPath.TopAviaResultPage.DATE).text
        return getDayAndMonth(dateInput)
    }

    private fun getDeparture(): String {
        return driver.getElement(XPath.TopAviaResultPage.DEPARTURE).text
    }

    private fun getArrival(): String {
        return driver.getElement(XPath.TopAviaResultPage.ARRIVAL).text
    }
}