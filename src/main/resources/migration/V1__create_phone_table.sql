-- Enable UUID extension
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Create ENUM type for phone state
CREATE TYPE phone_state AS ENUM ('AVAILABLE', 'IN_USE', 'INACTIVE');

-- Create phones table
CREATE TABLE phones (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(255) NOT NULL,
    brand VARCHAR(255) NOT NULL,
    state phone_state NOT NULL DEFAULT 'AVAILABLE',
    creation_time TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Add index on state for faster queries
CREATE INDEX idx_phones_state ON phones(state);