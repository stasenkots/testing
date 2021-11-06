import junit.framework.Assert.assertTrue
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import page.HomePage
import java.time.LocalDate

class RWTests {

    private var _driver: WebDriver? = null
    private val driver: WebDriver
        get() = _driver!!

    @Before
    fun browserSetup() {
        _driver = ChromeDriver()
        driver.manage().window().maximize()
    }

    @Test
    fun testSearchResultForTimetableAndTicketSearch() {
        val source = "Минск"
        val destination = "Борисов"
        val expectedSources = setOf("Институт Культуры", "Минск-Пассажирский", "Минск-Восточный")
        val expectedDestinations = setOf("Борисов")
        val passRWRoutePage = HomePage(driver)
            .openPage()
            .enterFrom(source)
            .enterTo(destination)
            .enterDate(LocalDate.now().plusDays(2))
            .searchForTrain()

        assertTrue(
            passRWRoutePage.getSources().all { expectedSources.contains(it) }
                    && passRWRoutePage.getDestinations().all { expectedDestinations.contains(it) }
        )

    }

    @After
    fun browserTearDown() {
        driver.quit()
        _driver = null
    }

}