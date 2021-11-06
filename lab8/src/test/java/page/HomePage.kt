package page

import conditions.PageLoadedCondition
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import utils.getElement
import java.time.Duration
import java.time.LocalDate

class HomePage(driver: WebDriver) : AbstractPage(driver) {

    private val HOMEPAGE_URL = "https://pass.rw.by/ru/"

    @FindBy(id = "one-way-raspFormFromTitle")
    private lateinit var fromInput: WebElement

    @FindBy(id = "one-way-raspFormToTitle")
    private lateinit var toInput: WebElement

    @FindBy(name = "front_date")
    private lateinit var dateInput: WebElement

    @FindBy(xpath = """//*[@id="filter-tab_2-1"]/descendant-or-self::*[@type="submit"]""")
    private lateinit var searchButton: WebElement

    fun openPage(): HomePage {
        driver.get(HOMEPAGE_URL)
        WebDriverWait(
            driver,
            Duration.ofSeconds(10)
        ).until(PageLoadedCondition())
        return this
    }

    fun enterFrom(from: String): HomePage {
        fromInput.sendKeys(from)
        return this
    }

    fun enterTo(to: String): HomePage {
        toInput.sendKeys(to)
        return this
    }

    fun enterDate(date: LocalDate): HomePage {
        dateInput.click()
        selectDate(date)
        return this
    }

    fun searchForTrain(): RoutePage {
        searchButton.click()
        return RoutePage(driver)
    }

    private fun selectDate(date: LocalDate) {
        val selectedDate =
            driver.getElement(By.xpath(getXPathForCalendarDays(date)))
        selectedDate.click()
    }


    private fun getXPathForCalendarDays(date: LocalDate): String {
        return """//td[@data-handler = "selectDay" and @data-month="${date.monthValue}" and @data-year="${date.year}"]/a[text() ="${date.dayOfMonth}"]"""

    }

}