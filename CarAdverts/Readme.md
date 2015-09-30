### CarAdverts 

The service allows users to place new car adverts and view, modify and delete existing car adverts.

Car adverts have the following fields:
* **id** (_required_): **number**
* **title** (_required_): **string**, e.g. _"Audi A4 Avant"_;
* **fuel** (_required_): Gasoline or Diesel
* **price** (_required_): **number**;
* **isNew** (_required_): **boolean**, indicates if car is new or used;
* **mileage** (_only for used cars_): **number**;
* **firstRegistration** (_only for used cars_): **date** without time.

The service has the following functionality, realized as a RESTful service using HTTP verbs and JSON format:
* **GET http:/<your domain>/caradverts**: returna a list of all car adverts. Use optiponal parameter **sortedBy** to specify according to which field the list should be sorted (e.g. _GET http:/<your domain>/caradverts?sortedBy=price_). Default sorting is by **id**
* **GET http:/<your domain>/caradverts/id**: have functionality to return data for single car advert by id (e.g. _GET http:/<your domain>/caradverts/1354_);
* **POST http:/<your domain>/caradverts**: add car advert (e.g. _POST http:/<your domain>/caradverts {"title":"VW", "id":1, "fuel":"Diesel", "price":10000, "isNew":false, "mileage":1234567, "firstRegistration":"2015-04-15"}_);
* **PUT http:/<your domain>/caradverts**: modify car advert by id;
* **DELETE http:/<your domain>/caradverts/id**: delete car advert by id;
