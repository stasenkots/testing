package page

import model.Hotel
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import utils.getElement
import utils.getElements
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


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
        driver.switchTo().frame(driver.getElement(By.xpath(XPath.ToursRussiaPage.FRAME)))
        driver.getElement(By.xpath(XPath.ToursRussiaPage.DATE))
        return this
    }

    fun enterHotel(hotelName: String): ToursRussiaPage {
        hotelInput.clear()
        hotelInput.sendKeys(hotelName)

        val firstItemOfDropdown = driver.getElement(By.xpath(XPath.ToursRussiaPage.FIRST_ITEM_OF_DROP_DOWN_MENU_HOTELS))
        firstItemOfDropdown.click()
        return this
    }

    fun enterDatesRange(from: LocalDate, to: LocalDate): ToursRussiaPage {
        dateInput.click()
        val fromInput = "${from.dayOfMonth}${String.format("%02d", from.monthValue)}${from.year}"
        val toInput = "${to.dayOfMonth}${String.format("%02d", from.monthValue)}${to.year}"

        dateInput.clear()
        dateInput.sendKeys("$fromInput$toInput")
        return this
    }

    fun getHotel(): Hotel {
        driver.switchTo().parentFrame().switchTo()
            .frame(driver.getElement(By.xpath(XPath.ToursRussiaPage.FRAME_NEW_WINDOW)))
        driver.getElement(By.xpath(XPath.ToursRussiaPage.DATE))
        val hotelTitle = hotelTitleNewWindow.text

        val dates = dateInputNewWindow.getAttribute("value")
            .split("-")
            .map { date ->
                val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale("ru"))
                LocalDate.parse(date.trim(), formatter)
            }
        return Hotel(hotelTitle, dates[0], dates[1])
    }

    fun getResultTours(): List<Hotel> {
        return driver.getElements(By.xpath(XPath.ToursRussiaPage.TOUR))
            .map { Hotel(it.getAttribute("title"), LocalDate.now(), LocalDate.now()) }
    }

    fun submit(): ToursRussiaPage {
        findButton.click()
        return this
    }

}