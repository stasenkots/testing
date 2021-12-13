package page

import model.Hotel
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import utils.*
import java.time.LocalDate


class ToursRussiaPage(driver: WebDriver) : AbstractPage(driver) {

    @FindBy(xpath = XPath.ToursRussiaPage.DATE)
    private lateinit var dateInput: WebElement

    @FindBy(xpath = XPath.ToursRussiaPage.DATE)
    private lateinit var dateInputNewWindow: WebElement

    @FindBy(xpath = XPath.ToursRussiaPage.FIND)
    private lateinit var findButton: WebElement

    @FindBy(xpath = XPath.ToursRussiaPage.HOTEL)
    private lateinit var hotelInput: WebElement

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

        val firstItemOfDropdown = driver.getElement(XPath.ToursRussiaPage.FIRST_ITEM_OF_DROP_DOWN_MENU_HOTELS)
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

    fun getHotel(): Hotel {
        driver.switchTo().parentFrame().switchTo()
            .frame(driver.getElement(XPath.ToursRussiaPage.FRAME_NEW_WINDOW))
        driver.getElement(XPath.ToursRussiaPage.DATE)
        val hotelTitle = hotelTitleNewWindow.text

        val (dateArrival, dateDeparture) = dateInputNewWindow.getAttribute(ATTRIBUTE_VALUE)
            .split("-")
            .map { date -> formatStringByDotPattern(date) }
            .toPair()
        return Hotel(hotelTitle, dateArrival, dateDeparture)
    }

    fun getResultTours(): List<Hotel> {
        return driver.getElements(XPath.ToursRussiaPage.TOUR)
            .map { Hotel(it.getAttribute(ATTRIBUTE_TITLE), LocalDate.now(), LocalDate.now()) }
    }

    fun submit(): ToursRussiaPage {
        findButton.click()
        return this
    }

    private companion object {
        const val ATTRIBUTE_VALUE = "value"
        const val ATTRIBUTE_TITLE = "title"
    }

}