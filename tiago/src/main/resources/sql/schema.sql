ALTER DATABASE db_movie_api SET timezone TO 'UTC';

DROP TABLE IF EXISTS tb_movie_subtitles;
DROP TABLE IF EXISTS tb_movie_languages;
DROP TABLE IF EXISTS tb_movie_actors;
DROP TABLE IF EXISTS tb_director_awards;
DROP TABLE IF EXISTS tb_actor_awards;
DROP TABLE IF EXISTS tb_movies;
DROP TABLE IF EXISTS tb_studios;
DROP TABLE IF EXISTS tb_directors;
DROP TABLE IF EXISTS tb_actors;
DROP TABLE IF EXISTS tb_languages;
DROP TABLE IF EXISTS tb_countries;

CREATE TABLE tb_countries (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL CHECK (LENGTH(name) >= 3 AND LENGTH(name) <= 50),
    code VARCHAR(3) NOT NULL UNIQUE CHECK (LENGTH(code) >= 2 AND LENGTH(code) <= 3),
    created TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    modified TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE tb_languages (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL CHECK (LENGTH(name) >= 3 AND LENGTH(name) <= 50),
    created TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    modified TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE tb_actors (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL CHECK (LENGTH(name) >= 3 AND LENGTH(name) <= 100),
    date_of_birth DATE NOT NULL,
    country_id INT NOT NULL,
    created TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    modified TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (country_id) REFERENCES tb_countries(id)
);

CREATE TABLE tb_directors (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL CHECK (LENGTH(name) >= 3 AND LENGTH(name) <= 100),
    date_of_birth DATE NOT NULL,
    country_id INT NOT NULL,
    created TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    modified TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (country_id) REFERENCES tb_countries(id)
);

CREATE TABLE tb_movies (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL CHECK (LENGTH(title) >= 1 AND LENGTH(title) <= 255),
    imdbId VARCHAR(12) NOT NULL CHECK (LENGTH(imdbId) >= 9 AND LENGTH(imdbId) <= 11 AND (imdbId ~ '^tt\\d{7,9}$')),
    release_date TIMESTAMP WITH TIME ZONE,
    duration_minutes INT CHECK (duration_minutes >= 0),
    budget_dollars DECIMAL(15, 2) CHECK (budget_dollars >= 0),
    box_office_dollars DECIMAL(15, 2) DEFAULT 0 CHECK (box_office_dollars >= 0),
    synopsis TEXT NOT NULL CHECK (LENGTH(synopsis) >= 10 AND LENGTH(synopsis) <= 1000),
    genre_id INT,
    director_id INT,
    studio_id INT,
    country_id INT,
    created TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    modified TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (genre_id) REFERENCES tb_genres(id)
    FOREIGN KEY (director_id) REFERENCES tb_directors(id)
    FOREIGN KEY (studio_id) REFERENCES tb_studios(id)
    FOREIGN KEY (country_id) REFERENCES tb_countries(id)
);

CREATE TABLE tb_studios (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL CHECK (LENGTH(name) >= 3 AND LENGTH(name) <= 50),
    country_id INT,
    created TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    modified TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (country_id) REFERENCES tb_countries(id)
);

CREATE TABLE tb_actor_awards (
    id SERIAL PRIMARY KEY,
    actor_id INT NOT NULL,
    award_name VARCHAR(100) NOT NULL CHECK (LENGTH(name) >= 3 AND LENGTH(name) <= 100),
    created TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    modified TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (actor_id) REFERENCES tb_actors(id)
);

CREATE TABLE tb_director_awards (
    id SERIAL PRIMARY KEY,
    director_id INT NOT NULL,
    award_name VARCHAR(100) NOT NULL CHECK (LENGTH(name) >= 3 AND LENGTH(name) <= 100),
    created TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    modified TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (director_id) REFERENCES tb_directors(id)
);

CREATE TABLE tb_movie_actors (
    movie_id INT NOT NULL,
    actor_id INT NOT NULL,
    PRIMARY KEY (movie_id, actor_id),
    FOREIGN KEY (movie_id) REFERENCES tb_movies(id) ON DELETE CASCADE,
    FOREIGN KEY (actor_id) REFERENCES tb_actors(id) ON DELETE CASCADE
);

CREATE TABLE tb_movie_languages (
    movie_id INT NOT NULL,
    language_id INT NOT NULL,
    PRIMARY KEY (movie_id, language_id),
    FOREIGN KEY (movie_id) REFERENCES tb_movies(id) ON DELETE CASCADE,
    FOREIGN KEY (language_id) REFERENCES tb_languages(id) ON DELETE CASCADE
);

CREATE TABLE tb_movie_subtitles (
    movie_id INT NOT NULL,
    language_id INT NOT NULL,
    PRIMARY KEY (movie_id, language_id),
    FOREIGN KEY (movie_id) REFERENCES tb_movies(id) ON DELETE CASCADE,
    FOREIGN KEY (language_id) REFERENCES tb_languages(id) ON DELETE CASCADE
);