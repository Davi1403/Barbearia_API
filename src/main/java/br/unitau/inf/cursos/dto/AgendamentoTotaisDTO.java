package br.unitau.inf.cursos.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface AgendamentoTotaisDTO {

    // Dia do agendamento
    LocalDate getDate();

    // Total de servicos do dia
    Long getQtdServicos();

    // Faturamento do dia
    BigDecimal getFaturamento();
}
