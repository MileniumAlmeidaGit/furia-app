# FURIA App

Aplicação full-stack para a FURIA, integrando back-end, front-end e módulo de IA.

## Visão Geral

Este projeto é uma solução completa para o hub da FURIA, permitindo que os usuários se cadastrem, façam login, selecionem jogos, acessem um feed social e interajam via chat.  

### Tecnologias Utilizadas

- **Back-end:**  
  - Spring Boot (REST API)
  - H2 (Banco de Dados, para desenvolvimento)  
  - Spring Security (configurado para ambiente de testes)

- **Front-end:**  
  - React (utilizando Create React App)
  - React Router para navegação entre as páginas
  - Axios para comunicação com a API

- **Módulo de IA (Opcional):**  
  - (Exemplo) Microserviço em Python com FastAPI para análise de sentimento e validação de cadastro  
  - Integração com bibliotecas como HuggingFace ou spaCy para gerar um "fan score" baseado nas interações

- **Integração com Redes Sociais:**  
  - APIs do Twitter e Instagram (ou respostas dummy) para coletar menções e interações

## Fluxo Simplificado

1. **Cadastro/Login:** O usuário se cadastra/faz login usando e-mail, redes sociais ou SSO.
2. **Seleção de Jogos:** O usuário escolhe os jogos/partidas que quer acompanhar (ex.: próximos confrontos da FURIA).
3. **Feed Social:** Mostra notificações personalizadas, menções das redes sociais e interações.
4. **Chat:** Funcionalidade de chat onde o usuário pode fazer perguntas e receber respostas automatizadas.
5. **Módulo de IA (Opcional):** Analisa as interações, gera um "fan score" e valida os cadastros.



