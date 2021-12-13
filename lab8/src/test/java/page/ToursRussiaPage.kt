package page

import model.Hotel
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import utils.getElement
import utils.getElements
import java.time.LocalDate


class ToursRussiaPage(driver: WebDriver) : AbstractPage(driver) {

    @FindBy(xpath = XPath.ToursRussiaPage.DATE)
    private lateinit var dateInput: WebElement

    @FindBy(xpath = XPath.ToursRussiaPage.FIND)
    private lateinit var findButton: WebElement

    fun openPage(): ToursRussiaPage {
        driver.switchTo().frame(driver.getElement(By.xpath(XPath.ToursRussiaPage.FRAME)))
        driver.getElement(By.xpath(XPath.ToursRussiaPage.DATE))
        return this
    }

    fun enterDatesRange(from: LocalDate, to: LocalDate): ToursRussiaPage {
        dateInput.click()
        val fromInput = "${from.dayOfMonth}${from.monthValue}${from.year}"
        val toInput = "${to.dayOfMonth}${to.monthValue}${to.year}"

        dateInput.sendKeys("$fromInput - $toInput")
        return this
    }


    fun getResultTours(): List<Hotel> {
        return driver.getElements(By.xpath(XPath.ToursRussiaPage.FIND)).map { Hotel(it.getAttribute("title")) }
    }

    fun submit(): ToursRussiaPage {
        findButton.click()
        return this
    }

}