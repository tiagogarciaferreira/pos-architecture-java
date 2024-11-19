ALTER DATABASE db_movie_api SET timezone TO 'UTC';

DROP TABLE IF EXISTS tb_movie_genres;
DROP TABLE IF EXISTS tb_user_roles;
DROP TABLE IF EXISTS tb_movie_subtitles;
DROP TABLE IF EXISTS tb_movie_languages;
DROP TABLE IF EXISTS tb_movie_actors;
DROP TABLE IF EXISTS tb_director_awards;
DROP TABLE IF EXISTS tb_actor_awards;
DROP TABLE IF EXISTS tb_actor_roles;
DROP TABLE IF EXISTS tb_movies;
DROP TABLE IF EXISTS tb_studios;
DROP TABLE IF EXISTS tb_genres;
DROP TABLE IF EXISTS tb_directors;
DROP TABLE IF EXISTS tb_actors;
DROP TABLE IF EXISTS tb_languages;
DROP TABLE IF EXISTS tb_users;
DROP TABLE IF EXISTS tb_countries;

CREATE TABLE tb_genres (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE CHECK (LENGTH(name) >= 4 AND LENGTH(name) <= 50),
    version INT NOT NULL CHECK (version >= 1),
    created TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    modified TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE tb_countries (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE CHECK (LENGTH(name) >= 3 AND LENGTH(name) <= 50),
    code VARCHAR(3) NOT NULL UNIQUE CHECK (LENGTH(code) = 3),
    version INT NOT NULL CHECK (version >= 1),
    created TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    modified TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE tb_languages (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE CHECK (LENGTH(name) >= 3 AND LENGTH(name) <= 50),
    version INT NOT NULL CHECK (version >= 1),
    created TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    modified TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE tb_actors (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE CHECK (LENGTH(name) >= 3 AND LENGTH(name) <= 100),
    date_of_birth DATE NOT NULL,
    country_id INT NOT NULL CHECK (country_id >= 1),
    version INT NOT NULL CHECK (version >= 1),
    created TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    modified TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (country_id) REFERENCES tb_countries(id)
);

CREATE TABLE tb_directors (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL CHECK (LENGTH(name) >= 3 AND LENGTH(name) <= 100),
    date_of_birth DATE NOT NULL,
    country_id INT NOT NULL CHECK (country_id >= 1),
    version INT NOT NULL CHECK (version >= 1),
    created TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    modified TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (country_id) REFERENCES tb_countries(id)
);

CREATE TABLE tb_studios (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL CHECK (LENGTH(name) >= 3 AND LENGTH(name) <= 50),
    country_id INT NOT NULL CHECK (country_id >= 1),
    version INT NOT NULL CHECK (version >= 1),
    created TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    modified TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (country_id) REFERENCES tb_countries(id)
);

CREATE TABLE tb_actor_awards (
    id SERIAL PRIMARY KEY,
    actor_id INT NOT NULL CHECK (actor_id >= 1),
    award_name VARCHAR(200) NOT NULL CHECK (LENGTH(award_name) >= 3 AND LENGTH(award_name) <= 200),
    created TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    modified TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (actor_id) REFERENCES tb_actors(id) ON DELETE CASCADE
);

CREATE TABLE tb_actor_roles (
    id SERIAL PRIMARY KEY,
    actor_id INT NOT NULL CHECK (actor_id >= 1),
    role_name VARCHAR(200) NOT NULL CHECK (LENGTH(role_name) >= 1 AND LENGTH(role_name) <= 200),
    created TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    modified TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (actor_id) REFERENCES tb_actors(id) ON DELETE CASCADE
);

CREATE TABLE tb_director_awards (
    id SERIAL PRIMARY KEY,
    director_id INT NOT NULL CHECK (director_id >= 1),
    award_name VARCHAR(200) NOT NULL CHECK (LENGTH(award_name) >= 3 AND LENGTH(award_name) <= 200),
    created TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    modified TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (director_id) REFERENCES tb_directors(id) ON DELETE CASCADE
);

CREATE TABLE tb_movies (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL UNIQUE CHECK (LENGTH(title) >= 1 AND LENGTH(title) <= 255),
    imdb_id VARCHAR(12) NOT NULL UNIQUE CHECK (LENGTH(imdb_id) >= 9 AND LENGTH(imdb_id) <= 11),
    imdb_rating REAL DEFAULT 0 CHECK (imdb_rating >= 0),
    poster VARCHAR(255),
    release_date DATE NOT NULL,
    duration_minutes INT CHECK (duration_minutes >= 0),
    budget_dollars REAL CHECK (budget_dollars >= 0),
    box_office_dollars DOUBLE PRECISION DEFAULT 0 CHECK (box_office_dollars >= 0),
    synopsis TEXT NOT NULL CHECK (LENGTH(synopsis) >= 10 AND LENGTH(synopsis) <= 1000),
    director_id INT NOT NULL CHECK (director_id >= 1),
    studio_id INT NOT NULL CHECK (studio_id >= 1),
    country_id INT NOT NULL CHECK (country_id >= 1),
    version INT NOT NULL CHECK (version >= 1),
    created TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    modified TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (director_id) REFERENCES tb_directors(id) ON DELETE RESTRICT,
    FOREIGN KEY (studio_id) REFERENCES tb_studios(id) ON DELETE RESTRICT,
    FOREIGN KEY (country_id) REFERENCES tb_countries(id) ON DELETE RESTRICT
);

CREATE TABLE tb_movie_actors (
    movie_id INT NOT NULL CHECK (movie_id >= 1),
    actor_id INT NOT NULL CHECK (actor_id >= 1),
    PRIMARY KEY (movie_id, actor_id),
    FOREIGN KEY (movie_id) REFERENCES tb_movies(id) ON DELETE CASCADE,
    FOREIGN KEY (actor_id) REFERENCES tb_actors(id) ON DELETE CASCADE
);

CREATE TABLE tb_movie_languages (
    movie_id INT NOT NULL CHECK (movie_id >= 1),
    language_id INT NOT NULL CHECK (language_id >= 1),
    PRIMARY KEY (movie_id, language_id),
    FOREIGN KEY (movie_id) REFERENCES tb_movies(id) ON DELETE CASCADE,
    FOREIGN KEY (language_id) REFERENCES tb_languages(id) ON DELETE CASCADE
);

CREATE TABLE tb_movie_subtitles (
    movie_id INT NOT NULL CHECK (movie_id >= 1),
    language_id INT NOT NULL CHECK (language_id >= 1),
    PRIMARY KEY (movie_id, language_id),
    FOREIGN KEY (movie_id) REFERENCES tb_movies(id) ON DELETE CASCADE,
    FOREIGN KEY (language_id) REFERENCES tb_languages(id) ON DELETE CASCADE
);

CREATE TABLE tb_movie_genres (
    movie_id INT NOT NULL CHECK (movie_id >= 1),
    genre_id INT NOT NULL CHECK (genre_id >= 1),
    PRIMARY KEY (movie_id, genre_id),
    FOREIGN KEY (movie_id) REFERENCES tb_movies(id) ON DELETE CASCADE,
    FOREIGN KEY (genre_id) REFERENCES tb_genres(id) ON DELETE CASCADE
);

CREATE TABLE tb_users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL CHECK (LENGTH(username) >= 3 AND LENGTH(username) <= 100),
    username VARCHAR(100) NOT NULL UNIQUE CHECK (LENGTH(username) >= 3 AND LENGTH(username) <= 50),
    password VARCHAR(100) NOT NULL CHECK (LENGTH(password) >= 20 AND LENGTH(password) <= 100),
    active BOOLEAN NOT NULL,
    date_of_birth DATE NOT NULL,
    country_id INT NOT NULL CHECK (country_id >= 1),
    version INT NOT NULL CHECK (version >= 1),
    created TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    modified TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (country_id) REFERENCES tb_countries(id)
);

CREATE TABLE tb_user_roles (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL CHECK (user_id >= 1),
    role_name VARCHAR(50) NOT NULL CHECK (LENGTH(role_name) >= 4 AND LENGTH(role_name) <= 50),
    created TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    modified TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES tb_users(id) ON DELETE CASCADE
);