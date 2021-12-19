package page

import model.Trip
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import page.XPath.TopTourSearchPage.FIRST_ITEM_FROM_DEPARTURES
import utils.*
import java.time.LocalDate

class TourSearchPage(driver: WebDriver) : AbstractPage(driver) {

    @FindBy(xpath = XPath.TopTourSearchPage.CLOSE_ARRIVAL_COUNTRY)
    private lateinit var closeArrivalCountryButton: WebElement

    @FindBy(xpath = XPath.TopTourSearchPage.CLOSE_ARRIVAL_CITY)
    private lateinit var closeArrivalCityButton: WebElement

    @FindBy(xpath = XPath.TopTourSearchPage.FIELD_DEPARTURE)
    private lateinit var departureField: WebElement

    @FindBy(xpath = XPath.TopTourSearchPage.FIELD_ARRIVAL_COUNTRY)
    private lateinit var arrivalCountryField: WebElement

    @FindBy(xpath = XPath.TopTourSearchPage.FIELD_ARRIVAL_CITY)
    private lateinit var arrivalCityField: WebElement

    @FindBy(xpath = XPath.TopTourSearchPage.CALENDAR_INPUT)
    private lateinit var calendarInput: WebElement

    @FindBy(xpath = XPath.TopTourSearchPage.SEARCH_BUTTON)
    private lateinit var searchButton: WebElement

    fun openPage(): TourSearchPage {
        Logger.info("open tour search page")
        driver.getElement(XPath.TopTourSearchPage.FIELD_DEPARTURE)
        return this
    }

    fun enterArrivalCountry(arrivalCountry: String): TourSearchPage {
        Logger.info(" Enter arrival country - $arrivalCountry")
        closeArrivalCountryButton.click()
        arrivalCountryField.click()
        val firstDropDownElement =
            driver.getElement(XPath.TopTourSearchPage.getListDropdownElement(arrivalCountry))
        firstDropDownElement.click()
        return this
    }

    fun enterArrivalCity(arrivalCity: String): TourSearchPage {
        Logger.info(" Enter arrival city - $arrivalCity")
        closeArrivalCityButton.click()
        arrivalCityField.click()
        val firstDropDownElement =
            driver.getElement(XPath.TopTourSearchPage.getListDropdownElement(arrivalCity))
        firstDropDownElement.click()
        driver.getElement(XPath.TopTourSearchPage.MASK).click()
        return this
    }

    fun enterArrivalDate(date: LocalDate): TourSearchPage {
        Logger.info(" Enter day arrival - $date")
        calendarInput.click()
        val calendarDay =
            driver.getElement(XPath.TopTourSearchPage.getXPathForCalendarDays(date))
        calendarDay.click()
        return this
    }

    fun search(): TourSearchPage {
        Logger.info("Click search button")
        searchButton.click()
        return this
    }

    fun getTrips(): List<Trip> {
        Logger.info("Get trips")
        val departures = driver.getElements(XPath.TopTourSearchPage.DEPARTURE_CITY).map { it.text }
        val arrivals = driver.getElements(XPath.TopTourSearchPage.ARRIVAL_CITY).map { it.text }
        val datesArrival = driver.getElements(XPath.TopTourSearchPage.ARRIVAL_DATE)
            .map { formatStringByDotPatternWithShortYear(it.text) }

        val trips = mutableListOf<Trip>()
        for (i in departures.indices) {
            trips.add(Trip(departures[i], arrivals[i], datesArrival[i]))
        }
        Logger.info("Trips - $trips")
        return trips
    }

    fun isToursFound(): Boolean {
        val isTourFound = !driver.getElement(XPath.TopTourSearchPage.NO_TOURS_FOUND_TITLE).isDisplayed
        Logger.info("Get trips - $isTourFound")
        return isTourFound
    }

}