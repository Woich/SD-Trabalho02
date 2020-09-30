const auth = require('./service/authService.js');

const prompt = require('prompt-sync')();

var escolha = '0';

var idCliente = auth.login();

while (escolha !== '-1') {
    console.log('O que deseja fazer?\n\n');

    console.log('1 - Inserir uma nova empresa');
    console.log('2 - Listar minhas ações');
    console.log('3 - Registrar ordem de compra de ação');
    console.log('4 - Registrar ordem de venda de ação');
    console.log('5 - Registrar interesse em notificações');
    console.log('6 - Remover interesse em notificações');
    console.log('7 - Listar Interesses');
    console.log('8 - Registrar aumento geral de preço');
    console.log('9 - Registrar diminuição geral de preço');
    console.log('10 - Mostrar lista de Empresas');
    console.log('11 - Listar Notificações');
    console.log('-1 - Sair');

    escolha = prompt();
    switch (escolha) {
    case '1':
        var nomeEmpresa = prompt('Digite o nome da empresa:\n');
        var qteAcoes = prompt('Digite o numero de acoes da empresa\n');
        console.log(nomeEmpresa);
        break;

    case '2':
        break;
    case '3':
        break;
    case '4':
        break;
    case '5':
        break;
    case '6':
        break;
    case '7':
        break;
    case '8':
        break;
    case '9':
        break;
    case '10':
        break;
    case '11':
        break;
    default:
        break;
    }
}
