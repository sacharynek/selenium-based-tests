package net.kozon.selenium.framework.tools.owasp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;

public class ProxyChromeDriverStrategy extends ProxyStrategy {

    @Override
    public WebDriver webDriver() throws IOException {
        return new ChromeDriver(owaspZAProxyService(), owaspZAProxyOptions());
    }

    private ChromeOptions owaspZAProxyOptions() {
        ChromeOptions options = new ChromeOptions();
        options.setProxy(proxyConfig());
        return options;
    }

    private ChromeDriverService owaspZAProxyService() throws IOException {
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(configuration.getPropertyFromFile("chromeDriver")))
                .usingAnyFreePort()
                .usingAnyFreePort()
                .build();
        service.start();
        return service;
    }
}
