CREATE TABLE IF NOT EXISTS projects (
                                        id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                                        created_date TIMESTAMP,
                                        updated_date TIMESTAMP,
                                        deleted_date TIMESTAMP,
                                        project_name VARCHAR(255) NOT NULL,
                                        project_description TEXT,
                                        owner VARCHAR(255),
                                        active BOOLEAN

    );