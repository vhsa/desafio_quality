Observação:
--

<p>Todas as requisições abaixo utilizam o mesmo <i>request body</i></p>

````javascript
{
    "prop_name" : "Apto - Asa sul",
    "prop_district" : "Brasília",
    "rooms" : [
        {"room_name": "Cozinha", "room_width" : 4.5, "room_length" : 2.4},
        {"room_name": "Quarto", "room_width" : 7.0, "room_length" : 4.0},
        {"room_name": "Sala", "room_width" : 7.5, "room_length" : 2.2}
    ]
}
````

<p> Os valores acima podem ser alterados, porém os atributos do JSON precisam estar estritamente com essa nomenclatura</p>

Os distritos dísponíveis são:
-- 
- Brasília
- São Paulo
- Rio de Janeiro
- Belo Horizonte

Endpoints
-- 
US-0001 - [POST] <br>
Calcule o total de metros quadrados de uma propriedade

````shell
/calculate-square-meter
````

US-0002 - [POST] <br>
Indique o valor de uma propriedade com base em seus cômodos e medidas. Lembre-se que os preços por metro quadrado são determinados de acordo com a vizinhança.

````shell
/calculate-prop-price
````

US-0003 - [POST] <br>
Determine qual é o maior cômodo.

````shell
/biggest-room
````

US-0004 - [POST] <br>
Determinar a quantidade de metros quadrados que tem cada cômodo de uma propriedade.

````shell
/room-detail
````




