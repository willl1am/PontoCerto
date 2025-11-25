# PontoCerto

## Como Rodar o Projeto

### Backend (Spring Boot)

1. Abra o terminal (PowerShell ou Git Bash)
2. Navegue até a pasta do BackEnd:
   ```bash
   cd "/PontoCerto/BackEnd/Bus/Bus"
   ```
3. Execute o comando Maven para rodar a aplicação:
   ```PowerShell
   mvn spring-boot:run
   ```
4. A aplicação iniciará na porta **8080**: `http://localhost:8080`

### Frontend

1. Abra um novo terminal
2. Navegue até a pasta Frontend:
   ```bash
   cd "/PontoCerto/Frontend"
   ```
3. Instale as dependências do npm:

   ```bash
   npm i
   ```

4. Abra o arquivo `index.html` no seu navegador.

## Arquitetura de Pastas Frontend

```
Frontend/              <- Raiz do seu Frontend Estático
├── src/
│   ├── css/               <- Arquivos CSS compilados (incluindo o Bootstrap)
│   │   ├── main.css
│   │   └── vendor/
│   │       └── bootstrap.min.css
│   ├── js/                <- Arquivos JavaScript
│   │   ├── main.js        <- Seu código JavaScript
│   │   └── vendor/
│   │       ├── jquery.min.js
│   │       └── bootstrap.min.js
│   ├── sass/              <- Arquivos SASS/SCSS (Opcional, se você compilar antes)
│   │   └── main.scss
│   ├── img/               <- Imagens
│   └── views/             <- Outras páginas HTML (contato, sobre, etc.)
├── index.html         <- A página de entrada da sua aplicação
└── application.properties (ou application.yml) <- Configurações do Spring
```

## Descrição dos Diretórios

- **static/** - Raiz do seu Frontend Estático
- **css/** - Arquivos CSS compilados (incluindo o Bootstrap)
- **js/** - Arquivos JavaScript da aplicação
- **sass/** - Arquivos SASS/SCSS para pré-processamento (Opcional)
- **img/** - Imagens utilizadas no projeto
- **views/** - Outras páginas HTML (contato, sobre, etc.)
