package tests

import model.Flight
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import page.TopTourHomePage
import java.time.LocalDate

import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.`is`
import org.junit.Assert


class TopTourTests : CommonConditions() {

    @Test
    fun testSearchResultForAviatickets() {
        val source = "Минск"
        val destination = "Москва"
        val date = LocalDate.now()

        val expectedFlight = Flight(source, destination, date)

        val aviaPage = TopTourHomePage(driver)
            .openPage()
            .navigateToAirTickets()
        val topAviaResultPage = aviaPage
            .enterDeparture(source)
            .enterArrival(destination)
            .enterDate(date)
            .submit()
            .getResultPage()
            .waitUntilPageLoaded()

        val flight = topAviaResultPage.getFlight()

        assertThat(flight,`is`(equalTo(expectedFlight)))
    }

    @Test
    fun testSearchResultForNotExistedAviatickets() {
        val source = "Гродно"
        val destination = "Нью-Йорк"
        val date = LocalDate.now()

        val aviaPage = TopTourHomePage(driver)
            .openPage()
            .navigateToAirTickets()
        val isTicketFound = aviaPage
            .enterDeparture(source)
            .enterArrival(destination)
            .enterDate(date)
            .submit()
            .isTicketsFound()

        Assert.assertTrue(isTicketFound)
    }
}