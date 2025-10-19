# World countries codes application

#### Application provide service to show and store countries codes

### How to use:

#### - Get all available countries:
curl -v http://localhost:8283/api/countries/all

#### - Create new entry with not existed yet code
curl -v POST http://localhost:8283/api/countries/add \
-H "Content-Type: application/json" \
-d '{"fullName": "Example", "code": "EX"}'

#### - Update country full name by code
curl -v -X PATCH http://localhost:8283/api/countries/update_full_name \
-H "Content-Type: application/json" \
-d '{"fullName": "Example_1", "code": "EX"}