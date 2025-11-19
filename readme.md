# Android MVI Sample

This project is sample application that demonstrates MVI aproach in developing android applications.

![](https://raw.githubusercontent.com/zsasko/android-mvi-sample/main/assets/screens.png)

It displays a list of data fetched from:
- https://jsonplaceholder.typicode.com/comments

It uses a CommentsRepository for loading data via ApiService and data is displayed in CommentsListScreen:
- [CommentsRepository.kt](https://github.com/zsasko/android-mvi-sample/blob/main/app/src/main/java/com/zsasko/android/mvi/sample/data/repository/CommentsRepository.kt)
- [CommentsListScreen.kt](https://github.com/zsasko/android-mvi-sample/blob/main/app/src/main/java/com/zsasko/android/mvi/sample/ui/screens/comments/CommentsListScreen.kt)

If error occurs, during data load, ErrorScreen is displayed:
- [ErrorScreen.kt](https://github.com/zsasko/android-mvi-sample/blob/main/app/src/main/java/com/zsasko/android/mvi/sample/ui/screens/common/ErrorScreen.kt)

While data is being loaded, a progress screen - LoadingScreen, is displayed:
- [LoadingScreen.kt](https://github.com/zsasko/android-mvi-sample/blob/main/app/src/main/java/com/zsasko/android/mvi/sample/ui/screens/common/LoadingScreen.kt)

If user clicks on 'Reload Data' button, CommentIntent.ReloadData() is invoked to the view model, that loads data from repository.
- [CommentsViewModel.kt](https://github.com/zsasko/android-mvi-sample/blob/main/app/src/main/java/com/zsasko/android/mvi/sample/viewmodel/CommentsViewModel.kt)

Project contains 2 instrumented tests for testing data load without any problems and data load when error occurs:
- [CommentsScreenWithNetworkError.kt](https://github.com/zsasko/android-mvi-sample/blob/main/app/src/androidTest/java/com/zsasko/android/mvi/sample/CommentsScreenWithNetworkError.kt)
- [CommentsScreenWithNetworkSuccess.kt](https://github.com/zsasko/android-mvi-sample/blob/main/app/src/androidTest/java/com/zsasko/android/mvi/sample/CommentsScreenWithNetworkSuccess.kt)

It also has Unit tests for repository:
- [CommentsRepositoryTest.kt](https://github.com/zsasko/android-mvi-sample/blob/main/app/src/test/java/com/zsasko/android/mvi/sample/data/repository/CommentsRepositoryTest.kt)
  and viewmodel:
- [CommentsViewModelTest.kt](https://github.com/zsasko/android-mvi-sample/blob/main/app/src/test/java/com/zsasko/android/mvi/sample/viewmodel/CommentsViewModelTest.kt)
