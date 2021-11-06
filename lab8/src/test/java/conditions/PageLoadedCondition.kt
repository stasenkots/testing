package conditions

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.ExpectedCondition


class PageLoadedCondition : ExpectedCondition<Boolean> {
    override fun apply(driver: WebDriver): Boolean {
        return (driver as JavascriptExecutor).executeScript("return document.readyState") == "complete"
    }
}
