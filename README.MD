# _SpecOps API_

#### _API for Special Operations, August 25, 2017_

#### By _**Ryan Nunez**_

## Description

This program can add, read, delete and retrieve operation details from the database. It can also add locations, terrain, missions, conditions, sites and maps. The program is also capable in receiving raters after action reviews for later risk management and mitigation.

## Postman
* _Adding Location Details_
![image of postman1](https://github.com/RxNunez/SpecOps/blob/master/src/main/resources/public/img/Postman1.jpg?raw=true "Adding Location Details")
* _Retrieving multiple location details_
![image of postman2](https://github.com/RxNunez/SpecOps/blob/master/src/main/resources/public/img/postman2.png?raw=true "Retrieving multiple location details")

## Development Specifications

| Behavior      | Input | Output |
| ------------- | ------------- | ------------- |
| API will add location details| name: "Korea", terrain: "Jungle", condition: "Hostile", mission: "Guard", site: "DMZ", map: "grid.jpg" | "name": "Korea", "terrain": "Jungle", "condition": "Hostile", "mission": "Guard", "site": "DMZ", "map": "grid.jpg", "id": 1 |
| API will display list of locations added  | "Korea" ..... "Guam" ....  | "Korea" ..... "Guam" ....  |
| API will accept operations name | Renegade | operationName: Renegade, operationId: 1 |
| API will accept assessment from rater | Casualties taken | "Casualties taken", assessmentId:1, operationId:1|
| API will delete location details | delete locationId: 1 | no value for locationId:1 |
| API will delete operations name | delete operationId: 1 | no value for operationId:1  |

## Setup/Installation Requirements

* _Clone this repository_
* _For a mac, run the following command in your terminal:
git clone (https://github.com/RxNunez/SpecOps)_
* _Open the folder and click the file in your intelliJ application._

## Support and contact details

_If you have any questions or suggestions. Please free to contact us._
_Ryan Nunez. Email: nunez.ryan@gmail.com_

## Technologies Used

_IntelliJ_
_Java_
_SQL_
_Spark_
_JSON-GSON_

### License
This software is licensed under MIT license.
