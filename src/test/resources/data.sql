DELETE FROM tbl_user_rol;
DELETE FROM tbl_user;
DELETE FROM tbl_rol;

ALTER TABLE tbl_user ALTER COLUMN user_id RESTART WITH 1;
ALTER TABLE tbl_rol ALTER COLUMN rol_id RESTART WITH 1;

INSERT INTO tbl_user (
    email, 
    password, 
    first_name,
    last_name_1,
    last_name_2,
    status,
    deleted,
    creation_date,
    created_by,
    last_update_date,
    last_updated_by
) VALUES (
    'test@example.com',
    '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu',
    'Test',
    'User',
    'Last',
    'active',
    false,
    CURRENT_TIMESTAMP(),
    'system',
    CURRENT_TIMESTAMP(),
    'system'
);

INSERT INTO tbl_rol (name, description, creation_date, created_by, last_update_date, last_updated_by)
VALUES 
('ROLE_USER', 'Standard user role', CURRENT_TIMESTAMP(), 'system', CURRENT_TIMESTAMP(), 'system'),
('ROLE_ADMIN', 'Administrator role', CURRENT_TIMESTAMP(), 'system', CURRENT_TIMESTAMP(), 'system');

INSERT INTO tbl_user_rol (user_id, rol_id)
SELECT u.user_id, r.rol_id
FROM tbl_user u, tbl_rol r
WHERE u.email = 'test@example.com'
AND r.name IN ('ROLE_USER', 'ROLE_ADMIN'); 