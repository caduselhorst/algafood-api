insert into estado (id, nome) values (1, 'Amapá');
insert into estado (id, nome) values (2, 'Paraná');
insert into estado (id, nome) values (3, 'Santa Catarina');
insert into estado (id, nome) values (4, 'Rio Grande do Sul');
insert into estado (id, nome) values (5, 'Rio Grande do Norte');
insert into estado (id, nome) values (6, 'Tocantins');


insert into cidade (nome, estado_id) values ('Macapá', 1);
insert into cidade (nome, estado_id) values ('Santana', 1);
insert into cidade (nome, estado_id) values ('Curitiba', 2);
insert into cidade (nome, estado_id) values ('São José dos Pinhais', 2);
insert into cidade (nome, estado_id) values ('Pinhais', 2);
insert into cidade (nome, estado_id) values ('Campo Mourão', 2);


insert into cozinha(id, nome) values (1, 'Tailandesa');
insert into cozinha(id, nome) values (2, 'Indiana');
insert into cozinha(id, nome) values (3, 'Argentina');
insert into cozinha(id, nome) values (4, 'Brasileira');

insert into restaurante (nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro, data_cadastro, data_atualizacao) values ('Ong Bak', 2.5, 1, 1, '68902000', 'R Leopoldo Machado', '123', 'Beirol', utc_timestamp, utc_timestamp);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Thai Delivery', 2.5, 1, utc_timestamp, utc_timestamp);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('India Express', 2.8, 2, utc_timestamp, utc_timestamp);

insert into produto (descricao, preco, ativo, restaurante_id) values ('Pad Thai', 80, true, 1);

insert into forma_pagamento (descricao) values ('Dinheiro');
insert into forma_pagamento (descricao) values ('Cartão de Débito');
insert into forma_pagamento (descricao) values ('Cartão de Crédito');
insert into forma_pagamento (descricao) values ('PIX');

insert into permissao (nome, descricao) values ('CONSULTAR_PRODUTOS', 'Permite consultar produtos');



insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (1, 4), (2, 1),(2, 2), (2, 4), (3, 1), (3, 4);
