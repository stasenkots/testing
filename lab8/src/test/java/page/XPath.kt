package page

import page.Urls
import java.time.LocalDate

object XPath {
    object TopAviaPage {
        const val DEPARTURE_FIELD = "//input[contains(@class,\"js-autofocus-field_departure\")]"
        const val ARRIVAL_FIELD = "//input[contains(@class,\"js-autofocus-field_arrival\")]"
        const val DATE_FIELD = "//input[contains(@class,\"js-autofocus-field_date\")]"
        const val SEARCH_BUTTON = "//*[contains(@class,\"nemo-flights-form__searchButton\")]"
        const val FIRST_ELEMENT_OF_DEPARTURE_LIST = "//*[@id=\"ui-id-1\"]/li[1]"
        const val FIRST_ELEMENT_OF_ARRIVAL_LIST = "//*[@id=\"ui-id-2\"]/li[1]"
        const val DIALOG_SEARCH_ERROR = "//*[@data-bind=\"text: searchError\"]"
        fun getXPathForCalendarDays(date: LocalDate): String {
            return """//div[@data-day = "${date.dayOfMonth}" and @data-month="${date.monthValue - 1}" and @data-year="${date.year}"]"""
        }
    }

    object TopAviaResultPage {
        const val SOURCE =
            "//*[contains(@class,\"nemo-flights-results__summary__route__segment__geo_departure\")]/*[@class= \"nemo-flights-results__summary__route__segment__geo__main\"]"
        const val DATE = "//*[@class = \"nemo-flights-results__summary__route__segment__date\"]"
        const val DESTINATION =
            "//*[contains(@class,\"nemo-flights-results__summary__route__segment__geo_arrival\")]/*[@class= \"nemo-flights-results__summary__route__segment__geo__main\"]"    }

    object TopTourHomePage {
        const val AIRTICKETS = "//a[@href=\"${Urls.AVIA}\"]"
    }
}