import junit.framework.Assert.assertTrue
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import page.TopAviaPage
import page.TopTourHomePage
import java.time.LocalDate

class TopTourTests {

    private var _driver: WebDriver? = null
    private val driver: WebDriver
        get() = _driver!!

    @Before
    fun browserSetup() {
        val option = ChromeOptions()
        option.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage")
        _driver = ChromeDriver(option)
        driver.manage().window().maximize()
    }

    @Test
    fun testSearchResultForAviatickets() {
        val source = "Киев"
        val destination = "Москва"
        val date = LocalDate.now()
        val aviaPage = TopTourHomePage(driver)
            .openPage()
            .navigateToAirTickets()
        aviaPage
            .enterFrom(source)
            .enterTo(destination)
            .enterDate(date)
            .submit()

        val resultDate = aviaPage.getDate()
        assertTrue(
            aviaPage.getSource().contains(source)
                    && aviaPage.getDestination().contains(destination)
                    && date.dayOfMonth == resultDate.dayOfMonth
                    && date.monthValue == resultDate.monthValue
        )

    }

    @After
    fun browserTearDown() {
        driver.quit()
        _driver = null
    }

}