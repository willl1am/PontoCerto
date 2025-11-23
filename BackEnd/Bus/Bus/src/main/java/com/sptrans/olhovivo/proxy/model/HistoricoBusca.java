package com.sptrans.olhovivo.proxy.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "historico_busca")
public class HistoricoBusca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String termo;

    private LocalDateTime dataHora = LocalDateTime.now();
}
