# ATM v3 Service


## Description



***This is a simple ATM service that allows you to perform the following operations:***

| No |        Status        | Desctiptions |
|:--:|:---------------------:|:------------:|
| 1  |   ✅                  |     done     |
| 2  |   👨‍💻                | in progress  |
| 3  |   ❌                  | not started  |



| No |          Services          | Status |
|:--:|:--------------------------:|:------:|
| 1  |  __Card Holder Service__   |   ✅    |
| 2  |      __ATM service__       |   ✅    |
| 3  |      __Card Service__      |   ✅    |
| 4  |    __Cashing Service__     |   ✅    |
| 5  | __Fill Out  Card Service__ |   ✅    |
| 6  |    __Transfer Service__    |   ✅    |
| 7  |    __Get Card Service__    |   ✅    |

##API REFERENCE

### Card Holder Service

#### Get Card Holder

```http
  POST /api/cardHolder
```
```json

{
    "name": "Asadbek",
    "surname": "Quronboyev",
    "phone_number": "+998999666374",
    "email": "asadbek@gmail.com",
    "address": "Tashkent",
    "passport_series": "AB",
    "passport_number": "0695312",
    "pin_FL": "11112222333344",
    "birth_date": "03.05.1998"
    
}
```

| Parameter         | Type     | Description                               |
|:------------------|:---------|:------------------------------------------|
| `name`            | `string` | **Required**. Card Holder Name            |
| `surname`         | `string` | **Required**. Card Holder Surname         |
| `email`           | `string` | **Required**. Card Holder Email           |
| `phone`           | `string` | **Required**. Card Holder Phone           |
| `address`         | `string` | **Required**. Card Holder Address         |
| `passport series` | `string` | **Required**. Card Holder Passport Series |
| `passport number` | `string` | **Required**. Card Holder Passport Number |
| `pinFl`           | `string` | **Required**. Card Holder PinFl           |
| `birthDate`       | `string` | **Required**. Card Holder Birth Date      |



Response
```json
{
  "id": 1,
  "name": "Asadbek",
  "surname": "Quronboyev",
  "phone_number": "+998999666374",
  "email": "asadbek@gmail.com",
  "address": "Tashkent",
  "passport_series": "AB",
  "passport_number": "0695312",
  "pin_FL": "11112222333344",
  "birth_date": "03.05.1998"
}

```


