Ponto Certo – Banco de Dados

Este documento descreve toda a estrutura do banco de dados utilizada pelo projeto Ponto Certo, assim como as instruções de conexão, criação da tabela, estrutura da Model para a sequencia do projeto e integração com o backend utilizando o MVC.

---

1. Informações Gerais do Banco

-Banco de dados:'ponto_certo'
Gerenciador: MySQL WorkBench
Porta padrão:3306
Status: Ok 
Tabela principal:`historico_buscas`

2. Script SQL do Banco (Tabela + Inserts)

USE ponto_certo;

CREATE TABLE IF NOT EXISTS historico_buscas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    linha VARCHAR(20) NOT NULL,
    sentido VARCHAR(100),
    tempo_previsto VARCHAR(20),
    data_hora DATETIME DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO historico_buscas (linha, sentido, tempo_previsto)
VALUES
('3006-10', 'CPTM GUAIASES → JD. FANGANIELO', '5 min'),
('2705-10', 'METRÔ ITAQUERA → JD. FANGANIELO', '8 min'),
('875P-10', 'TERMINAL LAPA → JD. PAULO VI', '3 min'),
('7023-10', 'TERMINAL SANTO AMARO → JD. NAKAMURA', '16 min'),
('3160-10', 'TERM. VL. PRUDENTE → TERM. PQ. D. PEDRO II', '5 min');
