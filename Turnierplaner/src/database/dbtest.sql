---------------vvvvvvvvvv------------------------  Schnitt
 
--  Hier sind die -- Kommentare,
--  es kann aber auch #-Zeichen sein.
 
--  Klein/Gro�-Schreibung spielt Rolle nur in Strings
--  Important: As root connecting
--  Connection kann unterschiedlich aussehen,
--  abh�ngig vom mysql-Client.
 
--  Vielleicht so:
--mysql
--connect user=root password=system;
 
--  oder besser so:
--mysql --host=localhost --user=root --password=12345
--mysql --host=localhost --file=Name_von_diesem_Script --user=root --password=12345
 
--  Benutzer max mit dem Password system erstellen
create user 'max'@'localhost' identified by 'system';
 
--  Eigenes Password �ndern
-- alter user user() identified by 'newsystem';
 
-- L�schen einer eventuell schon bestehenden DB VEGA
drop database VEGA;
 
-- Erstellen der DB VEGA
create database VEGA;
 
-- Rechte geben: entweder ALL oder *
grant ALL to max on VEGA;
 
-- L�schen einer eventuell schon bestehenden Tabelle ALPHA
drop table ALPHA;
 
-- Erstellen der Tabelle ALPHA
create table ALPHA
(
  ID number(6),
  Bezeichnung char(20)
);
 
-- Einf�gen von Datens�tzen in die Tabelle
insert into ALPHA values (1, 'Planet_A');
insert into ALPHA values (2, 'Planet_B');
--  Die Felder fer Tabelle m�ssen hier nicht geschrieben werden,
--  wenn die Reihenfolge der in der Definition entspricht.
 
--  manchmal notwendig, falls weiter noch viele Statements kommen
commit;
 
quit;

 
-- -------------------------
 
---------------^^^^^^^^^^------------------------  Schnitt