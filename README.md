# Rokomari Pet Project
RESTful in Peace

### Project Runtime Overview
1. First need to register a user.

header
```
token=Bearer
```

request body
```json
{
	"username":"fahim",
	"password":"123",
	"firstName": "Fahim",
	"lastName": "Shahrier",
	"email": "fahim@fahim.com",
	"mobile": "0154788576",
	"enabled": 1
}
```

2. Login with existing user.

request body
```json
{
	"username":"fahim",
	"password":"123"
}
```

response header
```
token: Bearer
jwt_token: eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJyb2tvbWFyaV9kZW1vIiwic3ViIjoiZmFoaW0iLCJhdWQiOiJ3ZW
expires_in: 300
```

3. For all other requests

request header
```
token: Bearer
jwt_token: eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJyb2tvbWFyaV9kZW1vIiwic3ViIjoiZmFoaW0iLCJhdWQiOiJ3ZW
<additional info>
```

request body
```
{
  <additional information>
}
