
# Requirements 

**Project Name:** Hobby Circles \
**Team:** Claire Farmer - Provider, Jay Solomon - Customer \
**Course:** CSC 340\
**Version:** 1.0\
**Date:** 2026-09-18

---

## 1. Overview
**Vision.** HobbyCircles connects like minded individuals who struggle to find well-suited hobby groups on a centralized online platform. The system allows for hobby groups to advertise their events and for individuals to RSVP and comment on event posts.

**Glossary** Terms used in the project
- **Groups:** hobby group organizers.
- **Individuals:** those who are RSVPing to events. 
- **Events:** Posts that groups are able to create to communicate information about meetings.

**Primary Users / Roles.**
- **Customer/Individual** — Find groups that align with their personal hobbies
- **Provider/Group** — Advertise their group and draw in new members. 

**Scope (this semester).**
- <capability 1> Profiles for individuals and groups with interest tags
- <capability 2> Search feature with filtering for interest tags
- <capability 3> Groups can create/post events
- <capability 4> Individuals/groups can comment on posts and respond to comments.
- <capability 5> Calender of events for indivuals and groups 

**Out of scope (deferred).**
- <deferred 1> Private messaging and online video chats for groups
- <deferred 2> Group albums to showcase photos 
- <deferred 2> Users/groups can see their stats

> This document is **requirements‑level** and solution‑neutral; design decisions (UI layouts, API endpoints, schemas) are documented separately.

---

## 2. Functional Requirements (User Stories)
Write each story as: **As a `<role>`, I want `<capability>`, so that `<benefit>`.** Each story includes at least one **Given/When/Then** scenario.

### 2.1 Customer Stories
- **US‑1 — Register an account**  
  _Story:_ As a customer, I want to create a profile so that I can browse hobby groups
  _Acceptance:_
  ```gherkin
  Scenario: Register an account
    Given I am not registered 
    When  I provide valid registration details
    Then  I should be successfully registered and logged in
    And   I can see hobby groups available 
  ```

- **US‑2 — Browse groups by interest**  
  _Story:_ As a customer, I want to browse groups filtered by interest so that I can find groups aligned with my hobbies  
  _Acceptance:_
  ```gherkin
  Scenario: Browse groups by interest tag
    Given I am logged in as a user
    When  I select an interest tag
    Then  I can see all hobby groups labeled with that tag
  ```

- **US‑3 — RSVP to events**  
  _Story:_ As a customer, I want to RSVP to events so that my attendance is accounted for
  _Acceptance:_
  ```gherkin
  Scenario: RSVP to an event
    Given I am logged in as a user
    When  I select an event posted by a hobby group
    Then  The event is shown in my calendar
  ```

- **US‑4 — Leave comments under group pages**  
  _Story:_ As a customer, I want to leave comments on hobby group profiles so that I can ask questions.
  _Acceptance:_
  ```gherkin
  Scenario: Leave comments under group profiles
    Given I am logged in as a user
    When  I post a comment on a group profile
    Then  The comment should be saved and be visisble to groups and other users
  ```

- **US‑5 — View calendar of events**  
  _Story:_ As a customer, I want to have a private calendar so that I can keep track of my past and future events that I have RSVP'd to.  
  _Acceptance:_
  ```gherkin
  Scenario: View calendar of events
    Given I am logged in as a user
    When  I view my account profile
    Then  I can view a calender of my RSVP'd events (past and future)
    And   Only I can view my calendar 
  ```

### 2.2 Provider (Group) Stories
- **US-6 — Create group profile**  
  _Story:_ As a group, I want to create events so that individuals can find my group and RSVP to my events .
  _Acceptance:_
  ```gherkin
  Scenario: <Create group profile>
    Given <I do not have a profile>
    When  <I provide details and submit the form>
    Then  <my profile should be created>
    And <my profile should be visible to customers>
  ```

- **US-7 — Publish events**  
  _Story:_ As a group, I want to publish events so that I can advertise my group and its events.
  _Acceptance:_
  ```gherkin
  Scenario: <Publish events>
    Given <I am logged in as my group>
    When  <I add my event details>
    Then  <the event should be saved and visible to individuals>
  ```

  **US-8 — Publish and reply to comments**  
  _Story:_ As a group, I want to publish and reply to comments so that customer questions can be answered. 
  _Acceptance:_
  ```gherkin
  Scenario: <Publish and reply to comments>
    Given <I am logged in as group>
    When  <I recieve a comment or want to add additional information to an event post>
    Then  <I should be able to submit a response or standalone comment under the event>
  ```

  **US-8 — View events calendar**  
  _Story:_ As a group, I want to view my events calendar so that I can see all future events hosted by my group
  _Acceptance:_
  ```gherkin
  Scenario: <View events calendar>
    Given <I am logged in as group>
    When  <I view my profile>
    Then  <I should be able to view a calendar holding all upcoming events>
  ```

## 3. Non‑Functional Requirements (make them measurable)
- **Performance:** 95% of discovery responses should be returned in less than 2 seconds under typical load. 
- **Availability/Reliability:** The system should be available 99.5% of the time, with planned maintenance windows communicated in advance.
- **Security/Privacy:** The system must implement secure authentication and authorization mechanisms. All sensitive data should be encrypted in transit and at rest.
- **Usability:** New users should be able to complete the registration process and RSVP to an event within 5 minutes without external assitance.

---

## 4. Assumptions, Constraints, and Policies
- Modern browsers (latest Chrome/Firefox/Edge/Safari) and stable connectivity.
- Course timeline and campus infrastructure constraints apply.
- Individuals and groups are expected to provide accurate profile information
- User data must be handeled securely
- HobbyCircles should be reasonably accessible, including keyboard navigation and screenreading capabilities
---

## 5. Milestones (course‑aligned)
- **M1 Requirements** — this file + stories opened as issues. 
- **M2 High‑fidelity prototype** — core customer/provider flows fully interactive. 
- **M3 Design** — architecture, schema, API outline. 
- **M4 Backend API** — key endpoints + tests. 
- **M5 Increment** — ≥2 use cases end‑to‑end. 
- **M6 Final** — complete system & documentation. 

---

## 6. Change Management
- Stories are living artifacts; changes are tracked via repository issues and linked pull requests.  
- Major changes should update this SRS.
- Changes that exceed project scope or course restraints should be discussed with the course instructor.