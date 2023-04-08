DROP SCHEMA IF EXISTS cinema CASCADE;

CREATE SCHEMA cinema;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

DROP TABLE IF EXISTS cinema.cinemas CASCADE;

CREATE TABLE cinema.cinemas
(
    id uuid NOT NULL,
    name character varying COLLATE pg_catalog."default" NOT NULL,
    active boolean NOT NULL,
    CONSTRAINT cinemas_pkey PRIMARY KEY (id)
);

DROP TYPE IF EXISTS approval_status;

CREATE TYPE approval_status AS ENUM ('APPROVED', 'REJECTED');

DROP TABLE IF EXISTS cinema.order_approval CASCADE;

CREATE TABLE cinema.order_approval
(
    id uuid NOT NULL,
    cinema_id uuid NOT NULL,
    order_id uuid NOT NULL,
    status approval_status NOT NULL,
    CONSTRAINT order_approval_pkey PRIMARY KEY (id)
);

DROP TABLE IF EXISTS cinema.movies CASCADE;

CREATE TABLE cinema.movies
(
    id uuid NOT NULL,
    name character varying COLLATE pg_catalog."default" NOT NULL,
    price numeric(10,2) NOT NULL,
    available boolean NOT NULL,
    CONSTRAINT movies_pkey PRIMARY KEY (id)
);

DROP TABLE IF EXISTS cinema.cinema_movies CASCADE;

CREATE TABLE cinema.cinema_movies
(
    id uuid NOT NULL,
    cinema_id uuid NOT NULL,
    movie_id uuid NOT NULL,
    CONSTRAINT cinema_movies_pkey PRIMARY KEY (id)
);


ALTER TABLE cinema.cinema_movies
    ADD CONSTRAINT "FK_cinema_ID" FOREIGN KEY (cinema_id)
    REFERENCES cinema.cinemas (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE RESTRICT
    NOT VALID;

ALTER TABLE cinema.cinema_movies
    ADD CONSTRAINT "FK_movie_id" FOREIGN KEY (movie_id)
    REFERENCES cinema.movies (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE RESTRICT
    NOT VALID;

DROP MATERIALIZED VIEW IF EXISTS cinema.order_cinema_m_view;

CREATE MATERIALIZED VIEW cinema.order_cinema_m_view
TABLESPACE pg_default
AS
 SELECT r.id AS cinema_id,
    r.name AS cinema_name,
    r.active AS cinema_active,
    p.id AS movie_id,
    p.name AS movie_name,
    p.price AS movie_price,
    p.available AS movie_available
   FROM cinema.cinemas r,
    cinema.movies p,
    cinema.cinema_movies rp
  WHERE r.id = rp.cinema_id AND p.id = rp.movie_id
WITH DATA;

refresh materialized VIEW cinema.order_cinema_m_view;

DROP function IF EXISTS cinema.refresh_order_cinema_m_view;

CREATE OR replace function cinema.refresh_order_cinema_m_view()
returns trigger
AS '
BEGIN
    refresh materialized VIEW cinema.order_cinema_m_view;
    return null;
END;
'  LANGUAGE plpgsql;

DROP trigger IF EXISTS refresh_order_cinema_m_view ON cinema.cinema_movies;

CREATE trigger refresh_order_cinema_m_view
after INSERT OR UPDATE OR DELETE OR truncate
ON cinema.cinema_movies FOR each statement
EXECUTE PROCEDURE cinema.refresh_order_cinema_m_view();