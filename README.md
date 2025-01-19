POST	/api/auth/signup	signup new account

POST	/api/auth/signin	login an account

GET	/api/test/all	retrieve public content

GET	/api/test/user	access User’s content

/refreshtoken запрос, вернется ответ с новым accessToken.


POST /transactional произвести транзакцию

GET /theMostPopularAuthor  “самого популярного” автора за определенный диапазон дат (с…по)

GET /theMostReadingClient" самого читающего клиента (кто больше взял книг)

POST /listOfAllReadersOfUndeliveredBooks" список всех читателей отсортированных по убыванию количества не сданных книг


Бд: PostgreSQL
Java Spring 3.0
