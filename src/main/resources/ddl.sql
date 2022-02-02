create table cidade (id bigint not null auto_increment, nome varchar(30) not null, estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table estado (id bigint not null auto_increment, nome varchar(30) not null, primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo_permissoes (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB
create table permissao (id bigint not null auto_increment, descricao varchar(255), nome varchar(30) not null, primary key (id)) engine=InnoDB
create table produto (id bigint not null auto_increment, ativo bit, descricao varchar(255), preco decimal(19,2), restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null auto_increment, data_atualizacao datetime not null, data_cadastro datetime not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(255) not null, taxa_frete decimal(19,2) not null, cozinha_id bigint not null, endereco_cidade_id bigint, primary key (id)) engine=InnoDB
create table restaurante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
create table usuario (id bigint not null auto_increment, data_cadastro datetime(6), email varchar(255) not null, nome varchar(255) not null, senha varchar(255) not null, primary key (id)) engine=InnoDB
create table usuario_grupos (usuario_id bigint not null, permissao_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table grupo_permissoes add constraint FKbjj8fbcfxr7joapufexdn7fv0 foreign key (permissao_id) references permissao (id)
alter table grupo_permissoes add constraint FKd7wt9tnvrfttdcl5ofoelgi6j foreign key (grupo_id) references grupo (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKbc0tm7hnvc96d8e7e2ulb05yw foreign key (endereco_cidade_id) references cidade (id)
alter table restaurante_forma_pagamento add constraint FK7aln770m80358y4olr03hyhh2 foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restaurante_forma_pagamento add constraint FKa30vowfejemkw7whjvr8pryvj foreign key (restaurante_id) references restaurante (id)
alter table usuario_grupos add constraint FK1g23sdkohueaeargext9rxk41 foreign key (permissao_id) references grupo (id)
alter table usuario_grupos add constraint FK158r9y55ufwykh675ddt8kb43 foreign key (usuario_id) references usuario (id)
insert into estado (id, nome) values (1, 'Amapá')
insert into estado (id, nome) values (2, 'Paraná')
insert into estado (id, nome) values (3, 'Santa Catarina')
insert into estado (id, nome) values (4, 'Rio Grande do Sul')
insert into estado (id, nome) values (5, 'Rio Grande do Norte')
insert into estado (id, nome) values (6, 'Tocantins')
insert into cidade (nome, estado_id) values ('Macapá', 1)
insert into cidade (nome, estado_id) values ('Santana', 1)
insert into cidade (nome, estado_id) values ('Curitiba', 2)
insert into cidade (nome, estado_id) values ('São José dos Pinhais', 2)
insert into cidade (nome, estado_id) values ('Pinhais', 2)
insert into cidade (nome, estado_id) values ('Campo Mourão', 2)
insert into cozinha(id, nome) values (1, 'Tailandesa')
insert into cozinha(id, nome) values (2, 'Indiana')
insert into restaurante (nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro, data_cadastro, data_atualizacao) values ('Ong Bak', 2.5, 1, 1, '68902000', 'R Leopoldo Machado', '123', 'Beirol', utc_timestamp, utc_timestamp)
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Thai Delivery', 2.5, 1, utc_timestamp, utc_timestamp)
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('India Express', 2.8, 2, utc_timestamp, utc_timestamp)
insert into produto (descricao, preco, ativo, restaurante_id) values ('Pad Thai', 80, true, 1)
insert into forma_pagamento (descricao) values ('Dinheiro')
insert into forma_pagamento (descricao) values ('Cartão de Débito')
insert into forma_pagamento (descricao) values ('Cartão de Crédito')
insert into forma_pagamento (descricao) values ('PIX')
insert into permissao (nome, descricao) values ('CONSULTAR_PRODUTOS', 'Permite consultar produtos')
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (1, 4), (2, 1),(2, 2), (2, 4), (3, 1), (3, 4)
create table cidade (id bigint not null auto_increment, nome varchar(30) not null, estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table estado (id bigint not null auto_increment, nome varchar(30) not null, primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo_permissoes (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB
create table permissao (id bigint not null auto_increment, descricao varchar(255), nome varchar(30) not null, primary key (id)) engine=InnoDB
create table produto (id bigint not null auto_increment, ativo bit, descricao varchar(255), preco decimal(19,2), restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null auto_increment, data_atualizacao datetime not null, data_cadastro datetime not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(255) not null, taxa_frete decimal(19,2) not null, cozinha_id bigint not null, endereco_cidade_id bigint, primary key (id)) engine=InnoDB
create table restaurante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
create table usuario (id bigint not null auto_increment, data_cadastro datetime(6), email varchar(255) not null, nome varchar(255) not null, senha varchar(255) not null, primary key (id)) engine=InnoDB
create table usuario_grupos (usuario_id bigint not null, permissao_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table grupo_permissoes add constraint FKbjj8fbcfxr7joapufexdn7fv0 foreign key (permissao_id) references permissao (id)
alter table grupo_permissoes add constraint FKd7wt9tnvrfttdcl5ofoelgi6j foreign key (grupo_id) references grupo (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKbc0tm7hnvc96d8e7e2ulb05yw foreign key (endereco_cidade_id) references cidade (id)
alter table restaurante_forma_pagamento add constraint FK7aln770m80358y4olr03hyhh2 foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restaurante_forma_pagamento add constraint FKa30vowfejemkw7whjvr8pryvj foreign key (restaurante_id) references restaurante (id)
alter table usuario_grupos add constraint FK1g23sdkohueaeargext9rxk41 foreign key (permissao_id) references grupo (id)
alter table usuario_grupos add constraint FK158r9y55ufwykh675ddt8kb43 foreign key (usuario_id) references usuario (id)
insert into estado (id, nome) values (1, 'Amapá')
insert into estado (id, nome) values (2, 'Paraná')
insert into estado (id, nome) values (3, 'Santa Catarina')
insert into estado (id, nome) values (4, 'Rio Grande do Sul')
insert into estado (id, nome) values (5, 'Rio Grande do Norte')
insert into estado (id, nome) values (6, 'Tocantins')
insert into cidade (nome, estado_id) values ('Macapá', 1)
insert into cidade (nome, estado_id) values ('Santana', 1)
insert into cidade (nome, estado_id) values ('Curitiba', 2)
insert into cidade (nome, estado_id) values ('São José dos Pinhais', 2)
insert into cidade (nome, estado_id) values ('Pinhais', 2)
insert into cidade (nome, estado_id) values ('Campo Mourão', 2)
insert into cozinha(id, nome) values (1, 'Tailandesa')
insert into cozinha(id, nome) values (2, 'Indiana')
insert into restaurante (nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro, data_cadastro, data_atualizacao) values ('Ong Bak', 2.5, 1, 1, '68902000', 'R Leopoldo Machado', '123', 'Beirol', utc_timestamp, utc_timestamp)
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Thai Delivery', 2.5, 1, utc_timestamp, utc_timestamp)
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('India Express', 2.8, 2, utc_timestamp, utc_timestamp)
insert into produto (descricao, preco, ativo, restaurante_id) values ('Pad Thai', 80, true, 1)
insert into forma_pagamento (descricao) values ('Dinheiro')
insert into forma_pagamento (descricao) values ('Cartão de Débito')
insert into forma_pagamento (descricao) values ('Cartão de Crédito')
insert into forma_pagamento (descricao) values ('PIX')
insert into permissao (nome, descricao) values ('CONSULTAR_PRODUTOS', 'Permite consultar produtos')
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (1, 4), (2, 1),(2, 2), (2, 4), (3, 1), (3, 4)
create table cidade (id bigint not null auto_increment, nome varchar(30) not null, estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table estado (id bigint not null auto_increment, nome varchar(30) not null, primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo_permissoes (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB
create table permissao (id bigint not null auto_increment, descricao varchar(255), nome varchar(30) not null, primary key (id)) engine=InnoDB
create table produto (id bigint not null auto_increment, ativo bit, descricao varchar(255), preco decimal(19,2), restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null auto_increment, data_atualizacao datetime not null, data_cadastro datetime not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(255) not null, taxa_frete decimal(19,2) not null, cozinha_id bigint not null, endereco_cidade_id bigint, primary key (id)) engine=InnoDB
create table restaurante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
create table usuario (id bigint not null auto_increment, data_cadastro datetime(6), email varchar(255) not null, nome varchar(255) not null, senha varchar(255) not null, primary key (id)) engine=InnoDB
create table usuario_grupos (usuario_id bigint not null, permissao_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table grupo_permissoes add constraint FKbjj8fbcfxr7joapufexdn7fv0 foreign key (permissao_id) references permissao (id)
alter table grupo_permissoes add constraint FKd7wt9tnvrfttdcl5ofoelgi6j foreign key (grupo_id) references grupo (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKbc0tm7hnvc96d8e7e2ulb05yw foreign key (endereco_cidade_id) references cidade (id)
alter table restaurante_forma_pagamento add constraint FK7aln770m80358y4olr03hyhh2 foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restaurante_forma_pagamento add constraint FKa30vowfejemkw7whjvr8pryvj foreign key (restaurante_id) references restaurante (id)
alter table usuario_grupos add constraint FK1g23sdkohueaeargext9rxk41 foreign key (permissao_id) references grupo (id)
alter table usuario_grupos add constraint FK158r9y55ufwykh675ddt8kb43 foreign key (usuario_id) references usuario (id)
create table cidade (id bigint not null auto_increment, nome varchar(30) not null, estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table estado (id bigint not null auto_increment, nome varchar(30) not null, primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo_permissoes (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB
create table permissao (id bigint not null auto_increment, descricao varchar(255), nome varchar(30) not null, primary key (id)) engine=InnoDB
create table produto (id bigint not null auto_increment, ativo bit, descricao varchar(255), preco decimal(19,2), restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null auto_increment, data_atualizacao datetime not null, data_cadastro datetime not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(255) not null, taxa_frete decimal(19,2) not null, cozinha_id bigint not null, endereco_cidade_id bigint, primary key (id)) engine=InnoDB
create table restaurante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
create table usuario (id bigint not null auto_increment, data_cadastro datetime(6), email varchar(255) not null, nome varchar(255) not null, senha varchar(255) not null, primary key (id)) engine=InnoDB
create table usuario_grupos (usuario_id bigint not null, permissao_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table grupo_permissoes add constraint FKbjj8fbcfxr7joapufexdn7fv0 foreign key (permissao_id) references permissao (id)
alter table grupo_permissoes add constraint FKd7wt9tnvrfttdcl5ofoelgi6j foreign key (grupo_id) references grupo (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKbc0tm7hnvc96d8e7e2ulb05yw foreign key (endereco_cidade_id) references cidade (id)
alter table restaurante_forma_pagamento add constraint FK7aln770m80358y4olr03hyhh2 foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restaurante_forma_pagamento add constraint FKa30vowfejemkw7whjvr8pryvj foreign key (restaurante_id) references restaurante (id)
alter table usuario_grupos add constraint FK1g23sdkohueaeargext9rxk41 foreign key (permissao_id) references grupo (id)
alter table usuario_grupos add constraint FK158r9y55ufwykh675ddt8kb43 foreign key (usuario_id) references usuario (id)
insert into estado (id, nome) values (1, 'Amapá')
insert into estado (id, nome) values (2, 'Paraná')
insert into estado (id, nome) values (3, 'Santa Catarina')
insert into estado (id, nome) values (4, 'Rio Grande do Sul')
insert into estado (id, nome) values (5, 'Rio Grande do Norte')
insert into estado (id, nome) values (6, 'Tocantins')
insert into cidade (nome, estado_id) values ('Macapá', 1)
insert into cidade (nome, estado_id) values ('Santana', 1)
insert into cidade (nome, estado_id) values ('Curitiba', 2)
insert into cidade (nome, estado_id) values ('São José dos Pinhais', 2)
insert into cidade (nome, estado_id) values ('Pinhais', 2)
insert into cidade (nome, estado_id) values ('Campo Mourão', 2)
insert into cozinha(id, nome) values (1, 'Tailandesa')
insert into cozinha(id, nome) values (2, 'Indiana')
insert into restaurante (nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro, data_cadastro, data_atualizacao) values ('Ong Bak', 2.5, 1, 1, '68902000', 'R Leopoldo Machado', '123', 'Beirol', utc_timestamp, utc_timestamp)
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Thai Delivery', 2.5, 1, utc_timestamp, utc_timestamp)
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('India Express', 2.8, 2, utc_timestamp, utc_timestamp)
insert into produto (descricao, preco, ativo, restaurante_id) values ('Pad Thai', 80, true, 1)
insert into forma_pagamento (descricao) values ('Dinheiro')
insert into forma_pagamento (descricao) values ('Cartão de Débito')
insert into forma_pagamento (descricao) values ('Cartão de Crédito')
insert into forma_pagamento (descricao) values ('PIX')
insert into permissao (nome, descricao) values ('CONSULTAR_PRODUTOS', 'Permite consultar produtos')
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (1, 4), (2, 1),(2, 2), (2, 4), (3, 1), (3, 4)
create table cidade (id bigint not null auto_increment, nome varchar(30) not null, estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table estado (id bigint not null auto_increment, nome varchar(30) not null, primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo_permissoes (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB
create table permissao (id bigint not null auto_increment, descricao varchar(255), nome varchar(30) not null, primary key (id)) engine=InnoDB
create table produto (id bigint not null auto_increment, ativo bit, descricao varchar(255), preco decimal(19,2), restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null auto_increment, data_atualizacao datetime not null, data_cadastro datetime not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(255) not null, taxa_frete decimal(19,2) not null, cozinha_id bigint not null, endereco_cidade_id bigint, primary key (id)) engine=InnoDB
create table restaurante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
create table usuario (id bigint not null auto_increment, data_cadastro datetime(6), email varchar(255) not null, nome varchar(255) not null, senha varchar(255) not null, primary key (id)) engine=InnoDB
create table usuario_grupos (usuario_id bigint not null, permissao_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table grupo_permissoes add constraint FKbjj8fbcfxr7joapufexdn7fv0 foreign key (permissao_id) references permissao (id)
alter table grupo_permissoes add constraint FKd7wt9tnvrfttdcl5ofoelgi6j foreign key (grupo_id) references grupo (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKbc0tm7hnvc96d8e7e2ulb05yw foreign key (endereco_cidade_id) references cidade (id)
alter table restaurante_forma_pagamento add constraint FK7aln770m80358y4olr03hyhh2 foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restaurante_forma_pagamento add constraint FKa30vowfejemkw7whjvr8pryvj foreign key (restaurante_id) references restaurante (id)
alter table usuario_grupos add constraint FK1g23sdkohueaeargext9rxk41 foreign key (permissao_id) references grupo (id)
alter table usuario_grupos add constraint FK158r9y55ufwykh675ddt8kb43 foreign key (usuario_id) references usuario (id)
insert into estado (id, nome) values (1, 'Amapá')
insert into estado (id, nome) values (2, 'Paraná')
insert into estado (id, nome) values (3, 'Santa Catarina')
insert into estado (id, nome) values (4, 'Rio Grande do Sul')
insert into estado (id, nome) values (5, 'Rio Grande do Norte')
insert into estado (id, nome) values (6, 'Tocantins')
insert into cidade (nome, estado_id) values ('Macapá', 1)
insert into cidade (nome, estado_id) values ('Santana', 1)
insert into cidade (nome, estado_id) values ('Curitiba', 2)
insert into cidade (nome, estado_id) values ('São José dos Pinhais', 2)
insert into cidade (nome, estado_id) values ('Pinhais', 2)
insert into cidade (nome, estado_id) values ('Campo Mourão', 2)
insert into cozinha(id, nome) values (1, 'Tailandesa')
insert into cozinha(id, nome) values (2, 'Indiana')
insert into restaurante (nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro, data_cadastro, data_atualizacao) values ('Ong Bak', 2.5, 1, 1, '68902000', 'R Leopoldo Machado', '123', 'Beirol', utc_timestamp, utc_timestamp)
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Thai Delivery', 2.5, 1, utc_timestamp, utc_timestamp)
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('India Express', 2.8, 2, utc_timestamp, utc_timestamp)
insert into produto (descricao, preco, ativo, restaurante_id) values ('Pad Thai', 80, true, 1)
insert into forma_pagamento (descricao) values ('Dinheiro')
insert into forma_pagamento (descricao) values ('Cartão de Débito')
insert into forma_pagamento (descricao) values ('Cartão de Crédito')
insert into forma_pagamento (descricao) values ('PIX')
insert into permissao (nome, descricao) values ('CONSULTAR_PRODUTOS', 'Permite consultar produtos')
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (1, 4), (2, 1),(2, 2), (2, 4), (3, 1), (3, 4)
