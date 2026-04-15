# JobSeeker Android App

A simple and structured Android application built with Java and XML for job seekers. This project is designed to demonstrate core Android development concepts such as Activities, Intents, RecyclerView, and User Session management.

## 🚀 Features

- **Authentication**: Simple Login and Sign-up screens (Accepts any credentials for easy testing).
- **Job Listings**: A scrollable list of 15+ jobs from top-tier tech companies.
- **Detailed Descriptions**: Full job details including experience range, responsibilities, and perks.
- **Application Tracking**: Prevent multiple applications; the "Apply" button changes to "Applied" once submitted.
- **Resume Upload**: Integration with a file picker to select PDF resumes.
- **Responsive UI**: Fixed overlapping issues with system bars and navigation using `fitsSystemWindows`.

## 🛠️ Technical Stack

- **Language**: Java
- **UI Layouts**: XML (LinearLayout, RelativeLayout, ScrollView)
- **Navigation**: Explicit & Implicit Intents
- **Data Display**: RecyclerView with Custom Adapter
- **State Management**: Singleton-based UserSession for login and application status

## 📸 Screenshots & Images

To add images to this README, follow these steps:

1. Create a folder named `screenshots` in the root of this project.
2. Place your images (e.g., `login.png`, `main.png`, `detail.png`) inside that folder.
3. Link them here using the following syntax:

```markdown
### Login Screen
![Login](screenshots/login.png)

### Job List
![Main Screen](screenshots/main.png)
```

## 🏗️ Project Structure

- `MainActivity.java`: Displays the list of available jobs.
- `LoginActivity.java`: Handles user authentication.
- `JobDetailActivity.java`: Shows full job requirements and the Apply button.
- `ApplyActivity.java`: Form for user details and PDF resume selection.
- `JobAdapter.java`: Connects the Job data to the RecyclerView.
- `UserSession.java`: Manages the global state (Login status and Applied jobs).

## 📝 Concepts Used

Detailed explanation of concepts like Intents, RecyclerView, and File Handling can be found in the `concepts_used.txt` file in the root directory.

## ⚙️ How to Run

1. Open the project in **Android Studio**.
2. Sync the project with Gradle files.
3. Run the app on an Emulator or Physical Device (API 31+ recommended).
4. Enter any Email/Password to Login and start browsing jobs!
