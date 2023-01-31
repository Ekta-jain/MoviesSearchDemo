# MoviesSearchDemo (Pagination + hilt + MVVM + Room db)


## 📃 TechStack used
- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Paging 3.0 with multiple viewType](https://developer.android.com/topic/libraries/architecture/paging/v3-overview) - The Paging library helps you load and display pages of data from a larger dataset from local storage or over network.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For asynchronous and more..
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - Jetpack Dependency Injection Framework
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Data objects that notify views when the underlying database changes.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes. 
  - [DataBinding](https://developer.android.com/topic/libraries/view-binding) - Generates a binding class for each XML layout file present in that module and allows you to more easily write code that interacts with views.
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.
- [ktlint](https://github.com/JLLeitschuh/ktlint-gradle) - This plugin creates convenient tasks in your Gradle project that run ktlint checks or do code auto format.



### Output Screenshots and Videos
---
| List Search | List Search 2 | Scrolled list | 
|:-:|:-:|:-:|
| ![Screenshot showing list search](https://user-images.githubusercontent.com/27636661/210199764-89ffda29-f2e4-4cc2-bb4e-654df492957e.png) | ![Screenshot showing showing list search 2](https://user-images.githubusercontent.com/27636661/210199800-8a1fda6d-a88b-474c-aee1-16c502f7cb54.png) | ![Screenshot showing scrolled list](https://user-images.githubusercontent.com/27636661/210199786-4a726575-7a40-4f87-aa7c-44f73aac329d.png) | 


## 🚀 Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites
*   Android Studio 3.2+
*   Java JDK

### Installing
Follow these steps if you want to get a local copy of the project on your machine.

#### 1. Clone or fork the repository by running the command below	
```
git clone https://github.com/Ekta-jain/MoviesSearchDemo

I have pushed server code on below path, I have added All the json and poster images in this server.

https://github.com/Ekta-jain/MoviesSearchDemo/tree/main/app/sampledata

one need to start this local server to run the app

Steps to run local server : https://github.com/Ekta-jain/MoviesSearchDemo/tree/main/app/sampledata/server 
Open above location on Visual studio code/ any editor

- yarn 
- yarn start

Yoo hoo, your Server would be start, now you can get response and rexpected images from local server.



