CREATE TABLE agendamentoservico(
    agendamentoid INTEGER,
    servicoid INTEGER, 
    PRIMARY KEY(agendamentoid, servicoid),
    FOREIGN KEY(agendamentoid) REFERENCES agendamento(id),
    FOREIGN KEY(servicoid) REFERENCES servico(id)
);
