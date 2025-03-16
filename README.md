# The Movie Database (TMDB) - Android App

This is a modern Android application built using Kotlin and Jetpack Compose, showcasing a clean architecture, efficient state management, and beautiful UI design. The app fetches and displays movie data from [The Movie Database (TMDB) API](https://developer.themoviedb.org/docs).

## Features

*   **Browse Movies:** Explore a curated list of movies that are currently playing.
*   **Responsive UI:** The app adapts beautifully to different screen sizes and orientations thanks to Jetpack Compose.
*   **Loading and Error Handling:** The app gracefully handles network requests and displays appropriate loading and error states.
*   **Image Loading:** Uses Coil library for efficient asynchronous image loading and caching.
* **Clean Architecture:** The app is structured with a clear separation of concerns, making it easy to maintain and scale.

## Tech Stack

*   **Kotlin:** The primary programming language.
*   **Jetpack Compose:** Modern toolkit for building native Android UIs.
*   **Coil:** For asynchronous image loading and caching.
*   **The Movie Database (TMDB) API:** For fetching movie data.
*   **Hilt:** Dependency Injection
* **ViewModel:** For State management
* **Navigation Compose:** For navigation between screens
* **Material3:** For the design system.

## Architecture

The app follows a clean architecture approach with a clear separation of concerns:

*   **UI Layer (`ui` package):** Contains composables for displaying the UI, handling user interactions, and observing the UI state.
*   **ViewModel Layer:** Contains the `ViewModel` classes that act as an intermediary between the UI and the data layer. They hold and manage the UI state, handle business logic, and communicate with the repository.
*   **Data Layer (`data` package):** Handles data access, including fetching data from the TMDB API and potentially local databases. It includes the repository.
*   **Domain Layer (`domain` package):** Contains business logic, use cases, and entities. It interacts with the repository to get data.
* **Model (`model` package):** Contains the data models used across the application.

## Getting Started

1.  **Clone the Repository:**
2.  **TMDB API Key:**
    *   Create an account at [https://www.themoviedb.org/](https://www.themoviedb.org/).
    *   Request an API key from your account settings.
    *   Add your API key to the `local.properties` file in the root of your project:
3.  **Open in Android Studio:** Open the project in Android Studio.
4.  **Build and Run:** Build the project and run it on an emulator or a physical device.

## Future Improvements

*   **Movie Details Screen:** Implement a detailed view for individual movies.
* **Search Functionality**: Add the ability to search for movies
*   **More Data:** Show cast, similar movies, and more from the API.
* **Navigation**: Add navigation between the different screens.
*   **Testing:** Add Unit and UI tests.
*   **Database:** Implement a local database to cache movies.
*   **Pagination:** Load more movies when the user scrolls.
* **Favorites**: Add the possibility to mark movies as favorites.

## Contributing

Contributions are welcome! If you'd like to contribute, please:

1.  Fork the repository.
2.  Create a new branch for your feature or bug fix.
3.  Submit a pull request with your changes.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.