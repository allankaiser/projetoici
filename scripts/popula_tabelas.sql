-- ===========================================
-- INSERÇÃO DE 1 MILHÃO DE REGISTROS ALEATÓRIOS
-- ===========================================
-- Este método é extremamente rápido (segundos a poucos minutos dependendo da máquina)
-- e não precisa de loops manuais.
-- ===========================================

INSERT INTO paciente (nome, cpf, data_nascimento, sexo, dt_inclusao, ativo)
SELECT
    -- Gerar nomes aleatórios
    initcap(
        (array['João','Maria','José','Ana','Paulo','Clara','Lucas','Juliana','Rafael','Fernanda',
               'Pedro','Camila','Marcos','Aline','Diego','Patrícia','Fábio','Carla','Luiz','Beatriz'])[floor(random()*20)+1]
        || ' ' ||
        (array['Silva','Souza','Oliveira','Santos','Pereira','Lima','Costa','Almeida','Ferreira','Gomes'])[floor(random()*10)+1]
    ) AS nome,

    -- CPF numérico com padding para 11 dígitos (único)
    lpad((10000000000 + generate_series)::text, 11, '0') AS cpf,

    -- Datas de nascimento aleatórias entre 1940-01-01 e 2010-12-31
    date '1940-01-01' + (random() * (date '2010-12-31' - date '1940-01-01'))::int AS data_nascimento,

    -- Sexo aleatório entre M, F e O
    (array['M','F','O'])[floor(random()*3)+1] AS sexo,

    -- Data de inclusão aleatória nos últimos 5 anos
    (current_date - (random() * 1825)::int) AS dt_inclusao,

    -- Ativo aleatório
    (random() > 0.2) AS ativo  -- 80% ativos, 20% inativos
FROM generate_series(1, 1000000);


-- ===========================================
-- INSERÇÃO DE 1 MILHÃO DE REGISTROS ALEATÓRIOS
-- PARA A TABELA MEDICAMENTO
-- ===========================================
-- Gera 1.000.000 registros de medicamentos com
-- nome, posologia, flag de controlado (0 ou 1)
-- e data de cadastro aleatória.
-- ===========================================

INSERT INTO medicamento (nome, controlado, posologia, data_cadastro)
SELECT
    -- Nome do medicamento (aleatório entre 20 nomes base)
    initcap(
        (array[
            'Paracetamol', 'Dipirona', 'Ibuprofeno', 'Amoxicilina', 'Omeprazol',
            'Ranitidina', 'Cetirizina', 'Losartana', 'Simvastatina', 'Azitromicina',
            'Metformina', 'Prednisona', 'Diclofenaco', 'Lorazepam', 'Clonazepam',
            'Enalapril', 'Captopril', 'Atenolol', 'Nimesulida', 'Albendazol'
        ])[floor(random()*20)+1]
    ) || ' ' || 
    -- Geração de dosagem aleatória
    ((array['50mg','100mg','250mg','500mg','20mg','10mg'])[floor(random()*6)+1]) AS nome,

    -- Campo controlado: 0 = não, 1 = sim (20% sim, 80% não)
    CASE WHEN random() < 0.2 THEN 1 ELSE 0 END AS controlado,

    -- Posologia aleatória (ex: "Tomar 1 comprimido a cada 8 horas")
    (
        'Tomar ' ||
        (array['1','2','3'])[floor(random()*3)+1] ||
        ' ' ||
        (array['comprimido','cápsula','gota'])[floor(random()*3)+1] ||
        ' a cada ' ||
        (array['6','8','12'])[floor(random()*3)+1] ||
        ' horas'
    ) AS posologia,

    -- Data de cadastro aleatória nos últimos 3 anos
    (now() - (random() * 1095)::int * interval '1 day') AS data_cadastro

FROM generate_series(1, 1000000);
