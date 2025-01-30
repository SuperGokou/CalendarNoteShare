# Calender Note Share

An app that allows users to add notes to a certain day for everyone to see.

## Code Organization 

All of our Java files are stored in the path `CalendarNoteShare/app/src/main/java/com/example/calendarapp/`
and all of our XML design files are stored in `CalendarNoteShare/app/src/main/res/layout/`

## How to run

Clone the app into Android Studio and build the program. Once built, run the program on an emulater using API 31 (it should default to that with the current settings)

## Workload

- Ming Xia - Created the layout for the frontend
- Quinn Croes - Helped create the interface for the app
- McGuire Croes - Set up the inital project structure and calender
- Colton Berry - Created Endpoints on the database to add and search the databse, also created the requests in the Java code to connected the add note and search 
- Andrew Keyes - Created the registration and login system for the app 

## Features

- **User Registration**: Allows new users to register and create their personal account.
- **User Login**: Secure login functionality for returning users.
- **View Notes**: Users can view their saved notes within the calendar interface.
- **Add Notes**: Provides the ability to add new notes to specific dates.
- **Interactive UI**: A user-friendly interface that makes navigation and operation intuitive.

## Technology Stack

- **Database**: MongoDB Atlas, utilized for storing user data and notes securely.
- **Backend**: MongoDB Realm Functions, facilitating data manipulation and management.
- **Frontend**: Integrated HTTP endpoints with JavaScript to connect the frontend with MongoDB Realm for dynamic data interaction.

## Application Setup

### Prerequisites

Ensure you have the following installed:
- MongoDB Atlas account
- Node.js
- Any modern web browser (Chrome, Firefox, Safari, etc.)
