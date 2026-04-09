<h2 align="center">Gerenciamento de Autores e Obras</h2>

Construa uma api para gerenciamento de autores e obras. Implemente um CRUD para
cadastro de obras relacionados aos autores e também para cadastro de autores.

<h3 align="left">Requisitos:</h3>

● Um autor pode ter várias obras.

● Uma obra pode ter vários autores.
<h3 align="left">Entidades:</h3>
1. Autor:

   ○ Nome (obrigatório)
   ○ Sexo

   ○ E-mail (validado, único)

   ○ Data de nascimento (validada)

   ○ País de origem (obrigatório)

   ○ CPF (obrigatório para autores do Brasil, único)


2. **Obra**:

   ○ Nome (obrigatório)

   ○ Descrição (máximo 240 caracteres)

   ○ Data de publicação ou data de exposição (uma é obrigatória).

<h3 align="left">Extras:</h3>

   ● Implemente autenticação com JWT.   
   ● Desenvolva testes unitários para CRUD das entidades