package page

import conditions.PageLoadedCondition
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

class TopTourHomePage(driver: WebDriver) : AbstractPage(driver) {

    @FindBy(xpath = "//a[@href=\"${Urls.AVIA}\"]")
    private lateinit var airtickets: WebElement

    fun openPage(): TopTourHomePage {
        driver.get(Urls.HOME)
        WebDriverWait(
            driver,
            Duration.ofSeconds(10)
        ).until(PageLoadedCondition())
        return this
    }

    fun navigateToAirTickets(): TopAviaPage {
        airtickets.click()
        driver.switchTo().window(driver.windowHandles.toList()[1])
        return TopAviaPage(driver).openPage()
    }

}