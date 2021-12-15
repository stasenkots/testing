package page

import model.ChildCamp
import model.Hotel
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import utils.*
import java.time.LocalDate


class ToursRussiaPage(driver: WebDriver) : AbstractPage(driver) {

    @FindBy(xpath = XPath.ToursRussiaPage.DATE)
    private lateinit var dateInput: WebElement

    @FindBy(xpath = XPath.ToursRussiaPage.DATE_CHILD_CAMP)
    private lateinit var childCampDateInput: WebElement

    @FindBy(xpath = XPath.ToursRussiaPage.DATE)
    private lateinit var dateInputNewWindow: WebElement

    @FindBy(xpath = XPath.ToursRussiaPage.DATE_CHILD_CAMP)
    private lateinit var childCampDateInputNewWindow: WebElement

    @FindBy(xpath = XPath.ToursRussiaPage.FIND)
    private lateinit var findButton: WebElement

    @FindBy(xpath = XPath.ToursRussiaPage.INPUT)
    private lateinit var hotelInput: WebElement

    @FindBy(xpath = XPath.ToursRussiaPage.CHILD_CAMP_BUTTON)
    private lateinit var childCampButton: WebElement

    @FindBy(xpath = XPath.ToursRussiaPage.HOTEL_TITLE_NEW_WINDOW)
    private lateinit var hotelTitleNewWindow: WebElement

    fun openPage(): ToursRussiaPage {
        driver.switchTo().frame(driver.getElement(XPath.ToursRussiaPage.FRAME))
        driver.getElement(XPath.ToursRussiaPage.DATE)
        return this
    }

    fun enterHotel(hotelName: String): ToursRussiaPage {
        hotelInput.clear()
        hotelInput.sendKeys(hotelName)

        val firstItemOfDropdown = driver.getElement(XPath.ToursRussiaPage.FIRST_ITEM_OF_DROP_DOWN_MENU)
        firstItemOfDropdown.click()
        return this
    }

    fun enterDatesRange(arrivalDate: LocalDate, departureDate: LocalDate): ToursRussiaPage {
        dateInput.click()
        val arrivalDateInput = formatDateByDotPattern(arrivalDate)
        val departureDateInput = formatDateByDotPattern(departureDate)

        dateInput.clear()
        dateInput.sendKeys("$arrivalDateInput$departureDateInput")
        return this
    }

    fun enterDateArrival(arrivalDate: LocalDate): ToursRussiaPage {
        childCampDateInput.click()
        val arrivalDateInput = formatDateByDotPattern(arrivalDate)

        childCampDateInput.clear()
        childCampDateInput.sendKeys("$arrivalDateInput")
        return this
    }

    fun moveToNewWindow(): ToursRussiaPage {
        driver.switchTo().frame(driver.getElement(XPath.ToursRussiaPage.FRAME_NEW_WINDOW))
        return this
    }

    fun getHotel(): Hotel {
        driver.getElement(XPath.ToursRussiaPage.DATE)
        val hotelTitle = hotelTitleNewWindow.text

        val (dateArrival, dateDeparture) = dateInputNewWindow.getAttribute(ATTRIBUTE_VALUE)
            .split("-")
            .map { date -> formatStringByDotPattern(date) }
            .toPair()
        return Hotel(hotelTitle, dateArrival, dateDeparture)
    }

    fun navigateToChildCamp(): ToursRussiaPage {
        childCampButton.click()
        return this
    }

    fun enterChildCamp(campName: String): ToursRussiaPage {
        hotelInput.sendKeys(campName)
        val firstItemOfDropdown = driver.getElement(XPath.ToursRussiaPage.FIRST_ITEM_OF_DROP_DOWN_MENU)
        firstItemOfDropdown.click()
        return this
    }

    fun getResultTours(): List<Hotel> {
        return driver.getElements(XPath.ToursRussiaPage.TOUR)
            .map { Hotel(it.getAttribute(ATTRIBUTE_TITLE), LocalDate.now(), LocalDate.now()) }
    }

    fun submit(): ToursRussiaPage {
        findButton.click()
        return this
    }

    fun moveToMainFrame(): ToursRussiaPage {
        driver.switchTo().parentFrame()
        return this
    }

    fun getChildCamp(): ChildCamp {
        driver.getElement(XPath.ToursRussiaPage.DATE_CHILD_CAMP)
        val title = hotelTitleNewWindow.text

        val dateArrivalInput = childCampDateInputNewWindow.getAttribute(ATTRIBUTE_VALUE)
        val dateArrival = formatStringByDotPattern(dateArrivalInput)
        return ChildCamp(title, dateArrival)
    }

    fun isChildCampsFound(): Boolean {
        return driver.getElement(XPath.ToursRussiaPage.CHILD_CAMP_NOT_FOUND).isDisplayed == false
    }

    private companion object {
        const val ATTRIBUTE_VALUE = "value"
        const val ATTRIBUTE_TITLE = "title"
    }

}