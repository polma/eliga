
--drop table rodzice;
--drop table uczniowie;

create table IF NOT EXISTS uczniowie(
  id serial PRIMARY KEY,
  imie varchar(30) NOT NULL,
  nazwisko varchar(30) NOT NULL,
  pesel varchar(20)
);

create table IF NOT EXISTS nauczyciele(
  id serial PRIMARY KEY,
  imie varchar(30),
  nazwisko varchar(30)
);

create table IF NOT EXISTS przedmioty(
  id serial primary key,

  nazwa varchar(50),
  opis text,

  id_nauczyciela integer,
  FOREIGN KEY (id_nauczyciela) REFERENCES nauczyciele(id)
);

create table IF NOT EXISTS oceny(
  id serial PRIMARY KEY,
  
  wartosc integer,
  znak char,
  data timestamp,

  id_ucznia integer,
  id_przedmiotu integer,

  FOREIGN KEY (id_ucznia) REFERENCES uczniowie(id),
  FOREIGN KEY (id_przedmiotu) REFERENCES przedmioty(id)
);

create table IF NOT EXISTS uwagi(

  id serial PRIMARY KEY,
  
  tresc text,
  data timestamp,
  
  id_ucznia integer,
  id_nauczyciela integer,

  FOREIGN KEY (id_ucznia) REFERENCES uczniowie(id),
  FOREIGN KEY (id_nauczyciela) REFERENCES nauczyciele(id)

);

create table IF NOT EXISTS rodzice(
  id serial PRIMARY KEY,
  imie varchar(30) NOT NULL,
  nazwisko varchar(30) NOT NULL,

  email varchar(50),
  tel varchar(50),

  id_ucznia integer,

  FOREIGN KEY (id_ucznia) REFERENCES uczniowie(id)
);

