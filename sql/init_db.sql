-- ================================
--  用户、角色、权限基础模型
--  设计目标：支持 RBAC（Role-Based Access Control）
--  作者：海龙
--  数据库：PostgreSQL
-- ================================

-- 1. 删除旧表（注意顺序，防止外键约束报错）
DROP TABLE IF EXISTS user_roles CASCADE;
DROP TABLE IF EXISTS role_permissions CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS roles CASCADE;
DROP TABLE IF EXISTS permissions CASCADE;

-- ================================
-- 2. 权限表
-- ================================
CREATE TABLE permissions (
    id BIGSERIAL PRIMARY KEY,
    code VARCHAR(100) NOT NULL UNIQUE,       -- 权限代码，如 'USER_READ'、'ORDER_DELETE'
    name VARCHAR(100) NOT NULL,              -- 权限名称
    description VARCHAR(255),
    created_at TIMESTAMP DEFAULT NOW()
);

-- ================================
-- 3. 角色表
-- ================================
CREATE TABLE roles (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,       -- 角色名，如 'ADMIN'、'USER'
    description VARCHAR(255),
    created_at TIMESTAMP DEFAULT NOW()
);

-- ================================
-- 4. 用户表
-- ================================
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,   -- 登录名
    password VARCHAR(255) NOT NULL,          -- 密码（BCrypt 加密）
    enabled BOOLEAN NOT NULL DEFAULT TRUE,   -- 是否启用
    created_at TIMESTAMP DEFAULT NOW()
);

-- ================================
-- 5. 用户-角色 关联表
-- ================================
CREATE TABLE user_roles (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),

    CONSTRAINT fk_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT fk_role
        FOREIGN KEY (role_id)
        REFERENCES roles(id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
);

-- 索引优化（加速查询）
CREATE INDEX idx_user_roles_user ON user_roles(user_id);
CREATE INDEX idx_user_roles_role ON user_roles(role_id);

-- ================================
-- 6. 角色-权限 关联表
-- ================================
CREATE TABLE role_permissions (
    role_id BIGINT NOT NULL,
    permission_id BIGINT NOT NULL,
    PRIMARY KEY (role_id, permission_id),

    CONSTRAINT fk_rp_role
        FOREIGN KEY (role_id)
        REFERENCES roles(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT fk_rp_permission
        FOREIGN KEY (permission_id)
        REFERENCES permissions(id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
);

-- 索引优化
CREATE INDEX idx_role_permissions_role ON role_permissions(role_id);
CREATE INDEX idx_role_permissions_permission ON role_permissions(permission_id);

-- ================================
-- 7. 示例数据
-- ================================

-- 插入一些初始权限
INSERT INTO permissions (code, name, description) VALUES
('USER_READ', '用户查看', '允许查看用户信息'),
('USER_WRITE', '用户编辑', '允许修改用户信息'),
('ROLE_MANAGE', '角色管理', '允许分配和修改角色'),
('PERMISSION_MANAGE', '权限管理', '允许修改权限定义');

-- 插入角色
INSERT INTO roles (name, description) VALUES
('ADMIN', '系统管理员'),
('USER', '普通用户');

-- 插入用户
INSERT INTO users (username, password, enabled) VALUES
('admin', '$2a$10$abcdefghijklmnopqrstuvwx1234567890abcdefghi', TRUE),
('testuser', '$2a$10$abcdefghijklmnopqrstuvwx1234567890abcdefghi', TRUE);

-- 建立用户-角色关系
INSERT INTO user_roles (user_id, role_id)
SELECT u.id, r.id FROM users u, roles r WHERE u.username='admin' AND r.name='ADMIN';

INSERT INTO user_roles (user_id, role_id)
SELECT u.id, r.id FROM users u, roles r WHERE u.username='testuser' AND r.name='USER';

-- 建立角色-权限关系
INSERT INTO role_permissions (role_id, permission_id)
SELECT r.id, p.id FROM roles r, permissions p
WHERE r.name='ADMIN'; -- 管理员拥有所有权限

INSERT INTO role_permissions (role_id, permission_id)
SELECT r.id, p.id FROM roles r, permissions p
WHERE r.name='USER' AND p.code='USER_READ'; -- 普通用户仅能查看