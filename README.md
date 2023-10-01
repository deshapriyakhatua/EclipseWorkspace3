

# Project Name - Find Friends Nearby

The "Find Friends Nearby" project is a social networking platform designed to help users discover and connect with new friends who are geographically close to them. The platform leverages location-based services to facilitate meaningful connections between individuals who share common interests and live in close proximity.

## Table of Contents

- [Project Description](#project-description)
- [Languages & Technologies Used](#languages-and-technologies-used)
- [Key Features](#key-features)
- [How It Works](#how-it-works)
- [Future Enhancements](#future-enhancements)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Configuration](#configuration)
- [API Documentation](#api-documentation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Project Description

The "Find Friends Nearby" project provides a platform that simplifies the process of finding like-minded individuals nearby. Whether you're in a new city, attending an event, or just looking to expand your social circle, this project offers a solution to connect with people who are physically close and share your hobbies or interests.
This project is designed to bridge the gap between online interactions and real-life friendships. By utilizing location-based matching and emphasizing shared interests, the platform aims to create genuine connections that can lead to lasting friendships and meaningful experiences.

Whether you're new to an area, looking to expand your social network, or simply interested in meeting new people, the "Find Friends Nearby" project offers a user-friendly and innovative solution to connect with individuals who are nearby and share common interests.

## Languages and Technologies Used

- Java
- MySQL
- JavaScript
- HTML
- CSS

## Key Features

- Geolocation Integration: The project integrates with location services to determine a user's current location, ensuring that potential friend matches are in close proximity.

- User Profiles: Users can create profiles with their interests, hobbies, and a brief bio. This information helps in identifying potential friends who share similar passions.

- Matching Algorithm: The project employs a matching algorithm that considers user preferences, interests, and proximity to suggest potential friends.

- Real-Time Messaging: Once users connect, they can communicate through a real-time messaging system, making it easy to plan meetups and get to know each other.

- Privacy Controls: Users have the ability to control their privacy settings and choose what information they want to share publicly.

- Notification System: The platform notifies users when there are potential friend matches nearby, encouraging them to initiate conversations and build connections.


## How It Works

- Profile Creation: Users create detailed profiles including their interests, a profile picture, and a brief description of themselves.

- Location Sharing: The platform uses the user's device location to determine their proximity to other users.

- Matching Process: The matching algorithm suggests potential friends based on shared interests and proximity.

- Connecting: Users can view profiles of potential matches and choose to connect. If both users agree, they become friends and gain access to the messaging feature.

- Messaging: Friends can exchange messages in real-time, making it easy to arrange meetups or learn more about each other.

- Privacy: Users have control over what information they share and can adjust their privacy settings as needed.

## Future Enhancements

- In the future, the project could expand by incorporating additional features such as:

- Interest-Based Groups: Allowing users to join and create groups centered around specific interests or activities.

- Event Integration: Displaying local events and activities to further facilitate real-life interactions between users.

- Enhanced Privacy Settings: Giving users more granular control over who can see their profile and location information.

- Multi-Platform Support: Developing mobile apps for iOS and Android platforms to enhance accessibility.

## Getting Started

Explain how to set up and run your backend project locally.

### Prerequisites

List any software, tools, or dependencies that users need to have installed to run the backend.

### Installation

Provide step-by-step instructions to install and run your backend. This might include things like:

1. Clone the repository: `git clone https://github.com/deshapriyakhatua/EclipseWorkspace3.git`
2. Navigate to the project directory: `cd findNearest`
3. Build the project: `./gradlew build`
4. Run the project: `./gradlew bootRun`

### Configuration

Explain any configuration files that need to be set up, environment variables that need to be defined, or database connections that need to be established.

## API Documentation

 This documentation provides an overview of the endpoints and functionalities that the API offers for discovering and connecting with new friends in close proximity.
 ### Base URL
```
 https://api.findfriendsnearby.com
```
### Authentication
Authentication is required to access certain endpoints. You need to include an authentication token in the headers of your requests.

```http
    Authorization: Bearer YOUR_AUTH_TOKEN
```

### Endpoints

1. User Registration

   - Endpoint: ` /signup `

   - Method: ` POST `

   - Description: -

   - Request:
     ```json
        {
          "email": "newuser@example.com",
          "password": "password123"
        }
       
     ```
       
    - Response:
      ```jsp
        Login Page
      ```
      
2. User Login
   - Endpoint: ` /login `
    
   - Method: ` POST `
    
   - Description: -
    
   - Request:
     ```json
        {
          "email": "user@example.com",
          "password": "password123"
        }
     ```
     
   - Response:
     ```jsp
         Home Page
     ```
     
3. Update User Profile
   - Endpoint: /updateUserDetails
    
   - Method: ` POST `
    
   - Description: -
    
   - Request:
     ```json
        {
          "name": "Firstname Lastname",
          "phone": "9876543210"
        }
     ```
     
   - Response:
     ```jsp
         Profile Page
     ```
     
4. Get Nearby Friends
   - Endpoint: /getOtherUsers
    
   - Method: ` post `
    
   - Description: Get a list of nearby friends based on the user's location.
    
   - Request:
     ```json
        {
            "userid": "0123456789-4587411-4874",
             "distance": "24",
             "gender": "male",
             ...
        }
     ```
     
   - Response:
     ```json
        [
            {
              "user_id": "789012-251424-45824",
              "username": "friend1",
              ...
            },
            {
              "user_id": "345678-548245-541525",
              "username": "friend2",
              ...
            },
             ...
       ]
     ```
     
5. Send Message
   - Endpoint: /sendMessage
    
   - Method: ` POST `
    
   - Request:
     ```json
        {
          "userid": "12548-458745-458745",
          "recipient_id": "789012",
          "message": "Hey, let's meet up for coffee!"
        }
     ```
     
   - Response:
     ```json
        true
     ```
     
### Error Handling
 The API returns appropriate error responses in case of invalid requests or other errors. For example:
 ```jsp
    Login Page
 ```
 
### Conclusion
This API documentation provides a comprehensive overview of the endpoints and functionalities available in the "Find Friends Nearby" project. Use these endpoints to register users, authenticate, update profiles, discover nearby friends, and send messages. If you have any questions or need further assistance, please contact our support team at support@findfriendsnearby.com.
 

## Usage

Explain how to use your backend's features, especially from a developer's perspective. Provide code examples and explanations if necessary.

## Contributing

If you're open to contributions, outline how others can contribute to your backend project. This might include instructions on how to fork the repository, make changes, and submit pull requests.

## License

Specify the license under which your backend project is distributed. For example, you can use the MIT License:

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

