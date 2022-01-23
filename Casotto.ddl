CREATE TABLE Spiaggia (nome varchar(255), indirizzo varchar(255), numero_telefono varchar(255), id varchar(255) NOT NULL, CONSTRAINT identificativo PRIMARY KEY (id));
CREATE TABLE Prenotazioni (id varchar(255) NOT NULL, id_cliente varchar(10) NOT NULL, id_ombrellone varchar(10) NOT NULL, id_sdraio varchar(5), data_inizio varchar(50), data_fine varchar(50), totale float, utentiid varchar(10) NOT NULL, CONSTRAINT id_cliente PRIMARY KEY (id));
CREATE TABLE utenti (id varchar(10) NOT NULL, nome varchar(50), email varchar(50), cognome varchar(50), password varchar(50), privilegio varchar(20), PRIMARY KEY (id));
CREATE TABLE Ordinazioni (id varchar(20) NOT NULL, id_qrcode varchar(10) NOT NULL, descrizione varchar(50), prezzo decimal(10, 2), id_cliente varchar(10) NOT NULL, PRIMARY KEY (id));
CREATE TABLE Dettagli_ordini (id varchar(20) NOT NULL, id_prodotto varchar(10), id_ordinazione varchar(20) NOT NULL, quantita int(10), PRIMARY KEY (id));
CREATE TABLE Prodotti (id varchar(20) NOT NULL, nome varchar(50), prezzo decimal(10, 2), categoria varchar(50), quantita_disponibile int(10), Dettagli_ordiniid varchar(20) NOT NULL, PRIMARY KEY (id));
CREATE TABLE Ombrelloni (id_ombrellone varchar(10) NOT NULL, id_qrcode varchar(20) NOT NULL, PRIMARY KEY (id_ombrellone));
ALTER TABLE Prenotazioni ADD CONSTRAINT FKPrenotazio571766 FOREIGN KEY (id_cliente) REFERENCES utenti (id);
ALTER TABLE Prenotazioni ADD CONSTRAINT FKPrenotazio512109 FOREIGN KEY (id_ombrellone) REFERENCES Ombrelloni (id_ombrellone);
ALTER TABLE Ordinazioni ADD CONSTRAINT FKOrdinazion629993 FOREIGN KEY (id_cliente) REFERENCES utenti (id);
ALTER TABLE Ordinazioni ADD CONSTRAINT FKOrdinazion251681 FOREIGN KEY (id_qrcode) REFERENCES Ombrelloni (id_ombrellone);
ALTER TABLE Dettagli_ordini ADD CONSTRAINT FKDettagli_o273703 FOREIGN KEY (id_ordinazione) REFERENCES Ordinazioni (id);
ALTER TABLE Prodotti ADD CONSTRAINT FKProdotti99592 FOREIGN KEY (id) REFERENCES Dettagli_ordini (id);