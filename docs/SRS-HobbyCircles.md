
# Requirements 

**Project Name:** Hobby Circles \
**Team:** Claire Farmer - Provider, Jay Solomon - Customer \
**Course:** CSC 340\
**Version:** 1.0\
**Date:** 2026-09-18

---

## 1. Overview
**Vision.** One or two sentences: who this is for, the core problem, and the outcome.

**Glossary** Terms used in the project
- **Term 1:** description.
- **Term 2:** description

**Primary Users / Roles.**
- **Customer (e.g., Student/Patient/Pet Owner/etc. )** — 1 line goal statement.
- **Provider (e.g., Teacher/Doctor/Pet Sitter/etc. )** — 1 line goal statement.
- **SysAdmin (optional)** — 1 line goal statement.

**Scope (this semester).**
- <capability 1>
- <capability 2>
- <capability 3>

**Out of scope (deferred).**
- <deferred 1>
- <deferred 2>

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

### 2.2 Provider (Group) Stories
- **US-6 — <Create group profile>**  
  _Story:_ As a group, I want to create events so that individuals can find my group and RSVP to my events .
  _Acceptance:_
  ```gherkin
  Scenario: <Create group profile>
    Given <I do not have a profile>
    When  <I provide details and submit the form>
    Then  <my profile should be created>
    And <my profile should be visible to customers>
  ```

- **US-7 — <Publish events>**  
  _Story:_ As a group, I want to publish events so that I can advertise my group and its events.
  _Acceptance:_
  ```gherkin
  Scenario: <Publish events>
    Given <I am logged in as my group>
    When  <I add my event details>
    Then  <the event should be saved and visible to individuals>
  ```

  **US-8 — <Publish and reply to comments>**  
  _Story:_ As a group, I want to publish and reply to comments so that customer questions can be answered. 
  _Acceptance:_
  ```gherkin
  Scenario: <Publish and reply to comments>
    Given <I am logged in as group>
    When  <I recieve a comment or want to add additional information to an event post>
    Then  <I should be able to submit a response or standalone comment under the event>
  ```

  **US-8 — <View events calendar>**  
  _Story:_ As a group, I want to view my events calendar so that I can see all future events hosted by my group
  _Acceptance:_
  ```gherkin
  Scenario: <View events calendar>
    Given <I am logged in as group>
    When  <I view my profile>
    Then  <I should be able to view a calendar holding all upcoming events>
  ```

## 3. Non‑Functional Requirements (make them measurable)
- **Performance:** description 
- **Availability/Reliability:** description
- **Security/Privacy:** description
- **Usability:** description

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