Make sure to update database credentials (`username`, `password`, `url`)
in [application.properties](./src/main/resources/application.properties)

Global exception handler is <b>omitted</b> in this sample project.

<hr />

cURL operations:

- Create a user

```
curl --location --request POST 'http://localhost:8080/users' \
  --header 'Content-Type: application/json' \
  --data-raw '{
  "name": "Lorem",
  "surname": "Ipsum",
  "email": "lorem@ipsum.com"
  }'
```

- Get user by id

```
curl --location --request GET 'http://localhost:8080/users/{userId}'
```

- Update user by id

```
curl --location --request PUT 'http://localhost:8080/users/{userId}' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Updated Lorem",
    "surname": "Updated Ipsum",
    "email": "updatedlorem@ipsum.com"
}'
```

- Delete user by id

```
curl --location --request DELETE 'http://localhost:8080/users/{userId}'
```

- Get revision of a user by id

```
curl --location --request GET 'http://localhost:8080/users/{userId}/revisions'
```