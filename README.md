# WeatherApp
<img src="https://github.com/ishan007/WeatherApp/blob/main/app/screenshots/screenshot_dropdown.png" width="200" />
<img src="https://github.com/ishan007/WeatherApp/blob/main/app/screenshots/screenshot_home.png" width="200" />
# How to setup
Clone the project in your local machine and open it in android studio.
To run unit test, click on test package and select "run test with coverage" option

## Architecture
App is built using Clean Architecture and MVVM.

![Architecture](https://github.com/ishan007/NewsApp/blob/master/app/screenshot/architecture.png)


* **DI:** This module is responsible for providing dependencies to the objects.

* **View:** This module is responsible for populating data and error on UI. It includes activities, fragments and adapters.

* **ViewModel:** This module is responisble for fetching data from domain layer, maintaining data state and providing data to view moudel. It includes ViewModels and custom observers.

* **Domain layer** This module includes all the use cases.
    
* **Repository:** This module provides data from **single source of truth** to domain layer. 


## Android architecture components used in this application are - 

* **[ViewModel:](https://developer.android.com/reference/android/arch/lifecycle/ViewModel.html)** Designed to store and manage UI related data in a life cycle conscious way

* **[Live Data:](https://developer.android.com/reference/android/arch/lifecycle/LiveData.html)** An observable data holder class that, unlike a regular observable, is life cycle aware, meaning it respects the lifecycle of other app components such as activities, fragments, and services


## Third party libaries used in this application are -

* **[Hilt:]([https://dagger.dev/tutorial/](https://developer.android.com/training/dependency-injection/hilt-android))** Dagger is compile-time dependency injection framework provided by google.

* **[Retrofit 2:](https://square.github.io/retrofit/)** A type-safe HTTP client for Android and Java  

* **[Mockito:](https://javadoc.io/static/org.mockito/mockito-core/2.9.0/org/mockito/Mockito.html)** A mocking framework for unit testing. The Mockito library enables mock creation, verification and stubbing.


## Language Used - 

* **[Kotlin:](https://kotlinlang.org/docs/reference/)** Kotlin is a great fit for developing Android applications, bringing all of the advantages of a modern language to the Android platform without introducing any new restrictions.
