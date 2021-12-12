package page

import model.Trip
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import page.XPath.TopTourSearchPage.FIRST_ITEM_FROM_DEPARTURES
import utils.getElement
import utils.getElements
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class TourSearchPage(driver: WebDriver) : AbstractPage(driver) {

    @FindBy(xpath = XPath.TopTourSearchPage.CLOSE_DEPARTURE)
    private lateinit var closeDepartureButton: WebElement

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

    fun enterDeparture(departure: String): TourSearchPage {
        closeDepartureButton.click()
        departureField.sendKeys(departure)
        val firstDropDownElement =
            driver.getElement(By.xpath(FIRST_ITEM_FROM_DEPARTURES))
        firstDropDownElement.click()
        return this
    }

    fun enterArrivalCountry(arrivalCountry: String): TourSearchPage {
        arrivalCountryField.click()
        val firstDropDownElement =
            driver.getElement(By.xpath(XPath.TopTourSearchPage.getListDropdownElement(arrivalCountry)))
        firstDropDownElement.click()
        return this
    }

    fun enterArrivalCity(arrivalCity: String): TourSearchPage {
        closeArrivalCityButton.click()
        arrivalCityField.click()
        val firstDropDownElement =
            driver.getElement(By.xpath(XPath.TopTourSearchPage.getListDropdownElement(arrivalCity)))
        firstDropDownElement.click()
        driver.getElement(By.xpath(XPath.TopTourSearchPage.MASK)).click()
        return this
    }

    fun enterCalendarDay(date: LocalDate): TourSearchPage {
        calendarInput.click()
        val calendarDay =
            driver.getElement(By.xpath(XPath.TopTourSearchPage.getXPathForCalendarDays(date)))
        calendarDay.click()
        return this
    }

    fun search(): TourSearchPage {
        searchButton.click()
        return this
    }

    fun getTrips(): List<Trip> {
        val departures = driver.getElements(By.xpath(XPath.TopTourSearchPage.DEPARTURE_CITY)).map { it.text }
        val arrivals = driver.getElements(By.xpath(XPath.TopTourSearchPage.ARRIVAL_CITY)).map { it.text }
        val datesArrival = driver.getElements(By.xpath(XPath.TopTourSearchPage.ARRIVAL_DATE)).map {
            val formatter = DateTimeFormatter.ofPattern("d.MM.yy", Locale("ru"))
            LocalDate.parse(it.text, formatter)
        }

        val trips = mutableListOf<Trip>()
        for (i in departures.indices) {
            trips.add(Trip(departures[i], arrivals[i], datesArrival[i]))
        }
        return trips
    }

    fun isToursFound(): Boolean {
        return driver.getElement(By.xpath(XPath.TopTourSearchPage.NO_TOURS_FOUND_TITLE)).isDisplayed == false
    }

}