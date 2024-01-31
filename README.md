

<h1>Projeto Comunica Cidadão:</h1>
<h2>Contexto:</h2>
O projeto iniciou no Trabalho Interdiciplinar do 4º ano do ens. médio técnico:
Consiste em um sistema para reportar problemas públicos a prefeitura do município. 
<br><br>
O sistema é composto por um aplicativo Mobile, no qual os cidadãos fazem cadastro, login, 
criam chamados para reportar problemas identificados na cidade e visualizam os problemas reportados; 
<br><br>
Um software Desktop também se fez presente, em teoria destinado a prefeitura para o controle dos chamado. 
Nesta aplicação, o usuário visualiza os chamados e controla o status de solucionamento. 
<br><br>
Os dois software's mencionados conectaam-se a um servidor alocado em uma máquina da rede local, ela, por sua vez, 
conecta-se ao banco de dados mySQL para fazer o CRUD. OBS: Nesta arquitetura cliente-servidor utilizou-se a tecnologia Socket para comunicação.

<h2>Arquivos:</h2>
+ "ServidorProjetoComunicaCidadao" -> Servidor Java onde as aplicações Desktop e Mobile se conectam
+ "ClienteProjetoComunicaCidadao" -> Aplicação destinada a prefeitura, para visualização de chamados e alteração de status de solucionamento
+ "ComunicaCidadao" -> Aplicativo Mobile para os usuários relaterem problemas por meio de chamados


 <h2>⚠️ Para rodar:</h2>

+ Importe o banco de dados mySQL disponibilizado;
+ No Aplicativo Mobile, dentro da classe Main é necessário trocar o IP de uma parte do código para o IP de sua máquina;
