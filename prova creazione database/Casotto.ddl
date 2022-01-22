CREATE TABLE Spiaggia (nome varchar(255), indirizzo varchar(255), numero_telefono varchar(255), id varchar(255) NOT NULL, CONSTRAINT identificativo PRIMARY KEY (id));
CREATE TABLE Prenotazioni (id varchar(10) NOT NULL, id_cliente varchar(10), num_sdraio int(5), data_inizio varchar(50), data_fine varchar(50), CONSTRAINT id_cliente PRIMARY KEY (id));
CREATE TABLE utenti (id varchar(10) NOT NULL, email varchar(50), cognome varchar(50), password varchar(50), nome varchar(50), privilegio varchar(20), PRIMARY KEY (id));
CREATE TABLE Ordinazioni (id varchar(20) NOT NULL, id_qrcode varchar(10) NOT NULL, descrizione varchar(50), id_cliente varchar(10), PRIMARY KEY (id));
CREATE TABLE Dettagli_ordini (id varchar(20) NOT NULL, id_prodotto varchar(10), id_ordinazione varchar(20) NOT NULL, quantita int(10), PRIMARY KEY (id));
CREATE TABLE Prodotti (id varchar(20) NOT NULL, nome varchar(50), prezzo decimal(10, 2), categoria varchar(50), quantita_disponibile int(10), PRIMARY KEY (id));
CREATE TABLE Ombrelloni (id_ombrellone varchar(10) NOT NULL, id_qrcode varchar(20) NOT NULL, PRIMARY KEY (id_ombrellone));
CREATE TABLE dettaglioPrenotazione (id varchar(10) NOT NULL, id_prenotazione varchar(10) NOT NULL, id_ombrellone varchar(10), PRIMARY KEY (id));
