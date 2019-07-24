create table name (
  id serial primary key,
  nconst varchar not null,
  primary_name varchar not null,
  birth_year int,
  death_year int,
  primary_profession varchar,
  known_for_titles varchar
);

create table movie (
  id serial primary key,
  tconst varchar not null,
  title_type varchar not null,
  primary_title varchar not null,
  original_title varchar null,
  is_adult boolean not null,
  is_favorite boolean default false,
  start_year int null,
  end_year int,
  runtime_minutes int,
  genres varchar
);

