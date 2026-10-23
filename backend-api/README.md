# HobbyCircles backend API

# UML Class Diagram

![Class Diagram]()

# API Overview

**Version:** 1.0
**Last Updated:** October 23, 2026

**Base URL:**

- local: http://localhost:8080/api
- production: https://f26-team5.onrender.com/api/

JSON request bodies are validated. The endpoint tables list applicable error
codes: `400 Bad Request` for invalid input, `404 Not Found` for missing
resources, and `409 Conflict` for duplicate records or invalid state
transitions.

## Profile and discovery

| Method | Path                            | Purpose                                                    | Response code | Error codes | User story                                                              |
| ------ | ------------------------------- | ---------------------------------------------------------- | ------------- | ----------- | ----------------------------------------------------------------------- |
| `POST` | `/api/hobbygroups`              | Register a group profile   |`201 Created` | `400, 409`  |[US-2.1](../docs/SRS-HobbyCircles.md#L21)
| `PUT` | `/api/hobbygroups/{hobbyGroupId}`| Update a group profile     |`201 Created` | `400, 409`  |[US-2.1](../docs/SRS-HobbyCircles.md#L21)
| `GET` | `/api/hobbygroups/{hobbyGroupId}`| View a group profile       |`201 Created` | `400, 409`  |[US-2.1](../docs/SRS-HobbyCircles.md#L21)
| `GET` | `/api/hobbygroups`               | Browse groups              | `201 Created`| `400, 409`  |[US-1.?](../docs/SRS-HobbyCircles.md#L)

### Example Hobby Group Request

```http
POST /api/hobbygroups
Content-Type: application/json

 {
    "name": "Greensboro Trail Runners",
    "email": "trailrunners@example.com",
    "password": "RunFast2026",
    "interest": "OUTDOORS"
  }
```
### Example Hobby Group Update Request

```http
PUT /api/hobbygroups/{hobbyGroupId}
Content-Type: application/json

 {
    "name": "Greensboro Trail & Road Runners",
    "email": "runners@example.com",
    "interest": "SPORTS"
  }
```


## Events

| Method | Path                            | Purpose                    | Response code | Error codes | User story                                                              |
| ------ | ------------------------------- | ---------------------------------------------------------- | ------------- | ----------- | ----------------------------------------------------------------------- |
| `POST` | `/api/hobbygroups/{hobbyGroupId}/events`|Create an event     | `201 Created` | `400, 409`  | [US-2.2](../docs/SRS-HobbyCircles.md#L22)
| `PUT` | `/api/hobbygroups/{hobbyGroupId}/events/{eventId}`|Create an event| `201 Created` | `400, 409`  | [US-2.2](../docs/SRS-HobbyCircles.md#L22)  
| `GET` | `/api/hobbygroups/{hobbyGroupId}/events` | Browse events          | `201 Created`| `400, 409`  |[US-1.2](../docs/SRS-HobbyCircles.md#L15)
| `GET` | `/api/hobbygroups/{hobbyGroupId}/events` | View an event          | `201 Created`| `400, 409`  |[US-1.2](../docs/SRS-HobbyCircles.md#L15)     

Event time uses ISO-8601 local date-time values, for example
`"2030-05-10T14:30:00"`.

### Example Event Request

```http
POST /api/hobbygroups/{hobbyGroupId}/events
Content-Type: application/json

{
    "name": "Saturday Morning Group Run",
    "description": "Easy 5-mile run along the greenway. All paces welcome.",
    "interest": "OUTDOORS",
    "location": "Country Park, Greensboro",
    "time": "2026-11-07T08:30:00",
    "status": "PUBLISHED"
  }
```

### Example Event Update Request

```http
PUT /api/hobbygroups/{hobbyGroupId}/events/{eventId}
Content-Type: application/json

{
    "name": "Saturday Morning Group Run (Updated)",
    "description": "Moved to 9 AM. Now a 6-mile route.",
    "interest": "OUTDOORS",
    "location": "Country Park Pavilion 2, Greensboro",
    "time": "2026-11-07T09:00:00",
    "status": "PUBLISHED"
  }
```

## RSVPs and comments

| Method  | Path                                                          | Purpose                                               | Response code | Error codes | User story                                                              |
| ------- | ------------------------------------------------------------- | ----------------------------------------------------- | ------------- | ----------- | ----------------------------------------------------------------------- |
| `GET` | `/api/hobbygroups/{hobbyGroupId}/events/{eventId}/rsvps`| View RSVPs for an event| `201 Created`| `400, 409`  |[US-2.4](../docs/SRS-HobbyCircles.md#L24)                                  |
| `GET` | `/api/hobbygroups//{hobbyGroupId}/comments`| View comments| `201 Created`| `400, 409`  |[US-2.3](../docs/SRS-HobbyCircles.md#L23) 
| `POST` | `/{hobbyGroupId}/comments/{commentId}/replies`| Reply to comments| `201 Created`| `400, 409`  |[US-2.3](../docs/SRS-HobbyCircles.md#L23) 

### Example Comment Reply Request

```http
POST /{hobbyGroupId}/comments/{commentId}/replies
Content-Type: application/json

{
    "replyMessage": "Great question! Yes, beginners are welcome — we have a walk/run group too."
  }
```
