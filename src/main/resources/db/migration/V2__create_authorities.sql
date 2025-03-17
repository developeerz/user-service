CREATE TABLE IF NOT EXISTS authorities (
    id VARCHAR(16) PRIMARY KEY,
    description TEXT NOT NULL
);

INSERT INTO authorities (id, description) VALUES
('ADMIN', 'Права админа'),
('USER', 'Права пользователя');