# PontoCerto

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
