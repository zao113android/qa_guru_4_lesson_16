package io.github.qaguru.owner;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:${environment}.properties")
public interface WebDriverConfig extends Config {

    @Key("webDriverBrowser")
    String getWebDriverBrowser();

    @Key("webDriverBrowserVersion")
    String getWebDriverBrowserVersion();

    @Key("remoteWebDriver")
    String getRemoteWebDriverUrl();

    @Key("videoStorage")
    String getVideoStorage();
}
