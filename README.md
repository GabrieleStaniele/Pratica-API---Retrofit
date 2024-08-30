# Projeto Kotlin Android - Gerenciador de Animais

Este é um projeto de exemplo de aplicação Android desenvolvida em Kotlin que consome uma API REST para gerenciar registros de animais. A aplicação permite executar operações CRUD (Criar, Ler, Atualizar, Excluir) em um cadastro de animais.

## Funcionalidades

- **GET:** Exibir a lista de animais cadastrados.
- **POST:** Adicionar um novo animal.
- **PUT:** Atualizar as informações de um animal existente.
- **DELETE:** Excluir um animal do cadastro.

## API Utilizada

A aplicação consome a seguinte API REST:

- **Base URL:** `https://6660d8a763e6a0189fe7b380.mockapi.io/animal`

### Endpoints

- **GET /animals** - Lista todos os animais.
- **POST /animals** - Adiciona um novo animal.
- **PUT /animals/{id}** - Atualiza um animal existente pelo ID.
- **DELETE /animals/{id}** - Remove um animal pelo ID.

## Tecnologias Utilizadas

- **Kotlin:** Linguagem de programação principal.
- **Android SDK:** Framework de desenvolvimento para Android.
- **Retrofit:** Biblioteca para consumo de APIs REST.
