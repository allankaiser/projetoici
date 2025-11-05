-- Índices recomendados (execute no DBPROJETOTESTE)
CREATE INDEX IF NOT EXISTS idx_paciente_cpf ON paciente (cpf);
CREATE INDEX IF NOT EXISTS idx_paciente_nome ON paciente (lower(nome));
CREATE INDEX IF NOT EXISTS idx_medicamento_nome_lower ON medicamento (LOWER(nome));

