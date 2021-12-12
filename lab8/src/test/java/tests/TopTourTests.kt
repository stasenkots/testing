package tests

import model.Flight
import model.Trip
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

        assertThat(flight, `is`(equalTo(expectedFlight)))
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

    @Test
    fun testFindTour() {
        val source = "Минск"
        val destinationCountry = "Грузия"
        val destinationCity = "Батуми"
        val date = LocalDate.now()
        val expectedTrip = Trip(source, destinationCity, date)

        val aviaPage = TopTourHomePage(driver)
            .openPage()
            .navigateToOnlineBooking()
        val topTourSearchPage = aviaPage
            .enterDeparture(source)
            .enterArrivalCountry(destinationCountry)
            .enterArrivalCity(destinationCity)
            .enterCalendarDay(date)
            .search()

        val trips = topTourSearchPage.getTrips()
        Assert.assertTrue(trips.all { expectedTrip == it })
    }

    @Test
    fun testFindNotExistedTour() {
        val source = "Минск"
        val destinationCountry = "ОАЭ"
        val destinationCity = "Ум Аль Кувейн"
        val date = LocalDate.now()
        val expectedTrip = Trip(source, destinationCity, date)

        val aviaPage = TopTourHomePage(driver)
            .openPage()
            .navigateToOnlineBooking()
        val topTourSearchPage = aviaPage
            .enterDeparture(source)
            .enterArrivalCountry(destinationCountry)
            .enterArrivalCity(destinationCity)
            .enterCalendarDay(date)
            .search()

        val isToursFound = topTourSearchPage.isToursFound()
        Assert.assertFalse(isToursFound)
    }
}