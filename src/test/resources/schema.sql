DROP TABLE IF EXISTS tbl_user_rol;
DROP TABLE IF EXISTS tbl_user;
DROP TABLE IF EXISTS tbl_rol;

CREATE TABLE tbl_user (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(255),
    last_name_1 VARCHAR(255),
    last_name_2 VARCHAR(255),
    profile_image VARCHAR(255),
    status VARCHAR(50) NOT NULL,
    deleted BOOLEAN DEFAULT FALSE,
    creation_date TIMESTAMP,
    created_by VARCHAR(255),
    last_update_date TIMESTAMP,
    last_updated_by VARCHAR(255)
);

CREATE TABLE tbl_rol (
    rol_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL,
    description VARCHAR(255),
    creation_date TIMESTAMP,
    created_by VARCHAR(255),
    last_update_date TIMESTAMP,
    last_updated_by VARCHAR(255)
);

CREATE TABLE tbl_user_rol (
    user_id BIGINT,
    rol_id INT,
    PRIMARY KEY (user_id, rol_id),
    FOREIGN KEY (user_id) REFERENCES tbl_user(user_id) ON DELETE CASCADE,
    FOREIGN KEY (rol_id) REFERENCES tbl_rol(rol_id) ON DELETE CASCADE
); 