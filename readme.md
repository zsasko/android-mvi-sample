# Android MVI Sample

A sample Android application demonstrating the **MVI (Model-View-Intent) architecture**.

![](https://raw.githubusercontent.com/zsasko/android-mvi-sample/main/assets/screens.png)


The app fetches and displays a list of comments from:
- [JSONPlaceholder](https://jsonplaceholder.typicode.com/comments)

# Features
- **List Of Comments**
  - Loaded via CommentsRepository using ApiService and displayed in CommentsListScreen.
      - [CommentsRepository.kt](https://github.com/zsasko/android-mvi-sample/blob/main/app/src/main/java/com/zsasko/android/mvi/sample/data/repository/CommentsRepository.kt)
      - [CommentsListScreen.kt](https://github.com/zsasko/android-mvi-sample/blob/main/app/src/main/java/com/zsasko/android/mvi/sample/ui/screens/comments/CommentsListScreen.kt)

- **Error Handling**
  - Displays ErrorScreen if data loading fails.
      - [ErrorScreen.kt](https://github.com/zsasko/android-mvi-sample/blob/main/app/src/main/java/com/zsasko/android/mvi/sample/ui/screens/common/ErrorScreen.kt)

- **Loading State**
  - Shows LoadingScreen while data is being fetched.
      - [LoadingScreen.kt](https://github.com/zsasko/android-mvi-sample/blob/main/app/src/main/java/com/zsasko/android/mvi/sample/ui/screens/common/LoadingScreen.kt)

- **Reload Data**
  - Clicking the 'Reload Data' button triggers CommentIntent.ReloadData() in the ViewModel, fetching the data again.
      - [CommentsViewModel.kt](https://github.com/zsasko/android-mvi-sample/blob/main/app/src/main/java/com/zsasko/android/mvi/sample/viewmodel/CommentsViewModel.kt)

# Testing

- **Instrumented Tests**
  - Ensure proper UI behavior for both success and error scenarios:
      - [CommentsScreenWithNetworkError.kt](https://github.com/zsasko/android-mvi-sample/blob/main/app/src/androidTest/java/com/zsasko/android/mvi/sample/CommentsScreenWithNetworkError.kt)
      - [CommentsScreenWithNetworkSuccess.kt](https://github.com/zsasko/android-mvi-sample/blob/main/app/src/androidTest/java/com/zsasko/android/mvi/sample/CommentsScreenWithNetworkSuccess.kt)

- **Unit Tests**
  - Test the repository and ViewModel logic:
      - [CommentsRepositoryTest.kt](https://github.com/zsasko/android-mvi-sample/blob/main/app/src/test/java/com/zsasko/android/mvi/sample/data/repository/CommentsRepositoryTest.kt)
      - [CommentsViewModelTest.kt](https://github.com/zsasko/android-mvi-sample/blob/main/app/src/test/java/com/zsasko/android/mvi/sample/viewmodel/CommentsViewModelTest.kt)

# Technologies Used

- Kotlin
- Jetpack Compose
- MVI Architecture
- Retrofit (ApiService)
- JUnit & Espresso for testing

