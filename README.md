## 📋 Task Manager API

A simple RESTful API to manage tasks and notes, built with Spring Boot.

---

### 🔧 Configure Environment Profile

Set the active profile to control which database is used:

- `dev` ➝ Uses **H2** in-memory database (default)
- `prod` ➝ Uses **PostgreSQL** for production

```properties
# application.properties
spring.profiles.active=dev  # or prod
```

➡️ **For `prod` profile (PostgreSQL)**

If PostgreSQL is not installed on your system:

- 🔗 Download and install it from the official site:  
  👉 [https://www.postgresql.org/download/](https://www.postgresql.org/download/)

After installation, create a database named: `taskManagerDB`

---

✅ After setting up the database and configuring the profile, run your project and access your API at:

#### 🔗 Base URL : `http://localhost:8080`

---
<details>
  
<summary>📚 API Endpoints</summary>
<br/>
<details>
  <summary>Tasks</summary>
  <br/>
  <details>
<summary><strong>Get All Tasks</strong> &mdash; <code>GET http://localhost:8080/tasks</code></summary>

**Method:** `GET`  
**Endpoint:** `http://localhost:8080/tasks`

</details>

<details>
<summary><strong>Get Task By ID</strong> &mdash; <code>GET http://localhost:8080/tasks/{task_id}</code></summary>

**Method:** `GET`  
**Endpoint:** `http://localhost:8080/tasks/{id}`

**Path Variables:**
- Path variable: task_id(e.g., 1)

</details>

<details>
<summary><strong>Add Task</strong> <code>POST http://localhost:8080/tasks</code></summary>

**Method:** `POST`  
**Endpoint:** `http://localhost:8080/tasks`

**Request Body:**
```json
{
    "title":"Task 1",
    "description":"This is task 2.",
    "deadline":"2025-07-11",
    "completed":false
}
```
</details>

<details>
<summary><strong>Update Task By ID</strong> &mdash; <code>PUT http://localhost:8080/tasks/{task_id}</code></summary>

**Method:** `PUT`  
**Endpoint:** `http://localhost:8080/tasks/{task_id}`

**Path Variables:**
- Path variable: task_id(e.g., 1)

**Request Body:**
```json
{
    "title":"Task 1",
    "description":"This is task 2.",
    "deadline":"2025-07-11",
    "completed":true
}
```
</details>

<details>
<summary><strong>Patch Update Task By ID</strong> &mdash; <code>PATCH http://localhost:8080/tasks/{task_id}</code></summary>

**Method:** `PATCH`  
**Endpoint:** `http://localhost:8080/tasks/{task_id}`

**Path Variables:**
- Path variable: task_id(e.g., 1)

**Request Body:**
```json
{
    "completed": true
}
```
</details>

<details>
<summary><strong>Delete Task</strong> &mdash; <code>DELETE http://localhost:8080/tasks/{task_id}</code></summary>

**Method:** `DELETE`  
**Endpoint:** `http://localhost:8080/tasks/{id}`

**Path Variables:**
- Path variable: task_id(e.g., 1)

</details>
</details>

<details>
  <summary>Notes</summary>
  <br/>
<details>
<summary><strong>Get All Notes in Task</strong> &mdash; <code>GET http://localhost:8080/tasks/{task_id}/notes</code></summary>

**Method:** `GET`  
**Endpoint:** `http://localhost:8080/tasks/{task_d}/notes`

**Path Variables:**
- Path variable: task_id(e.g., 1)

</details>

<details>
<summary><strong>Get Note by ID</strong> &mdash; <code>GET http://localhost:8080/tasks/{task_id}/notes/{note_id}</code></summary>

**Method:** `GET`  
**Endpoint:** `http://localhost:8080/tasks/{task_id}/notes/{note_id}`

**Path Variables:**
- Path variable: task_id(e.g., 1)
- Path variable: note_id(e.g., 2)

</details>

<details>
<summary><strong>Create Note in Task</strong> &mdash; <code>POST http://localhost:8080/tasks/{task_id}/notes</code></summary>

**Method:** `POST`  
**Endpoint:** `http://localhost:8080/tasks/{task_id}/notes`

**Path Variables:**
- Path variable: task_id(e.g., 1)

**Request Body:**
```json
{
    "content":"This third test note"
}
```
</details>

<details>
<summary><strong>Update note by Id</strong> &mdash; <code>PATCH http://localhost:8080/tasks/{task_id}/notes/{note_id}</code></summary>

**Method:** `PATCH`  
**Endpoint:** `http://localhost:8080/tasks/{task_id}/notes/{note_id}`

**Path Variables:**
- Path variable: task_id(e.g., 1)
- Path variable: note_id(e.g., 2)

**Request Body:**
```json
{
    "content":"This third test note"
}
```

</details>

<details>
<summary><strong>Delete note by Id</strong> &mdash; <code>DELETE http://localhost:8080/tasks/{task_id}/notes/{note_id}</code></summary>

**Method:** `DELETE`  
**Endpoint:** `http://localhost:8080/tasks/{task_id}/notes/{note_id}`

**Path Variables:**
- Path variable: task_id(e.g., 1)
- Path variable: note_id(e.g., 2)

</details>
</details>
</details>
