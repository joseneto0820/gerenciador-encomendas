# Sistema de Gerenciamento de Encomendas

## 1. Sobre o projeto

O Sistema de Gerenciamento de Encomendas foi desenvolvido para atender às necessidades de uma pequena empresa que trabalha com produtos personalizados sob encomenda.

A aplicação tem como objetivo centralizar o gerenciamento de clientes, encomendas, produtos, alterações solicitadas e pagamentos, facilitando o acompanhamento dos pedidos e reduzindo problemas como perda de informações, retrabalho e atrasos.

## 2. Tecnologias utilizadas

- **Java:** linguagem de programação.
- **Spring Boot:** framework para desenvolvimento da aplicação.
- **Spring Data JPA:** gerenciamento da persistência de dados.
- **Hibernate:** implementação da especificação JPA.
- **Maven:** gerenciamento de dependências e build.
- **Lombok:** redução de código repetitivo.
- **Banco de dados relacional:** armazenamento das informações do sistema.

## 3. Arquitetura do projeto

O sistema utiliza uma **arquitetura monolítica modular, organizada por domínio (feature-based), com separação em camadas**.

Essa abordagem mantém toda a aplicação em um único projeto, mas divide sua estrutura em módulos responsáveis por diferentes partes do negócio.

A organização foi escolhida considerando o porte da empresa e a complexidade inicial do sistema. Dessa forma, é possível manter o código organizado, facilitar a manutenção e permitir futuras expansões sem adicionar a complexidade desnecessária de uma arquitetura de microsserviços.

### 3.1 Organização das camadas

O projeto utiliza uma organização por funcionalidade, na qual cada módulo possui suas próprias responsabilidades:

- **Controller:** recebe as requisições HTTP e direciona as operações para a camada de serviço.
- **DTO (Data Transfer Object):** define os objetos utilizados para transportar dados entre as requisições e as respostas da aplicação.
- **Entity:** representa as entidades do domínio e seu mapeamento para o banco de dados.
- **Repository:** responsável pelo acesso e pela persistência dos dados utilizando Spring Data JPA.
- **Service:** concentra a lógica de negócio e coordena as operações de cada módulo.
- **Enums:** define valores fixos utilizados pelo domínio, como status de encomendas e formas de pagamento.

### 3.2 Estrutura de pacotes

A estrutura atual do projeto está organizada da seguinte maneira:

```text
gerenciador-encomendas/
└── src/
    └── main/
        ├── java/
        │   └── br/com/gerenciador/gerenciadorencomendas/
        │       ├── domain/
        │       │   └── enums/
        │       │       ├── FormaPagamento.java
        │       │       └── StatusEncomenda.java
        │       │
        │       ├── internal/
        │       │   ├── cliente/
        │       │   │   ├── controller/
        │       │   │   ├── dto/
        │       │   │   ├── entity/
        │       │   │   ├── repository/
        │       │   │   └── service/
        │       │   │
        │       │   └── encomenda/
        │       │       ├── controller/
        │       │       └── entity/
        │       │
        │       └── GerenciadorEncomendasApplication.java
        │
        └── resources/
            └── application.properties

    └── test/

docs/
└── diagrama-classes.png

pom.xml
README.md
```

*Observação: a estrutura acima representa os pacotes apresentados na organização atual do projeto. Os demais módulos e arquivos podem ser acrescentados conforme o desenvolvimento.*

## 4. Modelagem do domínio

O sistema possui cinco entidades principais:

- **Cliente:** armazena os dados e as informações dos clientes.
- **Encomenda:** representa os pedidos realizados, contendo informações como prazo, valor e status.
- **ItemEncomenda:** representa os produtos incluídos em uma encomenda e suas respectivas personalizações.
- **AlteracaoEncomenda:** registra o histórico de alterações solicitadas durante a execução de uma encomenda.
- **Pagamento:** registra os pagamentos realizados e permite acompanhar os valores pendentes.

Os relacionamentos entre as entidades são definidos utilizando JPA. Os valores fixos relacionados às formas de pagamento e aos status das encomendas são representados por enums.

O diagrama de classes do sistema pode ser encontrado em:

`docs/diagrama-classes.png`

## 5. Escopo da primeira versão

A primeira versão do sistema tem como objetivo disponibilizar funcionalidades para:

- Cadastrar e consultar clientes.
- Registrar e acompanhar encomendas.
- Cadastrar itens e suas personalizações.
- Registrar alterações solicitadas pelos clientes.
- Acompanhar prazos e status dos pedidos.
- Registrar pagamentos e consultar valores restantes.

Funcionalidades como controle de estoque, gerenciamento de fornecedores, integração com redes sociais, controle de permissões de funcionários e relatórios financeiros avançados não fazem parte do escopo inicial.

## 6. Como executar o projeto

### Pré-requisitos

- JDK compatível com a versão configurada no projeto.
- Maven (ou utilização do Maven Wrapper).

### Compilação

Na raiz do projeto, execute:

**Windows (PowerShell):**

```powershell
.\mvnw.cmd clean compile
```

**Linux/macOS:**

```bash
./mvnw clean compile
```

A execução da aplicação pode ser realizada pela classe principal:

`GerenciadorEncomendasApplication.java`

## 7. Equipe de desenvolvimento

- José Neto
- Luciano Braga
- Thyago Câmara
- Luiz Gabriel
