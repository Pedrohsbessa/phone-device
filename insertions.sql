-- Enable UUID extension if not already enabled
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Insert sample phones with different brands and states
INSERT INTO phones (id, name, brand, state, creation_time)
VALUES
    -- Samsung phones
    (uuid_generate_v4(), 'Galaxy S21', 'Samsung', 'AVAILABLE', CURRENT_TIMESTAMP),
    (uuid_generate_v4(), 'Galaxy S22', 'Samsung', 'IN_USE', CURRENT_TIMESTAMP),
    (uuid_generate_v4(), 'Galaxy A52', 'Samsung', 'INACTIVE', CURRENT_TIMESTAMP),
    (uuid_generate_v4(), 'Galaxy Note 20', 'Samsung', 'AVAILABLE', CURRENT_TIMESTAMP),
    (uuid_generate_v4(), 'Galaxy Z Fold 3', 'Samsung', 'IN_USE', CURRENT_TIMESTAMP),
    (uuid_generate_v4(), 'Galaxy S20 FE', 'Samsung', 'AVAILABLE', CURRENT_TIMESTAMP),
    (uuid_generate_v4(), 'Galaxy A72', 'Samsung', 'INACTIVE', CURRENT_TIMESTAMP),
    (uuid_generate_v4(), 'Galaxy M52', 'Samsung', 'AVAILABLE', CURRENT_TIMESTAMP),
    (uuid_generate_v4(), 'Galaxy S23', 'Samsung', 'IN_USE', CURRENT_TIMESTAMP),
    (uuid_generate_v4(), 'Galaxy A32', 'Samsung', 'AVAILABLE', CURRENT_TIMESTAMP),
    (uuid_generate_v4(), 'iPhone 13', 'Apple', 'AVAILABLE', CURRENT_TIMESTAMP),
    (uuid_generate_v4(), 'iPhone 14 Pro', 'Apple', 'IN_USE', CURRENT_TIMESTAMP),
    (uuid_generate_v4(), 'iPhone 12', 'Apple', 'INACTIVE', CURRENT_TIMESTAMP),
    (uuid_generate_v4(), 'iPhone SE 2022', 'Apple', 'AVAILABLE', CURRENT_TIMESTAMP),
    (uuid_generate_v4(), 'iPhone 14', 'Apple', 'IN_USE', CURRENT_TIMESTAMP),
    (uuid_generate_v4(), 'iPhone 13 Pro', 'Apple', 'AVAILABLE', CURRENT_TIMESTAMP),
    (uuid_generate_v4(), 'iPhone 11', 'Apple', 'INACTIVE', CURRENT_TIMESTAMP),
    (uuid_generate_v4(), 'iPhone 14 Plus', 'Apple', 'AVAILABLE', CURRENT_TIMESTAMP),
    (uuid_generate_v4(), 'iPhone 13 Mini', 'Apple', 'IN_USE', CURRENT_TIMESTAMP),
    (uuid_generate_v4(), 'iPhone 12 Pro', 'Apple', 'AVAILABLE', CURRENT_TIMESTAMP),
    (uuid_generate_v4(), 'Redmi Note 11', 'Xiaomi', 'AVAILABLE', CURRENT_TIMESTAMP),
    (uuid_generate_v4(), 'Mi 11 Ultra', 'Xiaomi', 'IN_USE', CURRENT_TIMESTAMP),
    (uuid_generate_v4(), 'POCO X3', 'Xiaomi', 'INACTIVE', CURRENT_TIMESTAMP),
    (uuid_generate_v4(), 'Redmi 10', 'Xiaomi', 'AVAILABLE', CURRENT_TIMESTAMP),
    (uuid_generate_v4(), 'Mi 12', 'Xiaomi', 'IN_USE', CURRENT_TIMESTAMP),
    (uuid_generate_v4(), 'POCO F3', 'Xiaomi', 'AVAILABLE', CURRENT_TIMESTAMP),
    (uuid_generate_v4(), 'Redmi Note 10', 'Xiaomi', 'INACTIVE', CURRENT_TIMESTAMP),
    (uuid_generate_v4(), 'Mi 11 Lite', 'Xiaomi', 'AVAILABLE', CURRENT_TIMESTAMP),
    (uuid_generate_v4(), 'POCO M3', 'Xiaomi', 'IN_USE', CURRENT_TIMESTAMP),
    (uuid_generate_v4(), 'Redmi 9', 'Xiaomi', 'AVAILABLE', CURRENT_TIMESTAMP),
    (uuid_generate_v4(), 'Moto G60', 'Motorola', 'AVAILABLE', CURRENT_TIMESTAMP),
    (uuid_generate_v4(), 'Edge 30', 'Motorola', 'IN_USE', CURRENT_TIMESTAMP),
    (uuid_generate_v4(), 'Moto G42', 'Motorola', 'INACTIVE', CURRENT_TIMESTAMP),
    (uuid_generate_v4(), 'Edge 20', 'Motorola', 'AVAILABLE', CURRENT_TIMESTAMP),
    (uuid_generate_v4(), 'Moto G52', 'Motorola', 'IN_USE', CURRENT_TIMESTAMP),
    (uuid_generate_v4(), 'One Fusion', 'Motorola', 'AVAILABLE', CURRENT_TIMESTAMP),
    (uuid_generate_v4(), 'Moto G71', 'Motorola', 'INACTIVE', CURRENT_TIMESTAMP),
    (uuid_generate_v4(), 'Edge 30 Pro', 'Motorola', 'AVAILABLE', CURRENT_TIMESTAMP),
    (uuid_generate_v4(), 'Moto G31', 'Motorola', 'IN_USE', CURRENT_TIMESTAMP),
    (uuid_generate_v4(), 'One 5G', 'Motorola', 'AVAILABLE', CURRENT_TIMESTAMP),
    (uuid_generate_v4(), 'Pixel 6', 'Google', 'AVAILABLE', CURRENT_TIMESTAMP),
    (uuid_generate_v4(), 'Pixel 7 Pro', 'Google', 'IN_USE', CURRENT_TIMESTAMP),
    (uuid_generate_v4(), 'Pixel 5', 'Google', 'INACTIVE', CURRENT_TIMESTAMP),
    (uuid_generate_v4(), 'Pixel 6a', 'Google', 'AVAILABLE', CURRENT_TIMESTAMP),
    (uuid_generate_v4(), 'Pixel 7', 'Google', 'IN_USE', CURRENT_TIMESTAMP),
    (uuid_generate_v4(), 'Pixel 4a', 'Google', 'AVAILABLE', CURRENT_TIMESTAMP),
    (uuid_generate_v4(), 'Pixel 5a', 'Google', 'INACTIVE', CURRENT_TIMESTAMP),
    (uuid_generate_v4(), 'Pixel 6 Pro', 'Google', 'AVAILABLE', CURRENT_TIMESTAMP),
    (uuid_generate_v4(), 'Pixel 4 XL', 'Google', 'IN_USE', CURRENT_TIMESTAMP),
    (uuid_generate_v4(), 'Pixel 3a', 'Google', 'AVAILABLE', CURRENT_TIMESTAMP);