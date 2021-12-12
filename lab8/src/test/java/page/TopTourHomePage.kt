package page

import conditions.PageLoadedCondition
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

class TopTourHomePage(driver: WebDriver) : AbstractPage(driver) {

    @FindBy(xpath = XPath.TopTourHomePage.AIRTICKETS)
    private lateinit var airtickets: WebElement

    @FindBy(xpath = XPath.TopTourHomePage.ONLINE_BOOKING)
    private lateinit var onlineBookingButton: WebElement

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

    fun navigateToOnlineBooking(): TourSearchPage {
        onlineBookingButton.click()
        driver.switchTo().window(driver.windowHandles.toList()[1])
        return TourSearchPage(driver)
    }

}