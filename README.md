# Task Traker

## Add task
```
POST /tasks/task
```
**Request**
```
{
  "user_id": 3,
  "summary": "Do something",
  "description": "Some description",
  "priority": "LOW",
  "department": "IT"
}
```

**Response**
```
{
    "task_id": 14,
    "summary": "Do something",
    "description": "Some description",
    "priority": "LOW",
    "status": "PRODUCT_BACKLOG",
    "dateCreate": "2020-06-30T09:47:13.144",
    "department": "IT",
    "reporter_id": 3,
    "assignee_id": null,
    "comment_ids": [],
    "attachments_ids": []
}
```


## Get all tasks
```
GET /tasks
```
If specify the optional parameter `department`, the answer will be only with the task of this department. If the optional parameter `sortByDateCreate` is specified, the output will be sorted by the date the task was created.

**Request**
```
'department=IT&sortByDateCreate=true' http://localhost:8080/tasks
```

**Response**
```{
        "task_id": 1,
        "summary": "Do something",
        "description": "Some description",
        "priority": "LOW",
        "status": "PRODUCT_BACKLOG",
        "dateCreate": "2020-06-27T20:21:49.981",
        "department": "IT",
        "reporter_id": 1,
        "assignee_id": 7,
        "comment_ids": [],
        "attachments_ids": [
            1
        ]
    },
    {
        "task_id": 2,
        "summary": "Do some task",
        "description": "Some description",
        "priority": "LOW",
        "status": "PRODUCT_BACKLOG",
        "dateCreate": "2020-06-29T20:39:06.04",
        "department": "IT",
        "reporter_id": 3,
        "assignee_id": 5,
        "comment_ids": [
            8,
            9,
            10,
            11,
            12,
            13
        ],
        "attachments_ids": []
    },
]
```


## Get task by ID
```
GET /tasks/task/{task_id}
```
**Request**
```
http://localhost:8088/tasks/task/1
```

**Response**
```
{
	"task_id": 1,
	"summary": "Do something",
	"description": "Some description",
	"priority": "LOW",
	"status": "PRODUCT_BACKLOG",
	"dateCreate": "2020-06-27T20:21:49.981",
	"department": "IT",
	"reporter_id": 1,
	"assignee_id": 7,
	"comment_ids": [],
	"attachments_ids": [
		1
	]
}
```


## Get task comments 
```
GET /tasks/task/{task_id}/comments
```
**Request**
```
http://localhost:8088/tasks/task/5/comments
```

**Response**
```
[
    {
        "comment_id": 8,
        "dateCreate": "2020-06-20T20:50:09.738",
        "text": "Some text",
        "user_id": 5,
        "task_id": 5
    },
    {
        "comment_id": 9,
        "dateCreate": "2020-06-29T20:50:21.809",
        "text": "Some comment",
        "user_id": 5,
        "task_id": 5
    }
]
```


## Add attachments to the task
```
POST /tasks/task/{task_id}/upload"
```
The required request parameter is `file`.
**Request**
```
http://localhost:8089/tasks/task/1/upload
```
**Response**
```
You successfully uploaded 'file_name.txt'
```



### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/maven-plugin/reference/html/#build-image)

