INSERT INTO cinema.cinemas(id, name, active)
	VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb45', 'cinema_1', TRUE);
INSERT INTO cinema.cinemas(id, name, active)
	VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb46', 'cinema_2', FALSE);

INSERT INTO cinema.movies(id, name, price, available)
	VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb47', 'movie_1', 25.00, FALSE);
INSERT INTO cinema.movies(id, name, price, available)
	VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb48', 'movie_2', 50.00, TRUE);
INSERT INTO cinema.movies(id, name, price, available)
	VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb49', 'movie_3', 20.00, FALSE);
INSERT INTO cinema.movies(id, name, price, available)
	VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb50', 'movie_4', 40.00, TRUE);

INSERT INTO cinema.cinema_movies(id, cinema_id, movie_id)
	VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb51', 'd215b5f8-0249-4dc5-89a3-51fd148cfb45', 'd215b5f8-0249-4dc5-89a3-51fd148cfb47');
INSERT INTO cinema.cinema_movies(id, cinema_id, movie_id)
	VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb52', 'd215b5f8-0249-4dc5-89a3-51fd148cfb45', 'd215b5f8-0249-4dc5-89a3-51fd148cfb48');
INSERT INTO cinema.cinema_movies(id, cinema_id, movie_id)
	VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb53', 'd215b5f8-0249-4dc5-89a3-51fd148cfb46', 'd215b5f8-0249-4dc5-89a3-51fd148cfb49');
INSERT INTO cinema.cinema_movies(id, cinema_id, movie_id)
	VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb54', 'd215b5f8-0249-4dc5-89a3-51fd148cfb46', 'd215b5f8-0249-4dc5-89a3-51fd148cfb50');
