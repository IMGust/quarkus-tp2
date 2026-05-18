-- Insere Motores de Exemplo
INSERT INTO motor (id, nome, cilindrada, potencia, torque, taxacompressao, rpmmax, preco, datacadastro)
VALUES (1, 'V8 5.0L Coyote', 5.0, 460, 556.0, 12.0, 7500, 45000.00, CURRENT_TIMESTAMP);

INSERT INTO motor (id, nome, cilindrada, potencia, torque, taxacompressao, rpmmax, preco, datacadastro)
VALUES (2, '2.0L TSI EA888', 2.0, 230, 350.0, 9.6, 6500, 25000.00, CURRENT_TIMESTAMP);

ALTER SEQUENCE motor_id_seq RESTART WITH 3;

-- Insere Pistões de Exemplo
INSERT INTO pistao (id, material, diametro, curso, volumedomo, marca, preco, motor_id, datacadastro)
VALUES (1, 'Forjado', 92.2, 92.7, -15.0, 'IAPEL', 2500.00, 1, CURRENT_TIMESTAMP);

INSERT INTO pistao (id, material, diametro, curso, volumedomo, marca, preco, motor_id, datacadastro)
VALUES (2, 'Fundido', 82.5, 92.8, -10.0, 'Mahle', 1200.00, 2, CURRENT_TIMESTAMP);

ALTER SEQUENCE pistao_id_seq RESTART WITH 3;

-- Insere Sobrealimentação de Exemplo (Pai do Turbo)
INSERT INTO sobrealimentacao (id, tipo) VALUES (1, 1);

-- Insere Turbo de Exemplo (Filho)
INSERT INTO turbo (id, tipoturbo, pressaoboost, possuiintercooler, quantidade, fabricante, ladoescape, ladoadmissao, tipoflange, tipomancal, wastegate, sistemarefrigeracao)
VALUES (1, 1, 1.5, true, 1, 'Garrett', 0.63, 0.50, 'T3', 'BALL_BEARING', 'INTERNA', 'OLEO_AGUA');

ALTER SEQUENCE sobrealimentacao_id_seq RESTART WITH 2;