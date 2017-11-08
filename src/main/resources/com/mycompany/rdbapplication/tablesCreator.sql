CREATE TABLE publishers(id SERIAL PRIMARY KEY, name VARCHAR(40) NOT NULL);
CREATE TABLE books(id SERIAL PRIMARY KEY , title VARCHAR(40) NOT NULL, publication DATE NOT NULL);
CREATE TABLE authors(id SERIAL PRIMARY KEY , name VARCHAR(50) NOT NULL, day_of_birthday DATE NOT NULL, day_of_death DATE, sex VARCHAR(6) NOT NULL);

ALTER TABLE authors
   ADD CONSTRAINT check_sex
   CHECK (sex IN ('male', 'female', 'MALE', 'FEMALE') );

CREATE TABLE publishers_books(publisher_id INT REFERENCES publishers(id) ON DELETE CASCADE, book_id INT REFERENCES books(id) ON DELETE CASCADE);
CREATE TABLE books_authors(book_id INT REFERENCES books(id) ON DELETE CASCADE, author_id INT REFERENCES authors(id) ON DELETE CASCADE);
