DROP DATABASE IF EXISTS feeds;

CREATE DATABASE feeds;

USE feeds;

CREATE TABLE posts (
    post_id VARCHAR(8) Not NULL,
    picture mediumblob,
    PRIMARY KEY (post_id)
);

GRANT ALL privileges ON feeds.* to 'test'@'localhost';
