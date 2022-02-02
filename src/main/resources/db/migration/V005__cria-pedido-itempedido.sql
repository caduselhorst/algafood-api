create table pedido
(
    id bigint not null auto_increment,
    subtotal decimal(10,2) not null,
    taxa_frete decimal(10,2) not null,
    valor_total decimal(10,2) not null,
    data_criacao datetime not null,
    data_confirmacao datetime,
    data_cancelamento datetime,
    data_entrega datetime,
    cliente_id bigint not null,
    restaurante_id bigint not null,
    forma_pagamento_id bigint not null,
    endereco_cidade_id bigint,
	endereco_cep varchar(9),
	endereco_logradouro varchar(100),
	endereco_numero varchar(20),
	endereco_complemento varchar(60),
	endereco_bairro varchar(60),
    status varchar(10) not null,
    primary key(id),
    foreign key fk_pedido_usuario (cliente_id) references usuario(id),
    foreign key fk_pedido_restaurante (restaurante_id) references restaurante(id),
    foreign key fk_pedido_end_cidade (endereco_cidade_id) references cidade(id),
    foreign key fk_pedido_forma_pagamento (forma_pagamento_id) references forma_pagamento (id)
) engine=InnoDB default charset=utf8;

create table item_pedido (
	id bigint not null auto_increment,
    quantidade integer not null,
    preco_unitario decimal(10,2) not null,
    preco_total decimal(10,2) not null,
    observacao varchar(255),
    produto_id bigint not null,
    pedido_id bigint not null,
    primary key (id),
    foreign key fk_itempedido_produto (produto_id) references produto(id),
    foreign key fk_itempedido_pedido (pedido_id) references pedido (id)
) engine=InnoDB default charset=utf8;