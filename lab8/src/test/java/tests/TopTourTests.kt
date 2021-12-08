package tests

import junit.framework.Assert.assertTrue
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import page.TopTourHomePage
import java.time.LocalDate

class TopTourTests : CommonConditions() {

    @Test
    fun testSearchResultForAviatickets() {
        val source = "Киев"
        val destination = "Москва"
        val date = LocalDate.now()
        val aviaPage = TopTourHomePage(driver)
            .openPage()
            .navigateToAirTickets()
        val topAviaResultPage = aviaPage
            .enterDeparture(source)
            .enterArrival(destination)
            .enterDate(date)
            .submit()
            .waitUntilPageLoaded()

        val resultDate = topAviaResultPage.waitUntilPageLoaded().getDate()
        assertTrue(
            topAviaResultPage.getSource().contains(source)
                    && topAviaResultPage.getDestination().contains(destination)
                    && date.dayOfMonth == resultDate.dayOfMonth
                    && date.monthValue == resultDate.monthValue
        )
    }
}