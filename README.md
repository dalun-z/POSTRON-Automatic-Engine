# POSTRON-Automatic-Engine

## Dev Log

- Language: Java
- JDK: jdk-20
- JRE: JavaSE-20
- Tool: Eclipse, appium, adb, node.js(latest), npm(latest)

### Setup Environment (Windows)
[Video Tutorial](https://www.youtube.com/watch?v=N7vY3cPSo8g)

[Appium-Java-Client Documentation](https://github.com/appium/java-client)

[selenium Documentation](https://github.com/SeleniumHQ/selenium)

- Setup `node.js` & `npm` environment
    - run `node --version` and `npm --version` on cmd to check if they work correctly
- Setup `ANDROID_HOME` and `JAVA_HOME` Path
    - will need SDK and JDK in this process 
    - [SDK-environment](https://developer.android.com/tools)
    - [JDK-download](https://www.oracle.com/java/technologies/downloads/)
- Setup Appium environment
    - run `npm install -g appium` on cmd
    - run `appium -v` to check if it works correctly
    - run `appium` to start appium server (will need to use this command later)
        - might also need install `UiAutomator2` to the driver
        - run `appium driver install uiautomator2`
        - run `appium dirver list` to check if the installation is done correctly
- Setup `adb` environment 
    - run `adb` on cmd to see if adb is working correctly
    - should be able to see the version of `adb`
- IDE (eclipse, Intellij)
- Selenium & appium libraies (maven)
- Connect 