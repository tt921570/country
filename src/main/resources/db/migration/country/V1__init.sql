CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE SCHEMA IF NOT EXISTS country;

-- Дать права пользователю
GRANT USAGE ON SCHEMA country TO postgres;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA country TO postgres;

CREATE TABLE IF NOT EXISTS country.name_x_code
(
    id          UUID unique  not null default uuid_generate_v1() primary key,
    full_name   varchar(50) not null,
    code        varchar(10) not null unique,
    created_at  date not null default current,
    updated_at  date not null default current
);

INSERT INTO country.name_x_code (full_name, code)
VALUES
     ('Fiji', 'FJ')
    ,('Tanzania', 'TZ')
    ,('Western Sahara', 'EH')
;