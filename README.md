# Fitness Centre Microservices Project

This project, developed for a University subject "Software Components," aims to create a microservices application for a fitness centre. The application architecture encompasses a frontend implemented in Java Swing, an API Gateway using Zuul, a service registry - Eureka, and three microservices developed with Java Spring:

- **User Service**
- **Training Reservation Service**
- **Notification Service**

## Overview

This project is a comprehensive Personal Training Scheduling System designed to streamline the booking and management of personal training sessions. It consists of three main services: User Service, Training Scheduling Service, and Notification Service. These services collectively handle user authentication and authorization, schedule management, and notification dispatch.

## Features

### User Service
- **User Types:** Supports multiple user roles (admin, client, gym manager) with specific privileges and data attributes.
  - Admin: All privileges, no additional attributes.
  - Client: Unique membership number, number of booked sessions.
  - Gym Manager: Gym name, employment date.
- **Registration:** Clients and managers can register and receive activation emails via the Notification Service.
- **Login:** Users log in with email and password, receiving a JWT upon successful authentication.
- **Profile Management:** Users can edit their profile details, except for membership number and booked session count.
- **Access Control:** Admins can disable and re-enable user access.

### Training Scheduling Service
- **Gym Data Management:** Gym managers can add and update gym details, including available training types and their costs.
- **Session Listing:** Users can browse available training sessions with filters for type, individual/group sessions, and day of the week.
- **Booking:** Clients can book sessions, triggering an asynchronous notification for confirmation. Session availability and client booking count are updated accordingly.
- **Cancellation:** Both clients and managers can cancel bookings, with appropriate updates to session availability and client booking count.
- **Loyalty Benefits:** Managers can define loyalty rewards, such as a free session after a specified number of bookings.

### Notification Service
- **Notification Types:** Admins can define, update, and delete notification templates, which include parameters for customization.
- **Activation Email:** Sent upon user registration via an asynchronous request.
- **Password Reset Email:** Dispatched upon request for password change.
- **Booking Confirmation:** Sent to both client and gym manager when a session is successfully booked.
- **Cancellation Notification:** Sent to relevant parties upon session cancellation.

## Technologies Used

- **Frameworks/Libraries:** Netflix Zuul (API Gateway), Eureka (Service Discovery)
- **Tools:** Message Broker (e.g., RabbitMQ)

