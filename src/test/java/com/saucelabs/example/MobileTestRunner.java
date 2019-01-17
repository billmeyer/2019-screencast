package com.saucelabs.example;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.testng.TestNGCucumberRunner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

// @formatter:off
@CucumberOptions(
    // Features
    features = "src/test/resources/features",

    // Glue
    glue = {"com/saucelabs/example/stepdefs"},
    snippets = SnippetType.CAMELCASE,

    // Plugins
    plugin = {
        // Cucumber report location
        "json:target/cucumber-report/cucumber.json",
        "usage:target/cucumber-report/cucumber-usage.json",
        "html:target/cucumber-html-report"
    }
)
// @formatter:on
public class MobileTestRunner extends AbstractTestRunner
{
    @Parameters({"deviceName", "platform", "platformVersion"})
    @BeforeClass(alwaysRun = true)
    public void setUpMobileProfile(String deviceName, String platform, String platformVersion)
    {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());

        TestPlatform.Builder builder = new TestPlatform.Builder();

        // @formatter:off
        TestPlatform tp = builder
                .deviceName(deviceName)
                .platformName(platform)
                .platformVersion(platformVersion)
                .platformContainer(PlatformContainer.MOBILE)
                .build();
        // @formatter:on

        Util.setTestPlatform(tp);
    }
}
