# Pro Framework: Appium + Selenium + TestNG (Java)
Single repo for **web**, **mobile-native**, and **mobile-web**, with **POM + PageFactory**, **WireMock**, real device–ready capabilities, Allure, retries, and GitHub Actions CI matrix.

## Run locally
```bash
# Web
mvn clean test -Dplatform=web -Dweb.browser=chrome

# Android native (real device)
mvn clean test -Dplatform=android_native -Dandroid.udid=<device_udid>  -Dandroid.appPackage=com.android.calculator2 -Dandroid.appActivity=com.android.calculator2.Calculator

# Android mobile web (Chrome)
mvn clean test -Dplatform=android_web -Dandroid.udid=<device_udid>

# iOS native (real device)
mvn clean test -Dplatform=ios_native -Dios.udid=<udid> -Dios.bundleId=com.example.demo

# iOS mobile web (Safari)
mvn clean test -Dplatform=ios_web -Dios.udid=<udid>
```

## WireMock quick start
```bash
# Starts automatically before suite on port 9090
# Example stub at /__admin
mvn -Dplatform=web -Dwiremock.enabled=true clean test
```

## CI with GitHub Actions
- Matrix over platforms: web, android_native, android_web, ios_native, ios_web.
- Uses **secrets** if present (e.g., `GRID_URL`, `BS_USERNAME`, `BS_ACCESS_KEY`). If not set, mobile jobs auto-skip.

## Structure
```
src/test/java/framework
  ├─ config/ (OWNER)
  ├─ core/ (BaseTest, ConfigManager)
  ├─ drivers/ (DriverFactory, MobileOptionsFactory, WebOptionsFactory)
  ├─ listeners/ (Allure listener, Retry)
  ├─ mocks/ (WireMock server + stubs)
  ├─ pages/
  │   ├─ web/...
  │   └─ mobile/
  │       ├─ native/...
  │       └─ web/...
  ├─ tests/
  │   ├─ web/...
  │   └─ mobile/
  │       ├─ native/...
  │       └─ web/...
  └─ utils/...
```

## Pushing to your GitHub repo
```bash
git init
git remote add origin https://github.com/<your-user>/<your-repo>.git
git add .
git commit -m "feat: pro framework with wiremock + mobile web/native + ci matrix"
git branch -M main
git push -u origin main
```
