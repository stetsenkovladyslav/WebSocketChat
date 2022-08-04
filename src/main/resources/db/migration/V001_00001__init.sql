CREATE TABLE IF NOT EXISTS users
(
    id       serial PRIMARY KEY,
    username VARCHAR(30)
    );

CREATE TABLE IF NOT EXISTS messages
(
    id           serial PRIMARY KEY,
    text         VARCHAR(1000),
    message_type VARCHAR(20),
    user_id      INT NOT NULL,
    CONSTRAINT messages_users_fk FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE
    );