CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(50),
    email VARCHAR(50),
    password VARCHAR(50)
);


CREATE TABLE movie (
    id SERIAL PRIMARY KEY,
    movie_Title VARCHAR(100),
    genre VARCHAR(100),
    duration VARCHAR(100),
    image VARCHAR(255),
    date date 

);

    ALTER TABLE movie ADD COLUMN current VARCHAR(100);

   CREATE TABLE customer (
    id SERIAL PRIMARY KEY,
    type VARCHAR(100),
    total DOUBLE PRECISION,
    date DATE
);

CREATE TABLE customer_info (
    id SERIAL PRIMARY KEY,
    customer_id INT,
    type VARCHAR(100),
    total DOUBLE PRECISION
);





