CREATE TABLE servico(
    id SERIAL PRIMARY KEY,
    tipo VARCHAR (100) NOT NULL,
    descricao TEXT,
    valor NUMERIC(7, 2) NOT NULL
);
