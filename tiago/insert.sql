-- tb_countries
INSERT INTO tb_countries (name, code, created, modified) VALUES
('United States', 'US', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('United Kingdom', 'GB', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Brazil', 'BR', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('France', 'FR', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Germany', 'DE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Japan', 'JP', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Canada', 'CA', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Italy', 'IT', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Australia', 'AU', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('India', 'IN', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- tb_studios
INSERT INTO tb_studios (name, number_of_movies_produced, created, modified) VALUES
('Warner Bros', 200, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Universal Pictures', 180, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Paramount Pictures', 250, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('20th Century Fox', 150, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Sony Pictures', 120, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Walt Disney Studios', 300, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('MGM', 100, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Lionsgate', 80, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('DreamWorks', 90, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Columbia Pictures', 110, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- tb_actors
INSERT INTO tb_actors (name, date_of_birth, nationality_id, number_of_awards, created, modified) VALUES
('Leonardo DiCaprio', '1974-11-11', 1, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Emma Stone', '1988-11-06', 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Denzel Washington', '1954-12-28', 1, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Tom Hardy', '1977-09-15', 2, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Marion Cotillard', '1975-09-30', 4, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Shah Rukh Khan', '1965-11-02', 10, 8, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Meryl Streep', '1949-06-22', 1, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Chris Hemsworth', '1983-08-11', 9, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Scarlett Johansson', '1984-11-22', 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Penélope Cruz', '1974-04-28', 5, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- tb_directors
INSERT INTO tb_directors (name, date_of_birth, nationality_id, years_active, directorial_style, number_of_films_directed, biography, created, modified) VALUES
('Steven Spielberg', '1946-12-18', 1, 55, 'Adventure, Drama', 35, 'Prolific American filmmaker known for Jaws and Jurassic Park.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Christopher Nolan', '1970-07-30', 2, 25, 'Sci-fi, Thriller', 11, 'British-American director known for Inception and Interstellar.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Quentin Tarantino', '1963-03-27', 1, 30, 'Crime, Non-linear', 10, 'Director known for Pulp Fiction and Reservoir Dogs.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Martin Scorsese', '1942-11-17', 1, 55, 'Crime, Drama', 25, 'Renowned for Goodfellas and Taxi Driver.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('James Cameron', '1954-08-16', 1, 45, 'Sci-fi, Action', 15, 'Famous for Titanic and Avatar.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Kathryn Bigelow', '1951-11-27', 1, 40, 'Thriller, War', 10, 'First woman to win Academy Award for Best Director.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Wes Anderson', '1969-05-01', 1, 30, 'Comedy, Drama', 11, 'Director known for stylized films like The Grand Budapest Hotel.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Sofia Coppola', '1971-05-14', 1, 20, 'Drama, Indie', 8, 'Director of Lost in Translation and The Virgin Suicides.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Alfonso Cuarón', '1961-11-28', 5, 25, 'Drama, Sci-fi', 9, 'Mexican filmmaker known for Gravity and Roma.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Ridley Scott', '1937-11-30', 1, 50, 'Sci-fi, Historical', 30, 'Known for Alien and Gladiator.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- tb_movies
INSERT INTO tb_movies (title, release_date, duration_in_minutes, budget_in_dollars, box_office_in_dollars, synopsis, has_been_released, studio_id, created, modified) VALUES
('Inception', '2010-07-16', 148, 160000000, 829895144, 'A thief who steals corporate secrets through dream-sharing.', true, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Avatar', '2009-12-18', 162, 237000000, 2790439000, 'A paraplegic Marine on Pandora.', true, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Titanic', '1997-12-19', 195, 200000000, 2187463944, 'A romance unfolds during the ill-fated voyage.', true, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('The Dark Knight', '2008-07-18', 152, 185000000, 1004558444, 'Batman battles the Joker.', true, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('The Grand Budapest Hotel', '2014-03-28', 99, 25000000, 173082417, 'A story about a concierge and his protege.', true, 8, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Interstellar', '2014-11-07', 169, 165000000, 677471339, 'A team of explorers travel through a wormhole.', true, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Jaws', '1975-06-20', 124, 9000000, 470700000, 'A shark terrorizes a New England beach town.', true, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('The Godfather', '1972-03-24', 175, 6000000, 246120986, 'The story of a mafia family.', true, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Gladiator', '2000-05-05', 155, 103000000, 460583960, 'A betrayed Roman general seeks vengeance.', true, 10, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Gravity', '2013-10-04', 91, 100000000, 723192705, 'Two astronauts struggle to survive in space.', true, 9, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- tb_characters
INSERT INTO tb_characters (name, movie_id, actor_id, created, modified) VALUES
('Dom Cobb', 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Jake Sully', 2, 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Jack Dawson', 3, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Bruce Wayne / Batman', 4, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('M. Gustave', 5, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Cooper', 6, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Chief Brody', 7, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Vito Corleone', 8, 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Maximus', 9, 7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Dr. Ryan Stone', 10, 8, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- tb_actor_awards
INSERT INTO tb_actor_awards (actor_id, award_name, created, modified) VALUES
(1, 'Academy Award for Best Actor', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'Academy Award for Best Actress', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 'Academy Award for Best Actor', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 'Golden Globe for Best Actor', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(5, 'Academy Award for Best Supporting Actress', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(6, 'Filmfare Award for Best Actor', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(7, 'Golden Globe for Best Actress', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(8, 'Academy Award for Best Supporting Actress', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(9, 'BAFTA Award for Best Actress', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(10, 'César Award for Best Actress', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- tb_director_awards
INSERT INTO tb_director_awards (director_id, award_name, created, modified) VALUES
(1, 'Academy Award for Best Director', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'BAFTA Award for Best Director', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 'Academy Award for Best Director', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 'Golden Globe for Best Director', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(5, 'César Award for Best Director', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(6, 'Golden Globe for Best Director', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(7, 'Cannes Film Festival Award for Best Director', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(8, 'BAFTA Award for Best Director', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(9, 'Academy Award for Best Director', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(10, 'BAFTA Award for Best Director', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
