-- Insertar permisos en la tabla 'permissions'
INSERT INTO permissions (name) VALUES ('ADMIN_READ');
INSERT INTO permissions (name) VALUES ('ADMIN_UPDATE');
INSERT INTO permissions (name) VALUES ('ADMIN_CREATE');
INSERT INTO permissions (name) VALUES ('ADMIN_DELETE');
INSERT INTO permissions (name) VALUES ('REGISTER_USER_READ');
INSERT INTO permissions (name) VALUES ('REGISTER_USER_UPDATE');
INSERT INTO permissions (name) VALUES ('REGISTER_USER_CREATE');
INSERT INTO permissions (name) VALUES ('REGISTER_USER_DELETE');

-- Insertar roles en la tabla 'roles'
INSERT INTO roles (name) VALUES ('USER');
INSERT INTO roles (name) VALUES ('ADMIN');
INSERT INTO roles (name) VALUES ('REGISTER_USER');

-- Insertar relaciones para el rol 'ADMIN'
INSERT INTO role_permissions (role_id, permission_id)
VALUES
    ((SELECT id FROM roles WHERE name = 'ADMIN'), (SELECT id FROM permissions WHERE name = 'ADMIN_READ')),
    ((SELECT id FROM roles WHERE name = 'ADMIN'), (SELECT id FROM permissions WHERE name = 'ADMIN_UPDATE')),
    ((SELECT id FROM roles WHERE name = 'ADMIN'), (SELECT id FROM permissions WHERE name = 'ADMIN_CREATE')),
    ((SELECT id FROM roles WHERE name = 'ADMIN'), (SELECT id FROM permissions WHERE name = 'ADMIN_DELETE'));

-- Insertar relaciones para el rol 'REGISTER_USER'
INSERT INTO role_permissions (role_id, permission_id)
VALUES
    ((SELECT id FROM roles WHERE name = 'REGISTER_USER'), (SELECT id FROM permissions WHERE name = 'REGISTER_USER_READ')),
    ((SELECT id FROM roles WHERE name = 'REGISTER_USER'), (SELECT id FROM permissions WHERE name = 'REGISTER_USER_UPDATE')),
    ((SELECT id FROM roles WHERE name = 'REGISTER_USER'), (SELECT id FROM permissions WHERE name = 'REGISTER_USER_CREATE'));
