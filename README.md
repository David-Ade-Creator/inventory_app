# Rest Api For Inventory Application

This is an application that provides end points to perform the following operations

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.david.Inventory` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## Deploying the application to Replit

Download the zip folder from GitHub and unzip files, open Replit and select Java, drag and drop or upload project folder to your replit workspace.
To run application, open shell on replit and cd to your project and run application

```shell
cd projectname
./mvnw spring-boot:run
```

## Endpoints available in application

Test application from localhost address after running on machine or test application on replit using this link 
[Run from replit]("https://replit.com/@DavidAdeCreator/inventoryapis#Inventory/src/main/java/com/david/Inventory/InventoryApplication.java)


Inventory Entity
1. name (String , required field)
2. quantity (Integer, min = 0 , max = 50)
3. description (String , required field, min = 5, max = 64)
4. type (Enum, required field) options to select from are : WORK_IN_PROCESS,
   FINISHED,
   RAW,
   CONSUMABLE,
   PACKED
5. warehouses

Operation  | Api endpoint                | Request
------------- |-----------------------------| -------------
Create inventory item  | api/inventory               | POST
Get List Of Inventory Items | api/inventory               | GET
Edit Inventory Item | api/inventory/{inventoryId} | PUT
Delete Inventory Item | api/inventory/{inventoryId} | DELETE

### Warehouse operations

Warehouse Entity
1. name (String , required field, min = 2, max = 25)
2. registrationId (String , required field, min = 2, max = 15, unique)
3. location (String , required field, min = 5, max = 20)
4. inventory_collection

Operation  | Api endpoint                                        | Request
------------- |-----------------------------------------------------| -------------
Create Warehouse  | api/warehouse                                       | POST
Get List Of Warehouses | api/warehouse                                       | GET
Edit Warehouse | api/warehouse/{warehouseId}                         | PUT
Add Inventory item to warehouse | api/warehouse/{warehouseId}/inventory/{inventoryId} | PUT
Remove Inventory item from warehouse | api/warehouse/{warehouseId}/inventory/{inventoryId} | PUT
Delete Warehouse | api/warehouse/{warehouseId}                         | DELETE

