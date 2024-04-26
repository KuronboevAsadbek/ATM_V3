# ATM v3 Service


## Description



***This is a simple ATM service that allows you to perform the following operations:***

| No | Status | Desctiptions |
|:--:|:------:|:------------:|
| 1  |   ✅    |     done     |
| 2  | 👨‍💻  | in progress  |
| 3  |   ❌    | not started  |



| No |          Services          | Status |
|:--:|:--------------------------:|:------:|
| 1  |  __Card Holder Service__(https://github.com/KuronboevAsadbek/ATM_V3?tab=readme-ov-file#description)   |   ✅    |
| 2  | __BankNote Type Service__  |   ✅    |
| 3  |      __Card Service__      |   ✅    |
| 4  |    __Cashing Service__     |   ✅    |
| 5  | __Fill Out  Card Service__ |   ✅    |
| 6  |    __Transfer Service__    |   ✅    |
| 7  |    __Get Card Service__    |   ✅    |

##API REFERENCE

### Card Holder Service

#### Create Card Holder

Request
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
  "passport_number": "123456",
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
  "passport_number": "123456",
  "pin_FL": "11112222333344",
  "birth_date": "03.05.1998"
}
```

#### update Card Holder

```http
  PUT /api/cardHolder
```

```Parameters
RequestParam
  id=1
```

Response

```json
{
  "name": "Asadbek",
  "surname": "Quronboyev",
  "phone_number": "+998999666374",
  "email": "asadbek@gmail.com",
  "address": "Tashkent",
  "passport_series": "AB",
  "passport_number": "123456",
  "pin_FL": "11112222333344",
  "birth_date": "03.05.1998"
}
```

| Parameter         | Type     | Description                               | 
|:------------------|:---------|:------------------------------------------|
| `id`              | `int`    | **Required**. Card Holder Id              |
| `name`            | `string` | **Required**. Card Holder Name            |
| `surname`         | `string` | **Required**. Card Holder Surname         |
| `email`           | `string` | **Required**. Card Holder Email           |
| `phone`           | `string` | **Required**. Card Holder Phone           |
| `address`         | `string` | **Required**. Card Holder Address         |
| `passport series` | `string` | **Required**. Card Holder Passport Series |
| `passport number` | `string` | **Required**. Card Holder Passport Number |
| `pinFl`           | `string` | **Required**. Card Holder PinFl           |
| `birthDate`       | `string` | **Required**. Card Holder Birth Date      |



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

### BankNote Type Service

#### Create BankNote Type

Request

```http
  POST /api/banknotetype/create
```

```json
{
  "name": "HUNDRED",
  "nominal": 100,
  "quantity": 100,
  "cashing_type_id": 1,
  "currency_type_id": 2
}
```

| Parameter          | Type     | Description                                  |
|:-------------------|:---------|:---------------------------------------------|
| `name`             | `string` | **Required**. BankNote Type Name             |
| `nominal`          | `int`    | **Required**. BankNote Type Nominal          |
| `quantity`         | `int`    | **Required**. BankNote Type Quantity         |
| `cashing_type_id`  | `Long`   | **Required**. BankNote Type Cashing Type Id  |
| `currency_type_id` | `Long`   | **Required**. BankNote Type Currency Type Id |

Response

```json
{
  "id": 1,
  "name": "HUNDRED",
  "nominal": 100,
  "quantity": 100,
  "cashing_type_id": 1,
  "currency_type_id": 2
}
```

#### Update BankNote Type

Request

```http
  PUT /api/banknotetype/update
```

```Parameters
RequestParam
  id=1
```

```json
{
  "name": "HUNDRED",
  "nominal": 100,
  "quantity": 100,
  "cashing_type_id": 1,
  "currency_type_id": 2
}
```

| Parameter          | Type     | Description                                  |
|:-------------------|:---------|:---------------------------------------------|
| `id`               | `Long`   | **Required**. BankNote Type Id               |
| `name`             | `string` | **Required**. BankNote Type Name             |
| `nominal`          | `int`    | **Required**. BankNote Type Nominal          |
| `quantity`         | `int`    | **Required**. BankNote Type Quantity         |
| `cashing_type_id`  | `Long`   | **Required**. BankNote Type Cashing Type Id  |
| `currency_type_id` | `Long`   | **Required**. BankNote Type Currency Type Id |

Response

```json
{
  "id": 1,
  "name": "HUNDRED",
  "nominal": 100,
  "quantity": 100,
  "cashing_type_id": 1,
  "currency_type_id": 2
}
```

#### Delete BankNote Type

Request

```http
  DELETE /api/banknotetype/delete
```

```Parameters
  id=1
```

| Parameter | Type   | Description               |
|:----------|:-------|:--------------------------|
| `id`      | `Long` | **Required**. BankNote Id |

Response

```json
{
  "message": "BankNote Type deleted successfully!"
}
```

### Card Service

#### Create Card

Request

```http
  POST /api/card/create
```

```json
{
  "card_pin": "1111",
  "card_holder_id": 2,
  "card_type_id": 1
}
```

| Parameter        | Type     | Description                  |
|:-----------------|:---------|:-----------------------------|
| `card_pin`       | `String` | **Required**. Card Pin       |
| `card_holder_id` | `Long`   | **Required**. Card Holder Id |
| `card_type_id`   | `Long`   | **Required**. Card Type Id   |

Response

```json
{
  "id": 7,
  "cardBalance": "0",
  "cardNumber": "9860160191819353",
  "cardExpireDate": "2029-04-26",
  "cardCVC": null,
  "cardPin": "1111",
  "cardTypeName": "HUMO",
  "cardStatus": true,
  "cardHolderName": "Asadbek Quronboyev",
  "cardHolderAddress": "Tashkent",
  "cardHolderPhoneNumber": "+998999666374"
}
```

#### Update Card

Request

```http
  PUT /api/card/update
```

```Parameters
RequestParam
  id=7
```

```json
{
  "card_pin": "1111",
  "card_holder_id": 2,
  "card_type_id": 1
}
```

| Parameter        | Type     | Description                  |
|:-----------------|:---------|:-----------------------------|
| `id`             | `Long`   | **Required**. Card Id        |
| `card_pin`       | `String` | **Required**. Card Pin       |
| `card_holder_id` | `Long`   | **Required**. Card Holder Id |
| `card_type_id`   | `Long`   | **Required**. Card Type Id   |

Response

```json
{
  "id": 7,
  "cardBalance": "0",
  "cardNumber": "9860160191819353",
  "cardExpireDate": "2029-04-26",
  "cardCVC": null,
  "cardPin": "1111",
  "cardTypeName": "HUMO",
  "cardStatus": true,
  "cardHolderName": "Asadbek Quronboyev",
  "cardHolderAddress": "Tashkent",
  "cardHolderPhoneNumber": "+998999666374"
}
```

#### Block Card

Request

```http
  POST /api/card/status
```

```TEXT
STATUS: BLOCK, ACTIVE
```

```json
{
  "id": 7,
  "cardStatus": "BLOCK"
}
```

| Parameter | Type     | Description               |
|:----------|:---------|:--------------------------|
| `id`      | `Long`   | **Required**. Card Id     |
| `status`  | `String` | **Required**. Card Status |

Response

```json
{
  "message": "Card blocked successfully!"
}
```

### Cashing Service

#### Cash In

Request

```http
  POST /api/cashing/in
```

```text
cheque_request: true, false
```

```json
{
  "card_number": 9860160116553910,
  "card_pin": 2222,
  "amount": 10000,
  "cashing_banknote_type": "SMALL",
  "cheque_request": true
}
```

| Parameter               | Type      | Description                         |
|:------------------------|:----------|:------------------------------------|
| `card_number`           | `String`  | **Required**. Card Number           |
| `card_pin`              | `String`  | **Required**. Card Pin              |
| `amount`                | `String`  | **Required**. Amount                |
| `cashing_banknote_type` | `String`  | **Required**. Cashing Banknote Type |
| `cheque_request`        | `Boolean` | **Required**. Cheque Request        |

Response

```text
if cheque is true
```

```json
{
  "message": "Card cashed",
  "commission": "100.0",
  "balance": "9982332950,00",
  "cashedAmount": "0.0"
}
```

```text
if cheque is false
```

```json
{
  "message": "Card cashed"
}
```

### Fill Out Card Service

Request

```http
  POST /api/cashing/out
```

```text
cheque_request: true, false
```

```json
{
  "card_number": 9860160116553910,
  "card_pin": 2222,
  "amount": 10000,
  "cashing_banknote_type": "SMALL",
  "cheque_request": true
}
```

| Parameter               | Type      | Description                         |
|:------------------------|:----------|:------------------------------------|
| `card_number`           | `String`  | **Required**. Card Number           |
| `card_pin`              | `String`  | **Required**. Card Pin              |
| `amount`                | `String`  | **Required**. Amount                |
| `cashing_banknote_type` | `String`  | **Required**. Cashing Banknote Type |
| `cheque_request`        | `Boolean` | **Required**. Cheque Request        |

Response

```text
if cheque is true
```

```json
{
  "message": "Card balance filled out",
  "commission": "100.0",
  "balance": "9982332950,00",
  "cashedAmount": "0.0"
}
```

```text
if cheque is false
```

```json
{
  "message": "Card balance filled out"
}
```

### Transfer Service

#### Transfer

Request

```http
  POST /api/transfer/card
```

```json
{
  "from_card_number": "9860160145764843",
  "to_card_number": "9860160116553910",
  "amount": "15000",
  "pin": "2222"
}
```

| Parameter          | Type     | Description                    |
|:-------------------|:---------|:-------------------------------|
| `from_card_number` | `String` | **Required**. From Card Number |
| `to_card_number`   | `String` | **Required**. To Card Number   |
| `amount`           | `String` | **Required**. Amount           |
| `pin`              | `String` | **Required**. Pin              |

Response

```json
{
  "message": "Transfer completed successfully",
  "commission": "100.0",
  "balance": "9982332950,00",
  "cashedAmount": "0.0"
}
```

### Get Card Service

#### Get All Card

Request

```http
  GET /api/card/all
```

Response

```json
[
  {
    "id": 1,
    "cardBalance": "0",
    "cardNumber": "9860160191819353",
    "cardExpireDate": "2029-04-26",
    "cardCVC": null,
    "cardPin": "1111",
    "cardTypeName": "HUMO",
    "cardStatus": true,
    "cardHolderName": "Asadbek Quronboyev",
    "cardHolderAddress": "Tashkent",
    "cardHolderPhoneNumber": "+998999666374"
  },
  {
    "id": 2,
    "cardBalance": "0",
    "cardNumber": "9860160116553910",
    "cardExpireDate": "2029-04-26",
    "cardCVC": null,
    "cardPin": "2222",
    "cardTypeName": "HUMO",
    "cardStatus": true,
    "cardHolderName": "Asadbek Quronboyev",
    "cardHolderAddress": "Tashkent",
    "cardHolderPhoneNumber": "+998999666374"
  }
]
```

#### Get Card By Id

Request

```http
  GET /api/card/{id}
```

```Parameters
  id=1
```

Response

```json
{
  "id": 1,
  "cardBalance": "0",
  "cardNumber": "9860160191819353",
  "cardExpireDate": "2029-04-26",
  "cardCVC": null,
  "cardPin": "1111",
  "cardTypeName": "HUMO",
  "cardStatus": true,
  "cardHolderName": "Asadbek Quronboyev",
  "cardHolderAddress": "Tashkent",
  "cardHolderPhoneNumber": "+998999666374"
}
```

#### Get All Card By CardHolder PinFL

Request

```http
  GET /api/get/all/pinfl
```

```Parameters
RequestParam
  pinfl=11112222333344
```

Response

```json
[
  {
    "id": 1,
    "cardBalance": "0",
    "cardNumber": "9860160191819353",
    "cardExpireDate": "2029-04-26",
    "cardCVC": null,
    "cardPin": "1111",
    "cardTypeName": "HUMO",
    "cardStatus": true,
    "cardHolderName": "Asadbek Quronboyev",
    "cardHolderAddress": "Tashkent",
    "cardHolderPhoneNumber": "+998999666374"
  },
  {
    "id": 2,
    "cardBalance": "0",
    "cardNumber": "9860160116553910",
    "cardExpireDate": "2029-04-26",
    "cardCVC": null,
    "cardPin": "2222",
    "cardTypeName": "HUMO",
    "cardStatus": true,
    "cardHolderName": "Asadbek Quronboyev",
    "cardHolderAddress": "Tashkent",
    "cardHolderPhoneNumber": "+998999666374"
  }
]
``` 











