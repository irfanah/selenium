// Licensed to the Software Freedom Conservancy (SFC) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The SFC licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

package org.openqa.selenium.firefox;

import static org.openqa.selenium.testing.Driver.FIREFOX;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.testing.Ignore;
import org.openqa.selenium.testing.JUnit4TestBase;

@Ignore(FIREFOX)
public class MarionetteTest extends JUnit4TestBase {

  private WebDriver driver;

  @After
  public void quitDriver() {
    if (driver != null) {
      driver.quit();
    }
  }

  @Test
  public void canStartDriverWithNoParameters() throws InterruptedException {
    driver = new FirefoxDriver();
    driver.get(pages.xhtmlTestPage);
    wait.until($ -> "XHTML Test Page".equals(driver.getTitle()));
  }

  @Test
  public void shouldUseFirefoxOptions() throws InterruptedException {
    DesiredCapabilities caps = new FirefoxOptions()
      .addPreference("browser.startup.page", 1)
      .addPreference("browser.startup.homepage", pages.xhtmlTestPage)
      .addTo(DesiredCapabilities.firefox());

    driver = new FirefoxDriver(caps);

    wait.until($ -> "XHTML Test Page".equals(driver.getTitle()));
  }

  @Test
  public void canSetProfileInFirefoxOptions() throws InterruptedException {
    FirefoxProfile profile = new FirefoxProfile();
    profile.setPreference("browser.startup.page", 1);
    profile.setPreference("browser.startup.homepage", pages.xhtmlTestPage);

    DesiredCapabilities caps = new FirefoxOptions().setProfile(profile)
        .addTo(DesiredCapabilities.firefox());

    driver = new FirefoxDriver(caps);

    wait.until($ -> "XHTML Test Page".equals(driver.getTitle()));
  }

  @Test
  public void canSetProfileInCapabilities() throws InterruptedException {
    FirefoxProfile profile = new FirefoxProfile();
    profile.setPreference("browser.startup.page", 1);
    profile.setPreference("browser.startup.homepage", pages.xhtmlTestPage);

    DesiredCapabilities caps = new DesiredCapabilities();
    caps.setCapability(FirefoxDriver.PROFILE, profile);

    driver = new FirefoxDriver(caps);

    wait.until($ -> "XHTML Test Page".equals(driver.getTitle()));
  }
}
