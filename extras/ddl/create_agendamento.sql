CREATE TABLE agendamento(
    id SERIAL PRIMARY KEY,
    datahora TIMESTAMP NOT NULL,
    status VARCHAR(50) DEFAULT 'Agendado',
    clienteid INTEGER,
    barbeiroid INTEGER,
    FOREIGN KEY (clienteid) REFERENCES cliente(id),
    FOREIGN KEY (barbeiroid) REFERENCES barbeiro(id)
);
