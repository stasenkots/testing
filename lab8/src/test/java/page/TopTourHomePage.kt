package page

import conditions.PageLoadedCondition
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.WebDriverWait
import utils.Logger
import java.time.Duration

class TopTourHomePage(driver: WebDriver) : AbstractPage(driver) {

    @FindBy(xpath = XPath.TopTourHomePage.AIRTICKETS)
    private lateinit var airtickets: WebElement

    @FindBy(xpath = XPath.TopTourHomePage.ONLINE_BOOKING)
    private lateinit var onlineBookingButton: WebElement

    @FindBy(xpath = XPath.TopTourHomePage.RUSSIA)
    private lateinit var russiaButton: WebElement

    fun openPage(): TopTourHomePage {
        Logger.info("Open toptour home page")
        driver.get(Urls.HOME)
        WebDriverWait(
            driver,
            Duration.ofSeconds(10)
        ).until(PageLoadedCondition())
        return this
    }

    fun navigateToAirTickets(): TopAviaPage {
        Logger.info("Click airtickets button")
        airtickets.click()
        driver.switchTo().window(driver.windowHandles.toList()[1])
        return TopAviaPage(driver).openPage()
    }

    fun navigateToOnlineBooking(): TourSearchPage {
        Logger.info("Click online-booking button")
        onlineBookingButton.click()
        driver.switchTo().window(driver.windowHandles.toList()[1])
        return TourSearchPage(driver).openPage()
    }

    fun navigateToRussiaTours(): ToursRussiaPage {
        Logger.info("Click Russia button")
        russiaButton.click()
        return ToursRussiaPage(driver).openPage()
    }

}