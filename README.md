# Inventory Management

## Tech Stack
```
1. Java 8
2. Springboot
3. Amazon RDS
```

## System Capabilities
```
1. create/update/get orders.
2. create/update/get products.
3. create customers.
4. list inventory.
```

### List of order API -

-:get paginated all orders :-

```curl http://localhost:9090/med-api/order/all-orders?customerId=1&page=0&size=10 
```

-: get order details by id :-
```
curl http://localhost:9090/med-api/order/1
```

-: place single order :-
```
curl -d "item=1&quantity=20&email:s@gmail.com" -X POST http://localhost:9090/med-api/order/place-order
```

-: place bulk order :-
```
curl -d '[
        {
                "quantity": 1,
                "email": "s@gmail.com",
                "product_id": 1
        },
        {
                "quantity": 2,
                "email": "b@gmail.com",
                "product_id": 1
        }
]' -H "Content-Type: application/json" POST http://localhost:9090/med-api/order/bulk-order
```

### List of product API -

-: get paginated all products :-
```
curl http://localhost:9090/med-api/product/all-products/?page=0&size=10
```

-:create product:-
```
curl -d '{
        "name": "saridon",
        "description": "saridon for headache",
        "quantity": 10,
        "actual_price": 100,
        "offered_price": 80
}' -H "Content-Type: application/json" -X POST http://localhost:9090/med-api/product/create
```

-: update product :-
```
curl -d '{
        "id" : 1,
        "name": "med2",
        "description": "med2 description",
        "quantity": 20,
        "actual_price": 200,
        "offered_price": 170
}' -H "Content-Type: application/json" -X POST http://localhost:9090/med-api/product/update
```

### List of customer API -

-: create customer :-
```
curl -d '{
        "email": "e@gmail.com",
        "phone": "1234567890",
        "password": "sssss",
        "name": "sssssssssss"
}' -H "Content-Type: application/json" -X POST http://localhost:9090/med-api/customer/create
```

### List of inventory API -

-: get paginated  all inventory :-
```
curl http://localhost:9090/med-api/inventory/all-inventory?customerId=1&page=0&size=3
```

-: get inventory details by id :-
```
curl http://localhost:9090/med-api/inventory/
 ```

