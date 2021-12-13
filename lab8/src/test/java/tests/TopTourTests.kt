package tests

import model.Flight
import model.Hotel
import model.Trip
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Test
import page.TopTourHomePage
import java.time.LocalDate


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

        assertThat(isTicketFound, `is`(false))
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

        assertThat(trips, everyItem(equalTo(expectedTrip)))
    }

    @Test
    fun testFindNotExistedTour() {
        val source = "Минск"
        val destinationCountry = "ОАЭ"
        val destinationCity = "Ум Аль Кувейн"
        val date = LocalDate.now()

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

        assertThat(isToursFound, `is`(false))
    }

    @Test
    fun testFindToursInRussia() {
        val from = LocalDate.now().plusMonths(1)
        val to = from.plusWeeks(1)

        val topToursRussiaPage = TopTourHomePage(driver)
            .openPage()
            .navigateToRussiaTours()

        val hotels = topToursRussiaPage
            .enterDatesRange(from, to)
            .submit()
            .getResultTours()

        assertThat(hotels, not(empty()))
    }

    @Test
    fun testFindToursInRussiaInHotel() {
        val hotelName = "«Бархатные сезоны» город-отель (Екатерининский квартал)"
        val arrivalDate = LocalDate.now().plusMonths(1)
        val departureDate = arrivalDate.plusWeeks(1)

        val expectedHotel = Hotel(hotelName, arrivalDate, departureDate)

        val topToursRussiaPage = TopTourHomePage(driver)
            .openPage()
            .navigateToRussiaTours()

        val hotel = topToursRussiaPage
            .enterHotel(hotelName)
            .enterDatesRange(arrivalDate, departureDate)
            .submit()
            .getHotel()

        assertThat(hotel, `is`(equalTo(expectedHotel)))
    }
}