Android Coding task 
=====================================

Introduction
-------------------------------------
This app is given as a task to show car list in Map and  List.

Requirements with comments in line :
● The minimum SDK supported for this application is API 21.
● The app is written strictly in Kotlin programming language.
● Added test cases.
● Error handlling.

● There are 2 sections. MAP, Car list.

● All the data is loaded from the provided API calls.
● Images are cached efficiently.
● API Requests/data loading and showing is done in a way to give the user the best experience.
    Kept in mind the JSON provided might have some data missing in some instances, 
    so these cases are handled gracefully to provide a good UX
● Responsive to all phone sizes.
● Used version control system (Git)

## Implemented things
view desgin is using via `Jetpack compose`
Repository is written in `Kotlin` and is based on `MVVM(ViewModel, LiveData)`
Dependency Injection is implemented using `Koin`
SOLID design principles are followed religiously - The Project is separated in app(presentation), domain and data layers

## Unit Testing

 Unit testing is achieved with `Junit`, `Mockito` and `MockWebServer`

### Libraries
* [Android Support Library][support-lib]
* [Jetpack compose][compose]
* [Android Architecture Components][arch]
* [Koin][koin] for dependency injection
* [Coroutine][coroutine] coroutine for concurrency and background process call
* [Retrofit][retrofit] for REST api communication
* [Coil][coil] for image loading
* [Espresso][espresso] for Android UI tests
* [Mockito][mockito] for evaluating app's logic using local unit tests
* [MockWebServer][mockwebserver] for testing HTTP clients


[mockwebserver]: https://github.com/square/okhttp/tree/master/mockwebserver
[compose]: https://developer.android.com/jetpack/compose
[support-lib]: https://developer.android.com/topic/libraries/support-library/index.html
[arch]: https://developer.android.com/arch
[koin]: https://github.com/InsertKoinIO/koin
[retrofit]: http://square.github.io/retrofit
[coroutine]: https://kotlinlang.org/docs/coroutines-guide.html
[coil]: https://coil-kt.github.io/coil/
[espresso]: https://developer.android.com/training/testing/espresso
[mockito]: https://site.mockito.org/
[mockwebserver]: https://github.com/square/okhttp/tree/master/mockwebserver

