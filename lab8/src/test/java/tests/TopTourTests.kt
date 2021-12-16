package tests

import model.*
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
            .openResultPage()

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
            .enterArrivalDate(date)
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
            .enterArrivalDate(date)
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
            .enterName(hotelName)
            .enterDatesRange(arrivalDate, departureDate)
            .submit()
            .moveToMainFrame()
            .moveToResultWindow()
            .getHotel()

        assertThat(hotel, `is`(equalTo(expectedHotel)))
    }

    @Test
    fun testFindChildCampInRussia() {
        val childCampName =
            "«Good Win» / «Гуд Вин» база лагеря детского отдыха"
        val arrivalDate = LocalDate.now()

        val expectedChildCamp = ChildCamp(childCampName, arrivalDate)

        val topToursRussiaPage = TopTourHomePage(driver)
            .openPage()
            .navigateToRussiaTours()

        val childCamp = topToursRussiaPage
            .navigateToChildCampTab()
            .enterName(childCampName)
            .enterDateArrival(arrivalDate)
            .submit()
            .moveToMainFrame()
            .moveToResultWindow()
            .getChildCamp()


        assertThat(childCamp, `is`(equalTo(expectedChildCamp)))
    }

    @Test
    fun testFindNotExistedChildCampInRussia() {
        val childCampName = "Детские лагеря"
        val arrivalDate = LocalDate.now()

        val topToursRussiaPage = TopTourHomePage(driver)
            .openPage()
            .navigateToRussiaTours()

        val isChildCampsFound = topToursRussiaPage
            .navigateToChildCampTab()
            .enterName(childCampName)
            .enterDateArrival(arrivalDate)
            .submit()
            .isToursFound()

        assertThat(isChildCampsFound, `is`(false))
    }

    @Test
    fun testFindExcursionsInRussia() {
        val excursionName = "«10 праздничных дней» (Орбита)"
        val arrivalDate = LocalDate.now()

        val expectedExcursion = Excursion(excursionName, arrivalDate)

        val topToursRussiaPage = TopTourHomePage(driver)
            .openPage()
            .navigateToRussiaTours()

        val excursion = topToursRussiaPage
            .navigateToExcursionsTab()
            .enterName(excursionName)
            .enterDateArrival(arrivalDate)
            .submit()
            .moveToMainFrame()
            .moveToResultWindow()
            .getExcursion()

        assertThat(excursion, `is`(equalTo(expectedExcursion)))
    }

    @Test
    fun testFindNotExistedCruisesInRussia() {
        val cruiseName = "Россия"
        val date = LocalDate.now()

        val topToursRussiaPage = TopTourHomePage(driver)
            .openPage()
            .navigateToRussiaTours()

        val isCruisesFound = topToursRussiaPage
            .navigateToCruises()
            .enterName(cruiseName)
            .enterDateArrival(date)
            .submit()
            .isToursFound()

        assertThat(isCruisesFound, `is`(false))
    }
}