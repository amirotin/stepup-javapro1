DROP TABLE IF EXISTS USERS;
CREATE TABLE USERS
(
    id SERIAL PRIMARY KEY,
    username character varying(100)
);

DROP TABLE IF EXISTS PRODUCTS;
CREATE TABLE PRODUCTS (
                          id SERIAL PRIMARY KEY,
                          account VARCHAR(20),
                          balance DECIMAL(10, 2),
                          product_type VARCHAR(10),
                          user_id INTEGER,
                          FOREIGN KEY (user_id) REFERENCES USERS(id)
);

INSERT INTO USERS (id, username) VALUES (1,'user1'),(2,'user2'),(2,'user3');

INSERT INTO PRODUCTS (account, balance, product_type, user_id)
VALUES
    ('acc1', 12.00, '0', (SELECT id FROM users WHERE username = 'user1')),
    ('acc2', 23.00, '0', (SELECT id FROM users WHERE username = 'user2')),
    ('acc3', 34.00, '1', (SELECT id FROM users WHERE username = 'user3')),
    ('acc4', 45.00, '1', (SELECT id FROM users WHERE username = 'user1'));