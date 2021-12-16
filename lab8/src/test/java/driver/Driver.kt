package driver

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.safari.SafariDriver
import org.openqa.selenium.safari.SafariOptions


object Driver {

    private var driver: WebDriver? = null
    fun get(): WebDriver {

        if (driver == null) {
            val browser = System.getProperty(SystemProperty.BROWSER)
            driver = when (browser) {
                Chrome.name -> {
                    WebDriverManager.chromedriver().setup()
                    ChromeDriver()
                }
                Safari.name -> {
                    WebDriverManager.safaridriver().setup()
                    SafariDriver()
                }
                else -> {
                    WebDriverManager.chromedriver().setup()
                    ChromeDriver()
                }
            }
            driver?.manage()?.window()?.maximize()
        }
        return driver!!
    }

    fun closeDriver() {
        driver?.quit()
        driver = null
    }
}