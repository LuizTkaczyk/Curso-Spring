insert into cozinha (id, nome) values (1, 'Tailandesa');
insert into cozinha (id, nome) values (2, 'Indiana');
insert into cozinha (id, nome) values (3, 'Argentina');

insert into restaurante (nome, taxa_frete, cozinha_id) values ('Thai Gourmet', 10, 1);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Thai Delivery', 9.50, 1);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Tuk Tuk Comida Indiana', 15, 2);

insert into estado (id, nome) values (1, 'Minas Gerais');
insert into estado (id, nome) values (2, 'Sao Paulo');
insert into estado (id, nome) values (3, 'Ceara');
insert into estado (id, nome) values (4, 'Rio de Janeiro');
insert into estado (id, nome) values (5, 'Parana');

insert into cidade (id, nome, estado_id) values (1, 'Uberlandia', 1);
insert into cidade (id, nome, estado_id) values (2, 'Sao Paulo', 2);
insert into cidade (id, nome, estado_id) values (3, 'Ceara', 3);
insert into cidade (id, nome, estado_id) values (4, 'Rio de Janeiro', 4);
insert into cidade (id, nome, estado_id) values (5, 'Curitiba', 5);
