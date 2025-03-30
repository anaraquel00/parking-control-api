# 🚗 Parking Control API | Controle de Estacionamento

[![Java](https://img.shields.io/badge/Java-17-%23ED8B00?logo=openjdk)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.0-%236DB33F?logo=spring)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/License-MIT-%2300ADD8)](LICENSE)

API REST para gestão de vagas de estacionamento, desenvolvida com boas práticas de arquitetura e validações robustas. Ideal para demonstração de habilidades em desenvolvimento backend com Spring Boot.

## ⚙️ Funcionalidades

- **CRUD Completo** de registros de veículos estacionados
- **Validações Customizadas** (CPF, placa, email, unicidade de dados)
- **Tratamento de Erros** com respostas padronizadas
- **Documentação Automatizada** via Swagger UI
- **Banco de Dados MySQL** (ambiente de desenvolvimento)
- **Testes Unitários** (JUnit 5 + Mockito)

🔗 **Endpoints Principais**:
POST /api/vagas → Cadastra novo veículo
GET /api/vagas → Lista todos registros
GET /api/vagas/{id} → Busca por ID
PUT /api/vagas/{id} → Atualiza registro
DELETE /api/vagas/{id} → Remove registro

## 🚀 Tecnologias

<div style="display: flex; gap: 10px; flex-wrap: wrap;">
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" width="50" title="Java 17"/>
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg" width="50" title="Spring Boot 3.2"/>
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/hibernate/hibernate-original.svg" width="50" title="Hibernate"/>
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/mysql/mysql-original.svg" width="50" title="MySQL"/>
 </div>

## 📦 Pré-requisitos

- Java 17+
- Maven 3.8+
- Docker (opcional para MySQL)

## ⚡ Executando Localmente

1. **Clone o repositório**:

git clone https://github.com/anaraquel00/parking-control-api.git

# MySQL Config
spring.datasource.url=jdbc:mysql://localhost:3306/parkingcontrol?createDatabaseIfNotExist=true
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Dialeto do Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

mvn spring-boot:run
Acesse a documentação:

http://localhost:8080/parkingspot.html
🧪 Testando a API
Cadastro de Veículo:

curl -X POST 'http://localhost:8080/parkingspot' \
-H 'Content-Type: application/json' \
-d '{
    "nome": "João da Silva Martins",
    "cpf": "12345670908",
    "email": "joaos@email.com",
    "telefone": "11995599991",
    "numVaga": "A122",
    "placaCarro": "ABC1234",
    "horaChegada": "2025-03-30 09:55:00"
}'

🌟 Destaques Técnicos Arquitetura em Camadas: Controller → Service → Repository

DTO Pattern para desacoplamento de entidades

Validações Customizadas com Bean Validation

Tratamento Global de Exceções

Configuração de CORS

📌 Melhorias Futuras
Implementar autenticação JWT

Adicionar relatórios em PDF

Criar frontend em Angular (em estudo)

Deploy em nuvem (AWS/Heroku)

📄 Licença
Este projeto está sob licença MIT - veja o arquivo LICENSE para detalhes.

👩💻 Desenvolvido por Ana Raquel
📂 Portfólio Completo: GitHub Profile

### ✨ App ainda em Desenvolvimento:
Aplicação sujeita à erros por ser iniciante em Spring Framework!
Livre para melhorar a aplicação.


