const listarAcoesCliente = (idCliente) => {
    return [{
        codigo: 'asdasd',
        precoDeCompra: '12',
        empresa: {
            codigo: '123'
        }
    },
    {
        codigo: 'asdasd2',
        precoDeCompra: '13',
        empresa: {
            codigo: '123'
        }
    },
    {
        codigo: 'asdasd2',
        precoDeCompra: '15',
        empresa: {
            codigo: '123'
        }
    }];
};

const comprarAcao = (
    codigoEmpresaCompra,
    maxPagar,
    minCompra,
    idCliente,
    qtdCompra) => {
    return true;
};

const venderAcao = (
    codigoAcaoVenda,
    minReceber,
    minVenda,
    idCliente,
    qtdVenda
) => {
    return true;
};

const insertCotacao = (valorCotacao) => {
    return true;
};

exports.listarAcoesCliente = listarAcoesCliente;
exports.comprarAcao = comprarAcao;
exports.venderAcao = venderAcao;
exports.insertCotacao = insertCotacao;
