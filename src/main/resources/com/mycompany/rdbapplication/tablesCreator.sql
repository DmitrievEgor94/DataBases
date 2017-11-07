CREATE TABLE publishers(id INT PRIMARY KEY, name VARCHAR(40));
CREATE TABLE books(id INT PRIMARY KEY , title VARCHAR(40), publication DATE);
CREATE TABLE authors(id INT PRIMARY KEY , name VARCHAR(50), day_of_birthday DATE, day_of_death DATE, sex VARCHAR(10));
CREATE TABLE publishers_books(publisher_id INT REFERENCES publishers(id) ON DELETE CASCADE, book_id INT REFERENCES books(id) ON DELETE CASCADE);
CREATE TABLE books_authors(book_id INT REFERENCES books(id) ON DELETE CASCADE, author_id INT REFERENCES authors(id) ON DELETE CASCADE);
