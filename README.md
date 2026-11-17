# Sistema de Gerenciamento de Encomendas

## 1. Sobre o projeto

O sistema foi pensado para uma pequena empresa que trabalha com produtos personalizados sob encomenda. Ele reúne os dados de clientes, pedidos, alterações e pagamentos, facilitando o acompanhamento do trabalho e ajudando a evitar informações perdidas, retrabalho e atrasos.

## 2. Tecnologias utilizadas

- Java e Spring Boot
- Spring Data JPA e Hibernate
- Maven e Lombok
- Banco de dados relacional

## 3. Arquitetura

O projeto utiliza uma **arquitetura monolítica em camadas**: toda a aplicação fica em um único projeto, com o código dividido por responsabilidade.

Essa escolha atende ao porte da empresa e à complexidade inicial do sistema. Como a operação é pequena, uma arquitetura distribuída, como microsserviços, traria um esforço de desenvolvimento e manutenção que não se justifica neste momento.

As camadas foram organizadas da seguinte forma:

- **Domain:** contém as entidades, os relacionamentos e as regras de negócio ligadas a elas.
- **Repository:** cuida do acesso e da persistência dos dados com Spring Data JPA.
- **Service:** implementa os casos de uso e coordena as operações do sistema.
- **Controller:** recebe as requisições HTTP, chama os serviços e retorna as respostas.

Nesta etapa, o foco é a implementação das entidades do domínio. As demais camadas serão desenvolvidas nas próximas etapas.

### Estrutura do projeto

```text
gerenciador-encomendas/
├── src/main/
│   ├── java/br/com/gerenciador/gerenciadorencomendas/
│   │   ├── domain/
│   │   │   ├── entity/
│   │   │   └── enums/
│   │   ├── repository/
│   │   ├── service/
│   │   ├── controller/
│   │   └── GerenciadorEncomendasApplication.java
│   └── resources/
├── src/test/
├── docs/diagrama-classes.png
├── pom.xml
└── README.md
```

## 4. Modelagem do domínio

O sistema possui cinco entidades principais:

- **Cliente:** guarda os dados dos clientes.
- **Encomenda:** registra o pedido, seu prazo, valor e status.
- **ItemEncomenda:** representa os produtos e suas personalizações.
- **AlteracaoEncomenda:** mantém o histórico das mudanças solicitadas.
- **Pagamento:** registra os valores pagos e permite acompanhar o saldo restante.

Os relacionamentos são mapeados com JPA. Os status das encomendas e as formas de pagamento são definidos por enums.

O diagrama de classes está em `docs/diagrama-classes.png`.

## 5. Escopo da primeira versão

A primeira versão permitirá cadastrar clientes e encomendas, registrar produtos e personalizações, consultar alterações, acompanhar prazos e status e controlar pagamentos.

Controle de estoque, fornecedores, integração com redes sociais, permissões de funcionários e relatórios financeiros avançados ficam fora do escopo inicial.

## 6. Como executar

É necessário ter o JDK compatível com a versão do projeto. Para compilar, use o Maven Wrapper:

```bash
./mvnw clean compile
```

No Windows PowerShell:

```powershell
.\mvnw.cmd clean compile
```

## 7. Equipe

- José Neto
- Luciano Braga
- Thyago Câmara
- Luiz Gabriel
