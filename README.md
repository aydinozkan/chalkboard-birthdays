# Birthdays app challenge

## How to install and run

- Clone the repository
- Create a new project in Android Studio by selecting `New -> Project from Version Control`
- Paste the link in the URL and choose your Directory

## Reasoning behind the technical choices

- MVVM: Cleanly separates the user interface from the application logic. Divorcing one from the other improves application maintenance.
- Coroutines: Provides a very high level of concurrency with very small overhead
- Koin: is easy to use and integrates well with a Kotlin Android project
- Jetpack Navigation: Fits well with single activity approach and helps to implement navigation without building custom navigation structures

## Trade-offs

- Setting the minSdkVersion to 17, thus resulting to not being able to elegantly use some of Android's new features

## If I had more time
I would have built a modular structure with reusable components and would have spend more time on unit tests. 

## Contact details
- aydinmehmetozkan@gmail.com
- https://github.com/aydinozkan
- https://stackoverflow.com/users/2408186/aydinozkan
