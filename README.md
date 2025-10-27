# Balança Inteligente API – Projeto BovinoScan (Agrotins 2025)

**Resumo:**  
A **Balança Inteligente API** é o backend desenvolvido para o projeto **BovinoScan**, apresentado na **Feira Tecnológica Agrotins 2025**.  
O sistema tem como objetivo coletar, registrar e gerenciar **pesagens de bovinos via hardware conectado com RFID e célula de carga**, integrando os dados a um banco **PostgreSQL** em tempo real.

---

## 🧠 Contexto do Projeto

O **BovinoScan** nasceu como uma solução tecnológica voltada à pecuária de precisão, permitindo a **automação da pesagem de bovinos** e o armazenamento centralizado das informações.  
A balança inteligente foi desenvolvida com componentes como:

- **Arduino Mega 2560**
    
- **PN5180-NFC** (leitor RFID)
    
- **HX711** (módulo conversor da célula de carga)
    
- **NodeMCU ESP8266** (comunicação Wi-Fi)
    

Esses dispositivos se comunicam com a **API Quarkus**, que processa e registra os dados de pesagem no banco de dados.

---

## ⚙️ Stack Tecnológica

|Categoria|Tecnologia / Ferramenta|
|---|---|
|Linguagem|**Java 21**|
|Framework|**Quarkus 3.21.3**|
|ORM|**Hibernate ORM Panache**|
|Banco de Dados|**PostgreSQL**|
|Validação|**Jakarta Validation / Hibernate Validator**|
|Documentação|**SmallRye OpenAPI (Swagger UI)**|
|Dependências extras|RESTEasy, Jackson, CORS habilitado|
|Build Tool|**Maven**|

---

## 🧩 Arquitetura da Aplicação

A aplicação segue o padrão **RESTful**, com entidades principais:

- **Animal** → Representa cada bovino identificado via RFID.
    
- **Pesagem** → Registra a pesagem individual, com data, peso e vínculo ao animal.
    

### Endpoints principais

|Método|Endpoint|Descrição|
|---|---|---|
|`GET`|`/animal`|Lista todos os animais registrados.|
|`POST`|`/animal`|Cadastra novo animal.|
|`GET`|`/pesagem`|Lista todas as pesagens.|
|`POST`|`/pesagem`|Registra uma nova pesagem.|
|`GET`|`/pesagem/{id}`|Retorna pesagem específica.|

---

## 🧪 Execução

### 🔧 Pré-requisitos

- **Java 21+**
    
- **Maven 3.9+**
    
- **Banco PostgreSQL** instalado e configurado localmente
    

### 🚀 Rodar a aplicação

```java
// Rodar em modo desenvolvimento 
mvn quarkus:dev  

// Acessar documentação (Swagger) 
http://localhost:8080/q/swagger-ui
```

---

## 💡 Funcionalidades

- Registro automático de pesagens recebidas via ESP8266.
    
- Associação entre animal (RFID) e pesagem.
    
- Persistência automática no banco PostgreSQL.
    
- API documentada via **Swagger UI**.
    
- CORS habilitado para integração com frontend ou dashboard.
    

---

## 👨‍💻 Equipe de Desenvolvimento

**Integrantes:**  
Higor Valuar Bailona, Iara Martins Ferro e Fabriny Custódio

---

## 📅 Status do Projeto

- ✅ MVP funcional apresentado na **Agrotins 2025**
    
- ⚙️ Versão de desenvolvimento para integração com dashboard web
    

---

## 📘 Licença

Este projeto foi desenvolvido para fins **acadêmicos e de pesquisa**, sem fins comerciais.  
© 2025 – **Equipe BovinoScan / Unitins**
