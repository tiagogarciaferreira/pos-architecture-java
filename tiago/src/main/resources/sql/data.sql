INSERT INTO tb_genres (name, version) VALUES
('Ação', 1),
('Comédia', 1),
('Drama', 1),
('Suspense', 1),
('Romance', 1);

INSERT INTO tb_countries (name, code, version) VALUES
('Brasil', 'BR', 1),
('Estados Unidos', 'US', 1),
('França', 'FR', 1),
('Japão', 'JP', 1),
('Canadá', 'CA', 1);

INSERT INTO tb_languages (name, version) VALUES
('Português', 1),
('Inglês', 1),
('Francês', 1),
('Japonês', 1),
('Espanhol', 1);

INSERT INTO tb_actors (name, date_of_birth, country_id, version) VALUES
('Carlos Silva', '1980-07-15', 1, 1),
('Ana Souza', '1990-03-22', 1, 1),
('John Doe', '1975-05-10', 2, 1),
('Marie Dupont', '1985-11-30', 3, 1),
('Akira Sato', '1992-01-25', 4, 1);

INSERT INTO tb_directors (name, date_of_birth, country_id, version) VALUES
('Pedro Martins', '1960-12-05', 1, 1),
('Julia Ferreira', '1972-08-17', 1, 1),
('James Johnson', '1955-04-04', 2, 1),
('Chantal Moreau', '1980-09-13', 3, 1),
('Hiroshi Nakamura', '1978-03-03', 4, 1);

INSERT INTO tb_studios (name, country_id, version) VALUES
('Estúdios Brasil', 1, 1),
('Hollywood Productions', 2, 1),
('Cinéma Français', 3, 1),
('Tokyo Studios', 4, 1),
('Maple Films', 5, 1);

INSERT INTO tb_actor_awards (actor_id, award_name) VALUES
(1, 'Melhor Ator Brasileiro'),
(2, 'Melhor Atriz Revelação'),
(3, 'Oscar de Melhor Ator'),
(4, 'César de Melhor Atriz'),
(5, 'Prêmio Japonês de Melhor Ator');

INSERT INTO tb_director_awards (director_id, award_name) VALUES
(1, 'Melhor Diretor Brasileiro'),
(2, 'Direção Inovadora'),
(3, 'Oscar de Melhor Diretor'),
(4, 'Prêmio César de Direção'),
(5, 'Prêmio Japonês de Direção');

INSERT INTO tb_movies (title, imdb_id, release_date, duration_minutes, budget_dollars, box_office_dollars, synopsis, genre_id, director_id, studio_id, country_id, version) VALUES
('Aventura nas Montanhas', 'tt1234567', '2022-06-15', 120, 5000000, 10000000, 'Um grupo de amigos enfrenta desafios nas montanhas.', 1, 1, 1, 1, 1),
('Comédia em Família', 'tt2345678', '2021-03-12', 95, 3000000, 8000000, 'Uma família divertida enfrenta situações inusitadas.', 2, 2, 2, 2, 1),
('Drama Urbano', 'tt3456789', '2020-09-25', 110, 4000000, 9000000, 'História de vida de pessoas comuns na cidade grande.', 3, 3, 3, 3, 1),
('Suspense no Japão', 'tt4567890', '2019-12-10', 105, 4500000, 8500000, 'Mistério em uma pequena vila japonesa.', 4, 4, 4, 4, 1),
('Romance Canadense', 'tt5678901', '2023-02-14', 115, 2000000, 5000000, 'Um romance inesperado no inverno canadense.', 5, 5, 5, 5, 1);

INSERT INTO tb_movie_actors (movie_id, actor_id) VALUES
(1, 1),
(1, 2),
(2, 3),
(2, 4),
(3, 1),
(3, 5),
(4, 2),
(4, 3),
(5, 4),
(5, 5);

INSERT INTO tb_movie_languages (movie_id, language_id) VALUES
(1, 1),
(1, 2),
(2, 2),
(2, 3),
(3, 1),
(3, 4),
(4, 4),
(4, 5),
(5, 5),
(5, 1);

INSERT INTO tb_movie_subtitles (movie_id, language_id) VALUES
(1, 2),
(1, 3),
(2, 1),
(2, 4),
(3, 5),
(3, 2),
(4, 3),
(4, 1),
(5, 4),
(5, 2);
