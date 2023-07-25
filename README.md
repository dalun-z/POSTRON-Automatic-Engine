# POSTRON-Automatic-Engine

## Dev Log
- Language: Java
- JDK: jdk-20
- JRE: JavaSE-20
- Tool: Eclipse, appium, adb, node.js(latest), npm(latest), [apk-info](https://www.virustotal.com/gui/home/upload)

### Setup Environment (Windows)
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
- Connect Android device to PC
    - [start developer mode on Android device](https://www.digitaltrends.com/mobile/how-to-get-developer-options-on-android/#:~:text=Android%20Enable%20Developer%20Options%201%20If%20using%20stock,the%20very%20bottom%20of%20the%20menu.%20See%20More.)
    - use usb cable connect Android device to PC
    - run `adb devices` on cmd to check if the device connected successfully(should see the serial number of the connected Android device)
    - [Detailed steps to connect Android device through IP address](https://www.makeuseof.com/use-adb-over-wifi-android/)
        - Get the IP address of your android device
        - run `adb tcpip 5555`
        - run `adb connect 192.168.1.132` (the ip address you got from Android device)
        - now you can disconnect the usb cable and remotely connect to the android device
- Run appium server before you run the test code 

### Run Test (Windows)
- Check if the device is connected `adb devices`
    - if not, run the below command
    ```
    adb kill-server
    adb start-server
    adb tcpip 5555
    adb connect 192.168.1.116
    adb devices
    ```
- if appium server down, run `adb kill-server` then `adb start-server`
- open `C:\Users\DalunZhang\AppData\Local\Android\Sdk\tools\bin\uiautomatorviewer` to capture the elemenets of the app
- run `appium` on cmd to start appium server
- go to `PostronTest.java` right click the screen `Run as: Java Application`

### Code (Java)
- When looking for a element before the screen is fully loaded. Use `findElements` instead of `findElement`
    - Usage: `Boolean isPresent = driver.findElements(By.yourLocator).size() > 0`
    - [Source](https://sqa.stackexchange.com/questions/14190/how-to-continue-script-when-element-is-not-found-in-selenium)
    - Or use `NoSuchElementException`