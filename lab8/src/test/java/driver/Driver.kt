package driver

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.safari.SafariDriver
import org.openqa.selenium.safari.SafariOptions


object Driver {

    private var driver: WebDriver? = null
    fun get(): WebDriver {

        if (driver == null) {
            val browser = System.getProperty(SystemProperty.BROWSER)
            driver = when (browser) {
                Chrome.name -> getChromeDriver()
                Firefox.name -> getFirefoxDriver()
                else -> getChromeDriver()
            }
            driver?.manage()?.window()?.maximize()
        }
        return driver!!
    }

    private fun getChromeDriver(): WebDriver {
        val options = ChromeOptions().apply {
            addArguments(
                "--window-size=1920,1080",
                "--start-maximized",
                "--headless",
                "--no-sandbox",
                "--disable-dev-shm-usage"
            )
        }
        WebDriverManager.chromedriver().setup()
        return ChromeDriver(options)
    }

    private fun getFirefoxDriver(): WebDriver {
        val options = FirefoxOptions().apply {
            addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage")
        }
        WebDriverManager.firefoxdriver().setup()
        return FirefoxDriver(options)
    }

    fun closeDriver() {
        driver?.quit()
        driver = null
    }
}