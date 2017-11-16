# Exemplo de aplicação web com JSF utilizando outra aplicação EJB e JPA.

Os projetos foram criados no NetBeans, utilizando JSF 2.2 na camada web e EclipseLink (JPA 2.1) para persistência.

## Configurações do banco de dados Oracle:

Execute o seguinte script para criar as sequências, tabelas e inserir alguns registros:

```
CREATE SEQUENCE DOCE_SEQ INCREMENT BY 1 START WITH 1 NOCACHE NOCYCLE;

-- tabela para representar um doce.
CREATE TABLE Doce (
	id         NUMBER(5)     NOT NULL PRIMARY_KEY,
	descricao  VARCHAR2(100) NOT NULL,
	valor      NUMBER(10,2)  NOT NULL
);

CREATE SEQUENCE VENDA_SEQ INCREMENT BY 1 START WITH 1 NOCACHE NOCYCLE;

-- tabela para representar uma venda.
CREATE TABLE Venda (
	id         NUMBER(5)     NOT NULL PRIMARY_KEY,
	data       DATE          NOT NULL,
	valorTotal NUMBER(10,2)  NOT NULL
);

-- tabela para associar os doces que fazem parte de uma venda.
CREATE TABLE Venda_Doce (
	venda_id   NUMBER(5)     NOT NULL,
	doce_id    NUMBER(5)     NOT NULL
);

INSERT INTO Doce (id, descricao, valor) values (DOCE_SEQ.nextval(), 'Cupcake', 3.5);
INSERT INTO Doce (id, descricao, valor) values (DOCE_SEQ.nextval(), 'Mini Pão de Mel', 0.75);
INSERT INTO Doce (id, descricao, valor) values (DOCE_SEQ.nextval(), 'Bolo', 20);
INSERT INTO Doce (id, descricao, valor) values (DOCE_SEQ.nextval(), 'Pão de Mel', 2.5);
```

## Configurando o servidor de aplicações web GlassFish

No GlassFish crie um pool de conexões chamado *jdbc/oracle* para seu banco de dados Oracle.

## Executando as aplicações

Abra ambas as aplicações no NetBeans, construa a aplicação web e publique-a no GlassFish.

A aplicação web quando publicada também publicará internamente a aplicação ejb.

Após publicado acesse a URL: [http://localhost:8080/WebDocesJSF](http://localhost:8080/WebDocesJSF) para acessar a aplicação web:

![Tela de venda de doces](https://github.com/rafaelsakurai/ExemploWebDoces/blob/master/Tela1.png?raw=true)
