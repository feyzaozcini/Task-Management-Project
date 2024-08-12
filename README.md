### User Service
Users register and log in to the system

Method	| Path	| Description	
------------- | ------------------------- | ------------- |
POST	| /auth/register	| Register for users	| 
POST	| /auth/login	| Login for users	|
GET	| /auth/{id}	| Get specific user information by id	| 
GET	| /auth/ids	| Get multiple user information	| 

### Project Service
Projects and information are recorded in the system

Method	| Path	| Description 
------------- | ------------------------- | ------------- |
GET	| /projects	| Get all the projects	
GET	| /projects/{id} |Get specific project by id	  
POST	| /projects	| Create new project	
POST	| /projects/search	|Searching for projects with specific features
PUT	| /projects	| Update project	
DELETE	| /projects/{id}	| Delete project by id	

### Task Service
Task creation service using user and project service

Method	| Path	| Description	
------------- | ------------------------- | ------------- |
GET	| /tasks	| Get all the tasks
GET	| /tasks/{id}	| Get specific task by id	
POST	| /tasks	| Create new task
POST	| /tasks/search	| Searching for tasks with specific features
PUT	| /tasks	| Update task

### Notification Service
The Notification Service listens for task creation and update events using Kafka. It produces notifications for these events and records them in Couchbase.

### Kafka Topics:
taskservice.task_created.0: Published when a task is created.

taskservice.task_updated.0: Published when a task is updated.
