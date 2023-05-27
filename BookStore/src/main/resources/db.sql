CREATE TABLE category (
  category_id SERIAL PRIMARY KEY,
  name TEXT NOT NULL
);

CREATE TABLE publishing_house (
  publishing_house_id SERIAL PRIMARY KEY,
  name TEXT NOT NULL
);

CREATE TABLE buyer (
  buyer_id SERIAL PRIMARY KEY,
  first_name TEXT NOT NULL,
  second_name TEXT NOT NULL,
  email TEXT,
  phone TEXT,
  CHECK (email IS NOT NULL OR phone IS NOT NULL)
);

CREATE TABLE book (
  book_id SERIAL PRIMARY KEY,
  title TEXT NOT NULL,
  author TEXT NOT NULL,
  price NUMERIC(10, 2) NOT NULL,
  category_id INTEGER REFERENCES category(category_id) ON DELETE CASCADE
);

CREATE TABLE orderb (
  orderb_id SERIAL PRIMARY KEY,
  total_price NUMERIC(10, 2) NOT NULL,
  buyer_id INTEGER REFERENCES buyer(buyer_id) ON DELETE CASCADE
);

CREATE TABLE book_order (
  book_id INTEGER REFERENCES book(book_id) ON DELETE CASCADE,
  orderb_id INTEGER REFERENCES orderb(orderb_id) ON DELETE CASCADE,
  PRIMARY KEY (book_id, orderb_id)
);
