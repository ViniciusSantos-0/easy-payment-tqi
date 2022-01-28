# easy-payment-tqi

Simulando uma empresa de emprestimos, que denominei por Easy Payment, essa empresa quer um sistema para poder facilitar a vida de seus clientes
na hora de pedir um emprestimo, então elaborei a seguinte solução:

!!Warning projeto imcompleto.

A solução que pensei para o desafio proposto foi de criar microserviços
Api rest, para compor o sistema para assim ficar mais leve o sistema
e com maior tempo de resposta possível.

primeiro dividi o desafio em partes, Autentição/users e gerenciamento de emprestimos, uma api para cada função, que vão compor o sistema

Autenticação: optei por usar as seguintes tecnologias: 
AWS Cognito: Criar Grupos de usuário,
AWS Lambda: Função lambda para novos usuários quando forem se cadastrar poderem ser criados na aws e ter a role de user,
Spring Security e JWT: criptgrafar e gerenciar tokens, (caso o token tiver expirado basta dar um get http://localhost:8080/refresh e pegar o token novo no console do front)
Angular: uma interface para login, cadastrar e listar de forma amigável ao usuário,
DynamoDb: Banco de dados da aws para poder gerenciar os usuários,
Amazon EC2: para dar deploy da api de gerenciamento de usuários.

O usuário poderá se cadastrar e irá receber um email de validação em seu email, para assim poder entrar no sistema.
entrando no sistema o usuário irá ver seus emprestimos e suas informações pessoais.
Usuário ADM irá ver todos os clientes e poderá remover ou editar um usuario comum.

Emprestimo: optei por usar as seguintes tecnologias:
Docker: para criar image do projeto, para rodar o postgres,
Heruku: dar deploy da aplicação.

Emprestimo irá ser respospavel por tratar as requições de emprestimo do usuário.

Para rodar a aplicação precisa ter o angular instalado e o jdk;

comando no front: ng serve -o

irá rodar a aplicação,
o backend roda com o intellij no spring boot na porta 8080 do localhost: http://localhost:8080
caso logar e aparecer esse erro: Http failure response for http://localhost:8080/hello-user: 0 Unknown Error
é por que o backend não foi iniciado.
todos que se inscrever terá o perfil de user, só tem uma conta ADM (não vai precisar da conta adm, pois não consegui implementar a tempo, com o user já dá para ver até onde fui).



