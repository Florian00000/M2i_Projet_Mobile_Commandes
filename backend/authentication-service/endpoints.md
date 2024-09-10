path `/api/auth/register`
body:
```json
{
  "firstname": "Georges",
  "lastname": "Abitbol",
  "email": "gabitbol@cowboy.com",
  "password": "123456",
  "roles": [
    {"role":  "ROLE"}
  ]
}
```
---
path `/api/auth/login`
body:
```json
{
  "email": "gabitbol@cowboy.com",
  "password": "123456"
}
```
---
path `/api/validate`
headers:
```json
{
  "Authorization": "Bearer ton_plus_beau_token"
}
```