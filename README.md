# Desafio Livraria Virtual

Aplicação Java de uma livraria virtual que cadastra livros (impressos e eletrônicos) e
registra vendas, persistindo em MySQL via JPA/Hibernate. Desafio do Treinamento Solutis
+ Programar Com Vc.

## Funcionalidades

- Cadastrar livro impresso (`titulo`, `autores`, `editora`, `preco`, `frete`, `estoque`).
- Cadastrar livro eletrônico (`titulo`, `autores`, `editora`, `preco`, `tamanho`).
- Listar livros (todos, só impressos ou só eletrônicos).
- Registrar uma venda informando cliente, valor e os ids dos livros.
- Listar vendas.

Regras implementadas:

- Limites de cadastro no fluxo CLI: até 10 impressos, 20 eletrônicos e 50 vendas.
- Ao vender um livro impresso, o estoque é decrementado em 1; impressos sem estoque são
  ignorados na venda.

## Interfaces de uso

O projeto tem dois pontos de entrada:

- CLI em `br.com.solutis.cli.Main` — menu de texto no terminal.
- Interface gráfica (Swing) em `br.com.solutis.gui.Main`.

## Stack

- Java 21
- Hibernate ORM 7.3.2.Final (JPA, `transaction-type=RESOURCE_LOCAL`)
- MySQL (driver `com.mysql:mysql-connector-j` 9.7.0)
- Swing (GUI)
- Maven

## Estrutura

```
src/main/java/br/com/solutis/
  application/   ApplicationContext (monta EntityManagerFactory e os services)
  cli/           Main, LivrariaVirtual, ScanManager
  gui/           Main, MainFrame, actions/, panels/
  domain/
    entity/      Livro (abstrata), Impresso, Eletronico, Venda
    dto/         livro/ (impresso, eletronico) e venda/
    mapper/      LivroMapper, VendaMapper
    repository/  LivroRepository, VendaRepository
    service/     ImpressoService, EletronicoService, VendaService
    controller/  ImpressoController, EletronicoController, VendaController
src/main/resources/META-INF/persistence.xml
```

Modelo: `Livro` é abstrata com herança JOINED e `@DiscriminatorColumn tipo_livro`;
`Impresso` e `Eletronico` a estendem. `Venda` tem `@OneToMany` de `Livro`.

## Pré-requisitos

- JDK 21
- MySQL acessível. A unidade de persistência usa `hibernate.hbm2ddl.auto=update`, então
  o Hibernate cria/atualiza as tabelas, mas o banco (schema) já precisa existir.

## Configuração do banco

A conexão padrão está em `src/main/resources/META-INF/persistence.xml`. A senha não fica
no arquivo: `ApplicationContext` lê `DB_PASSWORD` do ambiente e, se definidas, `DB_URL` e
`DB_USERNAME` sobrescrevem a URL e o usuário do XML:

```
DB_URL=jdbc:mysql://localhost:3306/bancohiber5?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
DB_USERNAME=java
DB_PASSWORD=sua_senha
```

## Como rodar

Não há wrapper do Maven no repositório; use um Maven instalado.

```
mvn clean compile
```

Rodar a versão CLI (classe `br.com.solutis.cli.Main`) ou a GUI
(`br.com.solutis.gui.Main`), por exemplo via IDE, ou com o plugin exec do Maven:

```
mvn exec:java -Dexec.mainClass=br.com.solutis.cli.Main
mvn exec:java -Dexec.mainClass=br.com.solutis.gui.Main
```

## Testes

O repositório não possui testes automatizados.
