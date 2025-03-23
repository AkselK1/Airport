# Airport Flight Booking Application

## Overview

This full-stack web application allows a flight passenger to plan a flight and select seats. The solution includes:
- **Back-end:** Built with Spring Boot (Java 21 LTS), Spring Data JPA, and PostgreSQL. It provides REST API endpoints for retrieving flights and seat data.
- **Front-end:** Built with Vue 3, TypeScript, Vue Router, Pinia, Vitest, ESLint, and Prettier. It offers a user-friendly interface for searching flights, filtering results, and selecting seats using a graphical layout.

## Features

- **Flight Search:** Users can search flights based on departure, destination, leaving date, and maximum price.
- **Graphical Seat Selection:** After selecting a flight, users see a graphical seat layout. 
  - Business class (rows 1–6) shows 4 seats per row (columns A and B on the left of the walking path, columns C and D on the right).
  - Economy (rows 7–30) shows 6 seats per row (columns A, B, C on the left and D, E, F on the right) with a walking path in the middle.
- **Seat Filters:** Filters to display only available seats, seats with more leg room, business class seats, and window seats.
- **Seat Selection:** Users can click on a seat to select it and then click "Save Selection" to finalize their choice.
- **REST API:** Endpoints for flights and seats (with proper CORS configuration) to allow smooth integration between front-end and back-end.

## Installation and Running the Application

### Prerequisites

- **Java 21 LTS**
- **Maven**
- **PostgreSQL** (configured as needed)
- **Node.js** and **npm**

### Back-End Setup

1. **Clone the Repository:**

   ```bash
   git clone <repository-url>
   cd airport

   Update the src/main/resources/application.properties file with your PostgreSQL connection.
   
   Build and Run the Application:
   mvn clean install
    mvn spring-boot:run

   Front-End Setup:
   Navigate to the Front-End Folder
   Install Dependencies:
   npm install
    Run the Development Server:
   npm run dev
Flight Search:

Visit http://localhost:5173/flights to view and search available flights.
Use the search form to filter flights by departure, destination, leaving date, and maximum price.
All flights are displayed by default if no filters are applied.
Seat Selection:
Click the "Select" button on a flight to navigate to the seat selection page (e.g., /seat-selection/18).
The seat selection page displays a graphical layout:
Business Class (Rows 1–6): Displays seats with columns A and B on the left of the walking path, and columns C and D on the right.
Economy (Rows 7–30): Displays 6 seats per row with a walking path in the middle.
Use filters on the seat selection page to narrow down options (e.g., available seats, more leg room, business class, window seats).
Click a seat to select it and then click the "Save Selection" button to confirm your choice.

### Development Notes
Time Spent
Approximately 30 hours were dedicated to this project, including design, development, and testing.

Challenges and Solutions
CORS Configuration:
Initially, API calls were blocked due to CORS issues (front-end on port 5173 vs. back-end on port 8080). This was resolved by adding @CrossOrigin annotations and configuring global CORS settings.

JSON Serialization and Entity Relationships:
Handling bidirectional relationships between Flight and Seat caused circular reference issues. These were resolved using Jackson annotations (@JsonManagedReference and @JsonBackReference) or by mapping entities to DTOs.

Graphical Seat Layout:
Designing the seat layout with a central walking path and different arrangements for business and economy classes was challenging. A table grid was used to dynamically generate rows and columns, inserting a walkway column where necessary. For example, for business class, the columns are arranged as A, B, walkway, C, D.

Filtering Logic:
In-memory filtering using computed properties in Vue allowed for flexible filtering on both the flight search and seat selection pages.
   
   

### My own thoughts
The hardest for me was front end part of the project, as i have not had that much practice developing front end.
I used AI's help for project setup at the start and AI's help was also used when developing the front end side of the project.
