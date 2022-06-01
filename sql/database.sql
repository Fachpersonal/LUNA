/*
USER Table
*/
DROP TABLE IF EXISTS 'users';
CREATE TABLE 'users' (
    'id' INTEGER PRIMARY KEY AUTO_INCREMENT,
    'username' TEXT NOT NULL,
    'password' TEXT NOT NULL
);
INSERT INTO 'users' ('username', 'password') VALUES
('admin', '370270537ce75eab5cdb087cb2a6dcc25f18bcf540ebe23868e9be43c5e4f47'),
('user', '3ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4');

/*
Default LUNA events [CORE-ONLY]
*/
DROP TABLE IF EXISTS 'registered_events';
CREATE TABLE 'registered_events' (
    'name' TEXT PRIMARY KEY NOT NULL,
    'description' TEXT NOT NULL
);
INSERT INTO 'registered_events' ('name', 'description') VALUES
('core::stop', 'Shutdowns LUNA'),
('core::help', 'Shows this Menu');

/*
TODO Table
*/
DROP TABLE IF EXISTS 'todo';
CREATE TABLE 'todo' (
  'id' INTEGER PRIMARY KEY AUTO_INCREMENT,
  'title' TEXT NOT NULL,
  'description' TEXT NOT NULL,
  'created_at' DATE DEFAULT CURRENT_TIMESTAMP,
  'created_by' INTEGER NOT NULL
);
INSERT INTO 'todo' ('title', 'description', 'created_by') VALUES
('Todo 1', 'Description 1', 0),
('Todo 2', 'Description 2', 0),
('Todo 3', 'Description 3', 0),
('Todo 4', 'Description 4', 0),
('Todo 5', 'Description 5', 0),
('Todo 6', 'Description 6', 0),
('Todo 7', 'Description 7', 0),
('Todo 8', 'Description 8', 0),
('Todo 9', 'Description 9', 0),
('Todo 10', 'Description 10', 0);