-- Inserindo dados na tabela 'cozinha'
insert into cozinha (id, nome) values (1, 'Tailandesa');
insert into cozinha (id, nome) values (2, 'Indiana');
insert into cozinha (id, nome) values (3, 'Italiana');
insert into cozinha (id, nome) values (4, 'Japonesa');
insert into cozinha (id, nome) values (5, 'Mexicana');

-- Inserindo dados na tabela 'restaurante'
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (1, 'Thai Gourmet', 10, 1);
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (2, 'Thai Delivery', 0, 1);
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (3, 'Tuk Tuk Comida Indiana', 15, 2);
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (4, 'La Bella Itália', 8, 3);
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (5, 'Sushi Express', 0, 4);
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (6, 'Taco Fiesta', 11.50, 5);

-- Inserindo dados na tabela 'estado'
insert into estado (id, nome) values (1, 'Minas Gerais');
insert into estado (id, nome) values (2, 'São Paulo');
insert into estado (id, nome) values (3, 'Ceará');
insert into estado (id, nome) values (4, 'Rio de Janeiro');
insert into estado (id, nome) values (5, 'Bahia');

-- Inserindo dados na tabela 'cidade'
insert into cidade (id, nome, estado_id) values (1, 'Uberlândia', 1);
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1);
insert into cidade (id, nome, estado_id) values (3, 'São Paulo', 2);
insert into cidade (id, nome, estado_id) values (4, 'Campinas', 2);
insert into cidade (id, nome, estado_id) values (5, 'Fortaleza', 3);
insert into cidade (id, nome, estado_id) values (6, 'Rio de Janeiro', 4);
insert into cidade (id, nome, estado_id) values (7, 'Salvador', 5);

-- Inserindo dados na tabela 'forma_pagamento'
insert into forma_pagamento (id, descricao) values (1, 'Cartão de crédito');
insert into forma_pagamento (id, descricao) values (2, 'Cartão de débito');
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro');
insert into forma_pagamento (id, descricao) values (4, 'Pix');
insert into forma_pagamento (id, descricao) values (5, 'Vale-refeição');

-- Inserindo dados na tabela 'permissao'
insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');
insert into permissao (id, nome, descricao) values (3, 'CONSULTAR_RESTAURANTES', 'Permite consultar restaurantes');
insert into permissao (id, nome, descricao) values (4, 'EDITAR_RESTAURANTES', 'Permite editar restaurantes');
insert into permissao (id, nome, descricao) values (5, 'GERENCIAR_FORMAS_PAGAMENTO', 'Permite gerenciar formas de pagamento');
insert into permissao (id, nome, descricao) values (6, 'CONSULTAR_CIDADES', 'Permite consultar cidades');
insert into permissao (id, nome, descricao) values (7, 'EDITAR_CIDADES', 'Permite editar cidades');


