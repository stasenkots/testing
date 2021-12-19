package page

import java.time.LocalDate

object XPath {
    object TopAviaPage {
        const val DEPARTURE_FIELD = "//input[contains(@class,\"js-autofocus-field_departure\")]"
        const val ARRIVAL_FIELD = "//input[contains(@class,\"js-autofocus-field_arrival\")]"
        const val DATE_FIELD = "//input[contains(@class,\"js-autofocus-field_date\")]"
        const val SEARCH_BUTTON = "//*[contains(@class,\"nemo-flights-form__searchButton\")]"
        const val FIRST_ELEMENT_OF_ARRIVAL_LIST = "//*[@id=\"ui-id-2\"]/li[1]"
        const val DIALOG_SEARCH_ERROR = "//*[@data-bind=\"text: searchError\"]"
        fun getXPathForCalendarDays(date: LocalDate): String {
            return """//div[@data-day = "${date.dayOfMonth}" and @data-month="${date.monthValue - 1}" and @data-year="${date.year}"]"""
        }
    }

    object TopAviaResultPage {
        const val DEPARTURE =
            "//*[contains(@class,\"nemo-flights-results__summary__route__segment__geo_departure\")]/*[@class= \"nemo-flights-results__summary__route__segment__geo__main\"]"
        const val DATE = "//*[@class = \"nemo-flights-results__summary__route__segment__date\"]"
        const val ARRIVAL =
            "//*[contains(@class,\"nemo-flights-results__summary__route__segment__geo_arrival\")]/*[@class= \"nemo-flights-results__summary__route__segment__geo__main\"]"
    }

    object TopTourHomePage {
        const val AIRTICKETS = "//a[@href=\"${Urls.AVIA}\"]"
        const val ONLINE_BOOKING = "//a[@href=\"${Urls.ONLINE_BOOKING}\"]"
        const val RUSSIA = "//a[@href=\"${Urls.RUSSIA}\"]"
    }

    object TopTourSearchPage {
        const val CLOSE_ARRIVAL_CITY =
            "//*[@class=\" paddingRightLeft5px \"]//*[@class=\"select2-search-choice-close\"]"
        const val CLOSE_ARRIVAL_COUNTRY =
            "//*[@class=\"select2-search-choice\"]//*[@class=\"select2-search-choice-close\"]"
        const val FIELD_DEPARTURE = "//*[contains(@class,\"search-placeholder\")]"
        const val FIELD_ARRIVAL_COUNTRY = "//*[@id = \"s2id_autogen6\"]"
        const val FIELD_ARRIVAL_CITY = "//*[@id = \"s2id_autogen7\"]"
        const val CALENDAR_INPUT = "//*[@id = \"dates\"]"
        const val SEARCH_BUTTON = "//*[contains(@class, \"btn-search\")]"
        const val MASK = "//*[@id = \"select2-drop-mask\"]"
        const val DEPARTURE_CITY = "//*[contains(@data-bind,\"CityDepature.Value\")]"
        const val ARRIVAL_CITY = "//*[contains(@data-bind,\"City.Value\")]"
        const val ARRIVAL_DATE = "//*[contains(@data-bind,\"text:FromDateShown\")]"
        const val NO_TOURS_FOUND_TITLE = "//*[contains(@data-bind,\"text: noToursFoundTitle()\")]"
        fun getListDropdownElement(value: String): String {
            return "//*[@class=\"select2-result-label\" and text() = \"${value}\"]"
        }

        fun getXPathForCalendarDays(date: LocalDate): String {
            return """//*[@class = "day activeClass" and text() = "${date.dayOfMonth}"]"""
        }
    }

    object ToursRussiaPage {
        const val FRAME = "//*[@id=\"stellsPartner1\"]"
        const val DATE = "//*[@id = \"iDrPicker-input\"]"
        const val ARRIVAL_DATE = "//*[contains(@class, \"iDatePicker-input\")]"
        const val SEARCH = "//button[contains(@class,\"finderCatalog-submit\")]"
        const val INPUT = "//*[@id=\"finderLook-input\"]"
        const val TOUR = "//*[contains(@class,\"resultGrid-productName\")]"
        const val FIRST_ITEM_OF_DROP_DOWN_MENU = "//*[contains(@class,\"dropdown-menu\")]/li[1]"
        const val HOTEL_TITLE_RESULT_WINDOW = "//*[contains(@class,\"product-title\")]"
        const val FRAME_RESULT_WINDOW = "//*[@id = \"stellsPartnerModal\"]/descendant-or-self::iframe"
        const val CHILD_CAMP_BUTTON =
            "//*[text() = \"Детские лагеря\"]//parent::*[contains(@class, \"finderCatalog-menuItem\")]"
        const val TOURS_NOT_FOUND = "//*[contains(@class, \"alert-warning\")]"
        const val EXCURSIONS_BUTTON =
            "//*[text() = \"Экскурсии\"]//parent::*[contains(@class, \"finderCatalog-menuItem\")]"
        const val CRUISES_BUTTON = "//*[text() = \"Круизы\"]//parent::*[contains(@class, \"finderCatalog-menuItem\")]"
    }
}