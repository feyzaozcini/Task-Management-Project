CREATE TABLE IF NOT EXISTS users (
                                     id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                                     email VARCHAR(255) NOT NULL,
                                     first_name VARCHAR(255) NOT NULL,
                                     last_name VARCHAR(255) NOT NULL,
                                     password VARCHAR(255) NOT NULL,
                                     created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                     status BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS roles (
                                     id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                                     name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS user_roles (
                                    user_id INT NOT NULL,
                                    role_id INT NOT NULL,
                                    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                                    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE,
                                    PRIMARY KEY (user_id, role_id)
);