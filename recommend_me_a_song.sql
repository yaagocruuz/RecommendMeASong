create table genre(
id_genre serial primary key not null,
name_genre varchar(40) not null
);

create table band(
id_band serial primary key not null,
name_band varchar(40) not null
);

create table artist(
id_artist serial primary key not null,
name_artist varchar(50) not null,
band integer not null,
constraint fk_band foreign key(band) references band(id_band)
);

create table song(
id_song serial primary key not null,
name_song varchar(60) not null,
genre integer not null,
band integer not null,
constraint fk_genre foreign key(genre) references genre(id_genre),
constraint fk_band foreign key(band) references band(id_band)
);

create table band_has_artist(
band integer not null,
artist integer not null,
constraint pk_band_has_artist primary key(band, artist),
constraint fk_band foreign key(band) references band(id_band),
constraint fk_artist foreign key(artist) references artist(id_artist)
);

create table user_c(
id_user serial primary key not null,
name_user varchar(50) not null,
email_user varchar(50) not null,
password_user varchar(12) not null
);

create table user_has_genre(
user_c integer not null,
genre integer not null,
constraint pk_user_has_genre primary key(user_c, genre),
constraint fk_user foreign key(user_c) references user_c(id_user),
constraint fk_genre foreign key(genre) references genre(id_genre)
);

create table user_has_artist(
user_c integer not null,
artist integer not null,
constraint pk_user_has_artist primary key(user_c, artist),
constraint fk_user foreign key(user_c) references user_c(id_user),
constraint fk_artist foreign key(artist) references artist(id_artist)
);

create table already_heard(
user_c integer not null,
song integer not null,
constraint pk_already_heard primary key(user_c, song),
constraint fk_user foreign key(user_c) references user_c(id_user),
constraint fk_song foreign key(song) references song(id_song)
);

create or replace function deleteUser() returns trigger as $$
begin

delete from user_has_genre where user_c = old.id_user;
delete from user_has_artist where user_c = old.id_user;
return old;

end;
$$ language plpgsql;

create trigger "deleteUser"
before delete
on user_c
for each row
execute procedure deleteUser();

insert into genre values(1, 'R&B');
insert into genre values(2, 'Rock');
insert into genre values(3, 'Funk');

insert into band values(1, 'Amy Winehouse');
insert into band values(2, 'Elton John');
insert into band values(3, 'MC Brinquedo');
insert into band values(4, 'Queen');
insert into band values(5, 'ACDC');

insert into artist values(1, 'Amy Winehouse', 1);
insert into artist values(2, 'Elton John', 2);
insert into artist values(3, 'Mc Brinquedo', 3);
insert into artist values(4, 'Freddy Mercury', 4);
insert into artist values(5, 'Angus Young', 5);

insert into song values(1, 'Back to black', 1, 1);
insert into song values(2, 'Rocket Man', 2, 2);
insert into song values(3, 'Bohemiah Rapsody', 2, 4);
insert into song values(4, 'Radio GaGa', 2, 4);
insert into song values(5, 'TNT', 2, 5);
insert into song values(6, 'Hells Bells', 2, 5);
insert into song values(7, 'Highway to hell', 2, 5);

insert into user_c values (1, 'rodrigo', 'rod@hot.com', '123');
insert into user_has_genre values (1, 1);
insert into user_has_genre values (1, 2);
insert into user_has_artist values (1, 1);
insert into user_has_artist values (1, 4);
insert into user_has_artist values (1, 5);

insert into already_heard values (1, 6);
insert into already_heard values (1, 1);

select * from artist;

select * from user_c;
select * from user_has_genre where user_c = 5;
select * from user_has_artist where user_c = 5;

select * from song;

select song from already_heard where user_c = 5;

delete from already_heard where song = 1 and user_c = 1;

((select s.id_song from user_c u, song s, user_has_genre usg, user_has_artist uha
where 5 = usg.user_c and 5 = uha.user_c 
and (usg.genre = s.genre or uha.artist in (select a.id_artist from artist a, band b
						where a.band = b.id_band and s.band = b.id_band)))
intersect
((select s.id_song from song s)
except
(select s.id_song from song s, already_heard ah 
where s.id_song = ah.song)));
