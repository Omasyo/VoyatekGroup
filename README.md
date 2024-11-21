## Running the application

### Requirements

- [Android SDK and related tools](https://developer.android.com/studio)
- [Android Studio](https://developer.android.com/studio) or [IntelliJ Idea](https://www.jetbrains.com/idea/) or any suitable IDE
- Android Emulator and a virtual device or a physical android device

### How to Run

- Clone this repository on you computer device and open the project using Android Studio, IntelliJ Idea or any suitable
  IDE.
- Connect to your Android device or virtual device using [adb](https://developer.android.com/tools/adb).
- Using Android Studio/IDEA you can sync the gradle project and run the application using the default configurations.
- Alternatively you can install the app directly on the device using gradle by running `./gradlew installRelease` from
  the root directory.
- The network client can be reconfigured [here](app/src/main/java/com/example/voyatekgroup/network/Client.kt), you can set a new base url by modifying the host variable.

## API Endpoints

POST `/trips/`: Create a trip<br>
GET `/trips/`: Get all trips<br>
POST `/trips/{id}`: Get trip with id

### Download
You can download the app [here](https://github.com/Omasyo/VoyatekGroup/releases/download/v1.0.0/app-release.apk)