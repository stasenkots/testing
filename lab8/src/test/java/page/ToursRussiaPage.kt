package page

import model.ChildCamp
import model.Excursion
import model.Hotel
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import utils.*
import java.time.LocalDate


class ToursRussiaPage(driver: WebDriver) : AbstractPage(driver) {

    @FindBy(xpath = XPath.ToursRussiaPage.DATE)
    private lateinit var datesRangeInput: WebElement

    @FindBy(xpath = XPath.ToursRussiaPage.ARRIVAL_DATE)
    private lateinit var dateArrivalInput: WebElement

    @FindBy(xpath = XPath.ToursRussiaPage.DATE)
    private lateinit var dateInputNewWindow: WebElement

    @FindBy(xpath = XPath.ToursRussiaPage.ARRIVAL_DATE)
    private lateinit var childCampDateInputNewWindow: WebElement

    @FindBy(xpath = XPath.ToursRussiaPage.SEARCH)
    private lateinit var searchButton: WebElement

    @FindBy(xpath = XPath.ToursRussiaPage.INPUT)
    private lateinit var hotelInput: WebElement

    @FindBy(xpath = XPath.ToursRussiaPage.CHILD_CAMP_BUTTON)
    private lateinit var childCampButton: WebElement

    @FindBy(xpath = XPath.ToursRussiaPage.HOTEL_TITLE_RESULT_WINDOW)
    private lateinit var hotelTitleNewWindow: WebElement

    @FindBy(xpath = XPath.ToursRussiaPage.EXCURSIONS_BUTTON)
    private lateinit var excursionsButton: WebElement

    @FindBy(xpath = XPath.ToursRussiaPage.CRUISES_BUTTON)
    private lateinit var cruisesButton: WebElement

    fun openPage(): ToursRussiaPage {
        Logger.info("Open tours russia page")
        driver.switchTo().frame(driver.getElement(XPath.ToursRussiaPage.FRAME))
        driver.getElement(XPath.ToursRussiaPage.DATE)
        return this
    }

    fun enterName(name: String): ToursRussiaPage {
        Logger.info("Enter name - $name")
        hotelInput.clear()
        hotelInput.sendKeys(name)

        val firstItemOfDropdown = driver.getElement(XPath.ToursRussiaPage.FIRST_ITEM_OF_DROP_DOWN_MENU)
        firstItemOfDropdown.click()
        return this
    }

    fun enterDatesRange(arrivalDate: LocalDate, departureDate: LocalDate): ToursRussiaPage {
        Logger.info("Enter arrival date - $arrivalDate; departure date - $departureDate")
        datesRangeInput.click()
        val arrivalDateInput = formatDateByDotPattern(arrivalDate)
        val departureDateInput = formatDateByDotPattern(departureDate)

        datesRangeInput.clear()
        datesRangeInput.sendKeys("$arrivalDateInput$departureDateInput")
        return this
    }

    fun enterDateArrival(arrivalDate: LocalDate): ToursRussiaPage {
        Logger.info("Enter arrival date - $arrivalDate")
        dateArrivalInput.click()
        val arrivalDateInput = formatDateByDotPattern(arrivalDate)

        dateArrivalInput.clear()
        dateArrivalInput.sendKeys("$arrivalDateInput")
        return this
    }

    fun moveToResultWindow(): ToursRussiaPage {
        Logger.info("Move to result window")
        driver.switchTo().frame(driver.getElement(XPath.ToursRussiaPage.FRAME_RESULT_WINDOW))
        return this
    }

    fun getHotel(): Hotel {
        Logger.info("Get hotel")
        driver.getElement(XPath.ToursRussiaPage.DATE)
        val hotelTitle = hotelTitleNewWindow.text

        val (dateArrival, dateDeparture) = dateInputNewWindow.getAttribute(ATTRIBUTE_VALUE)
            .split("-")
            .map { date -> formatStringByDotPattern(date) }
            .toPair()
        val hotel = Hotel(hotelTitle, dateArrival, dateDeparture)
        Logger.info("Hotel - $hotel")
        return hotel
    }

    fun navigateToChildCampTab(): ToursRussiaPage {
        Logger.info("Navigate to child camp tab")
        childCampButton.click()
        return this
    }

    fun getResultTours(): List<Hotel> {
        Logger.info("Get hotels")
        val hotels = driver.getElements(XPath.ToursRussiaPage.TOUR)
            .map { Hotel(it.getAttribute(ATTRIBUTE_TITLE), LocalDate.now(), LocalDate.now()) }
        Logger.info("Hotels - $hotels")
        return hotels
    }

    fun submit(): ToursRussiaPage {
        Logger.info("Click search button")
        searchButton.click()
        return this
    }

    fun moveToMainFrame(): ToursRussiaPage {
        Logger.info("Click search button")
        driver.switchTo().parentFrame()
        return this
    }

    fun getChildCamp(): ChildCamp {
        Logger.info("Get child camp")

        driver.getElement(XPath.ToursRussiaPage.ARRIVAL_DATE)

        val title = hotelTitleNewWindow.text

        val dateArrivalInput = childCampDateInputNewWindow.getAttribute(ATTRIBUTE_VALUE)
        val dateArrival = formatStringByDotPattern(dateArrivalInput)
        val childCamp = ChildCamp(title, dateArrival)
        Logger.info("Child camp - $childCamp")
        return childCamp
    }

    fun getExcursion(): Excursion {
        Logger.info("Get excursion")
        driver.getElement(XPath.ToursRussiaPage.ARRIVAL_DATE)

        val title = hotelTitleNewWindow.text

        val dateArrivalInput = childCampDateInputNewWindow.getAttribute(ATTRIBUTE_VALUE)
        val dateDeparture = formatStringByDotPattern(dateArrivalInput)
        val excursion = Excursion(title, dateDeparture)
        Logger.info("Excursion - $excursion")
        return excursion
    }

    fun navigateToExcursionsTab(): ToursRussiaPage {
        Logger.info("Navigate to excursion tab")

        excursionsButton.click()
        return this
    }

    fun navigateToCruises(): ToursRussiaPage {
        Logger.info("Navigate to cruises tab")

        cruisesButton.click()
        return this
    }

    fun isToursFound(): Boolean {
        val isToursFound = driver.getElement(XPath.ToursRussiaPage.TOURS_NOT_FOUND).isDisplayed == false
        Logger.info("Is tours found - $isToursFound")
        return isToursFound
    }

    private companion object {
        const val ATTRIBUTE_VALUE = "value"
        const val ATTRIBUTE_TITLE = "title"
    }

}