Logística - Leva e Traz
=======================

 

Exemplo de implementação utilizando algoritmo para cálculo de rotas e seus
respectivos custos.

 

Arquitetura do Projeto
----------------------

 

O projeto foi implementado utilizando-se de linguagem Java. Para gestão de
dependências, execução das fases de compilação e empacotamento do artefato,
[Maven](<https://maven.apache.org>) é utilizado.

Desenhado para ser executado sobre o serviço [Amazon
Lambda](<https://aws.amazon.com/lambda/>) e com persistência em DataBase
[NoSQL](<https://www.google.com.br/url?sa=t&rct=j&q=&esrc=s&source=web&cd=3&cad=rja&uact=8&ved=0CCUQFjACahUKEwirmP_d4PTHAhWBDpAKHWgPBZ8&url=https%3A%2F%2Fpt.wikipedia.org%2Fwiki%2FNoSQL&usg=AFQjCNFBP3QwsZfT1L6YqvnRdDLtjMFXhw&sig2=6L6i0MWRtLN1Z5WiVTSmNg>)
DynamoDB. Os serviços são expostos via Rest utilizando-se do serviço [Gateway
API.](<https://aws.amazon.com/api-gateway/>)

 

Pré-Requisitos
--------------

 

1.  Conta na plataforma de serviços AWS.

2.  Maven para gestão de dependências e empacotamento do artefato.

 

Obs: Para facilitar os testes a última versão do código já foi implantada e está
exposta pelo endpoint
[https://uawajzheeg.execute-api.us-east-1.amazonaws.com](<https://uawajzheeg.execute-api.us-east-1.amazonaws.com>)

 

Fases de Build
--------------

 

Para empacotar o projeto para distribuição via [Amazon
Lambda](<https://aws.amazon.com/lambda/>) utilize:

 

mvn clean package shade:shade

 

Obs: As funções são executadas com timeout de 9 segundos e 512MB de RAM.

 

Criando malha viária
--------------------

A criação de malha viária (mapa) será feita com uma requisição **HTTP** ao
endpoint acima descrito utilizando-se do método **POST** e recurso**
/test/mapa**.

 

Para criação do mapa o método deverá ser invocado com  seguindo os requisitos
abaixo descritos.

 

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

 

O response segue o modelo:

 

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

 

Buscando a rota mais curta
--------------------------

A busca da rota mais curta será feita com uma requisição **HTTP** ao endpoint
acima descrito utilizando-se do método **GET** e recurso** /test/rota**.

 

Para busca de melhor rota o método deverá ser invocado com  seguindo os
requisitos abaixo descritos.

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



Um exemplo de requisição à busca de rota mais curta seria:

 

https://uawajzheeg.execute-api.us-east-1.amazonaws.com/test/rota?mapName=MapaSP&src=A&dst=D&autonomy=25&autonomyPrice=2.5

 

Atenção: Não esqueça de configurar o recurso  [Gateway API] acima descrito com
“Integration Request” abaixo:

 

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
{
  "mapName" : "$input.params('mapName')",
  "src" : "$input.params('src')",
  "dst" : "$input.params('dst')",
  "autonomy" : "$input.params('autonomy')",
  "autonomyPrice" : "$input.params('autonomyPrice')"
}
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

 

Códigos de Retorno
------------------

Foram mapeados os seguintes códigos de retorno para os recursos :

 

/test/rota

/test/mapa

 

1.  Bad Request: 400

2.  Internal Error: 500

3.  Validation Error: 406

 

Modelo de “Integration Response”:

 

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
#set($inputRoot = $input.path('$'))
{
  "description" : "$inputRoot.errorMessage"
}
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

 
