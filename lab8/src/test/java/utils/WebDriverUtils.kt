package utils

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

fun WebDriver.getElement(xpath: String): WebElement {
    return WebDriverWait(this, Duration.ofSeconds(10))
        .until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)))
}


fun WebDriver.getElements(xpath: String): List<WebElement> {
    return WebDriverWait(this, Duration.ofSeconds(10))
        .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)))
}