
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
- **US‑1 — <short title>**  
  _Story:_ As a customer, I want … so that …  
  _Acceptance:_
  ```gherkin
  Scenario: <happy path>
    Given <preconditions>
    When  <action>
    Then  <observable outcome>
  ```

- **US‑2 — <short title>**  
  _Story:_ As a customer, I want … so that …  
  _Acceptance:_
  ```gherkin
  Scenario: <happy path>
    Given <preconditions>
    When  <action>
    Then  <observable outcome>
  ```

### 2.2 Provider Stories
- **US-20 — <short title>**  
  _Story:_ As a provider, I want … so that …  
  _Acceptance:_
  ```gherkin
  Scenario: <happy path>
    Given <preconditions>
    When  <action>
    Then  <observable outcome>
  ```

- **US-21 — <short title>**  
  _Story:_ As a provider, I want … so that …  
  _Acceptance:_
  ```gherkin
  Scenario: <happy path>
    Given <preconditions>
    When  <action>
    Then  <observable outcome>
  ```

### 2.3 SysAdmin Stories
- **US‑30 — <short title>**  
  _Story:_ As a sysadmin, I want … so that …  
  _Acceptance:_
  ```gherkin
  Scenario: <happy path>
    Given <preconditions>
    When  <action>
    Then  <observable outcome>
  ```

- **US‑31 — <short title>**  
  _Story:_ As a sysadmin, I want … so that …  
  _Acceptance:_
  ```gherkin
  Scenario: <happy path>
    Given <preconditions>
    When  <action>
    Then  <observable outcome>
  ```

---

## 3. Non‑Functional Requirements (make them measurable)
- **Performance:** description 
- **Availability/Reliability:** description
- **Security/Privacy:** description
- **Usability:** description

---

## 4. Assumptions, Constraints, and Policies
- list any rules, policies, assumptions, etc.

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