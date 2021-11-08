package page

import conditions.PageLoadedCondition
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.WebDriverWait
import utils.getElement
import java.time.Duration
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class TopAviaPage(driver: WebDriver) : AbstractPage(driver) {

    @FindBy(xpath = "//input[@class=\"nemo-ui-textInput__input nemo-ui-dummiedInput__input nemo-flights-form__route__segment__input nemo-flights-form__route__segment__input_autocomplete js-autofocus-field js-autofocus-field_departure ui-autocomplete-input\"]")
    private lateinit var fromInput: WebElement

    @FindBy(xpath = "//input[@class=\"nemo-ui-textInput__input nemo-ui-dummiedInput__input nemo-flights-form__route__segment__input nemo-flights-form__route__segment__input_autocomplete js-autofocus-field js-autofocus-field_arrival ui-autocomplete-input\"]")
    private lateinit var toInput: WebElement

    @FindBy(xpath = "//input[@class=\"nemo-ui-textInput__input nemo-ui-dummiedInput__input nemo-flights-form__route__segment__input nemo-flights-form__route__segment__input_date js-autofocus-field js-autofocus-field_date nemo-flights-form__route__segment__input_date_there\"]")
    private lateinit var dateInput: WebElement

    @FindBy(xpath = "//*[@class=\"nemo-ui-button nemo-ui-button_main nemo-ui-button nemo-ui-button_main_form nemo-flights-form__searchButton\"]")
    private lateinit var findButton: WebElement

    fun openPage(): TopAviaPage {
        driver.getElement(
            By.xpath("//input[@class=\"nemo-ui-textInput__input nemo-ui-dummiedInput__input nemo-flights-form__route__segment__input nemo-flights-form__route__segment__input_autocomplete js-autofocus-field js-autofocus-field_departure ui-autocomplete-input\"]")
        )

        return this
    }

    fun enterFrom(from: String): TopAviaPage {
        fromInput.sendKeys(from + "\n")
        val prompt = driver.getElement(By.xpath("//*[@id=\"ui-id-1\"]/li[1]"))
        prompt.click()
        return this
    }

    fun enterTo(to: String): TopAviaPage {
        toInput.sendKeys(to)
        val prompt = driver.getElement(By.xpath("//*[@id=\"ui-id-2\"]/li[1]"))
        prompt.click()
        return this
    }

    fun enterDate(date: LocalDate): TopAviaPage {
        dateInput.click()
        selectDate(date)
        return this
    }

    fun submit() {
        findButton.click()
    }

    fun getSource(): String {
        return driver.getElement(
            By.xpath("//*[@class=\"nemo-flights-results__summary__route__segment__geo nemo-flights-results__summary__route__segment__geo_departure\"]/*[@class= \"nemo-flights-results__summary__route__segment__geo__main\"]")
        ).text
    }

    fun getDate(): LocalDate {
        val dateOutput =
            driver.getElement(By.xpath("//*[@class = \"nemo-flights-results__summary__route__segment__date\"]"))
                .text
                .substringBefore(',')
                .plus(" 1970")
        val formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale("ru"))
        return LocalDate.parse(dateOutput, formatter)
    }

    fun getDestination(): String {
        return driver.getElement(
            By.xpath("//*[@class=\"nemo-flights-results__summary__route__segment__geo nemo-flights-results__summary__route__segment__geo_arrival\"]/*[@class= \"nemo-flights-results__summary__route__segment__geo__main\"]")
        ).text
    }

    private fun selectDate(date: LocalDate) {
        val selectedDate =
            driver.getElement(By.xpath(getXPathForCalendarDays(date)))
        selectedDate.click()
    }


    private fun getXPathForCalendarDays(date: LocalDate): String {
        return """//div[@data-day = "${date.dayOfMonth}" and @data-month="${date.monthValue - 1}" and @data-year="${date.year}"]"""

    }

}