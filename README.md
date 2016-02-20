Logistics - takes and brings
=======================

 

Sample implementation using algorithm to calculate routes and their
related costs.

 

Project architecture
----------------------

 
The project was implemented using Java language. For management
dependencies, build phases and artifacts packaging,
[Maven](<https://maven.apache.org>) is used.

Drawing to be executed on the service [Amazon
Lambda](<https://aws.amazon.com/lambda/>) and data persistence with
[NoSQL](<https://www.google.com.br/url?sa=t&rct=j&q=&esrc=s&source=web&cd=3&cad=rja&uact=8&ved=0CCUQFjACahUKEwirmP_d4PTHAhWBDpAKHWgPBZ8&url=https%3A%2F%2Fpt.wikipedia.org%2Fwiki%2FNoSQL&usg=AFQjCNFBP3QwsZfT1L6YqvnRdDLtjMFXhw&sig2=6L6i0MWRtLN1Z5WiVTSmNg>)
DynamoDB. The services are exposed via Rest using the service [Gateway
API.](<https://aws.amazon.com/api-gateway/>)

 

Prerequisites
--------------

 

1.  AWS Account.

2.  Maven for dependency management and device packaging.

 

The latest version of the code has been implemented and is
exposed by the endpoint
[https://uawajzheeg.execute-api.us-east-1.amazonaws.com](<https://uawajzheeg.execute-api.us-east-1.amazonaws.com>)

 

Build Phases
--------------

 

To package the project for run in [Amazon
Lambda](<https://aws.amazon.com/lambda/>) use:

 

mvn clean package shade:shade

 

The functions are performed with a timeout of 9 seconds and 512MB of RAM.

 

Creating road network
--------------------

The creation of road network (map) will be made on a request **HTTP**to
endpoint described above using the method **POST** and resource**
/test/mapa**.

 

For map creation method must be invoked with the following requirements
described below.

 

1.  Header = **Content-Type: application/json**

2.  Body Model

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
{
  "$schema": "http://json-schema.org/draft-03/schema#",
  "title": "MapInputModel",
  "type": "object",
  "properties": {
    "name": { "type": "string" },
    "routes": {
      "type": "array",
        "items": {
            "type": "object",
                "properties": {
                    "src": { "type": "string" },
                    "dst": { "type": "string" },
                    "distance": { "type": "integer" }
                }
            }
        }
    }
}
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

 

The response follows the model:

 

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
{
  "$schema" : "http://json-schema.org/draft-01/schema#",
  "title" : "Generic Schema",
  "type" : "object",
  "properties" : {
    "description" : { "type" : "string" }
  }
}
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

 

Finding the shortest path
--------------------------

The search for the shortest route will be with a request **HTTP** to endpoint
described above using the method **GET** and resource** /test/rota**.

 

To search the best route the method should be invoked with the following
requirements described below.

1.  Header = **Content-Type: application/json**

2.  Query Strings

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
{
  "$schema": "http://json-schema.org/draft-02/schema#",
  "title": "FindRouteInputModel",
  "type": "object",
  "properties": {
    "mapName": { "type": "string" },
    "src": { "type": "string" },
    "dst": { "type": "string" },
    "autonomy": { "type": "number" },
    "autonomyPrice": { "type": "number" }
    }
}
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~



An example request the shortest path search would:

 

https://uawajzheeg.execute-api.us-east-1.amazonaws.com/test/rota?mapName=MapaSP&src=A&dst=D&autonomy=25&autonomyPrice=2.5

 

Warning: Do not forget to set the feature [Gateway API] described above with
"Integration Request" below:

 

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
{
  "mapName" : "$input.params('mapName')",
  "src" : "$input.params('src')",
  "dst" : "$input.params('dst')",
  "autonomy" : "$input.params('autonomy')",
  "autonomyPrice" : "$input.params('autonomyPrice')"
}
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

 

Return codes
------------------

The following return codes were mapped to the resources :

 

/test/rota

/test/mapa

 

1.  Bad Request: 400

2.  Internal Error: 500

3.  Validation Error: 406

 

“Integration Response” model:

 

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
#set($inputRoot = $input.path('$'))
{
  "description" : "$inputRoot.errorMessage"
}
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

 
