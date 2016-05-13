
function excluirBackupBanco(arquivo){
    decisao = confirm("Você perderá todas as informações deste arquivo. Deseja realmente excluir este Backup?");
    if (decisao){
        window.location = "bancoBackupDelete.do?arquivo="+arquivo;
    }
}

function excluirUsuario(id,temPlano){
    var msg = "";
    if(temPlano){
        msg = "A exclusão implicará no cancelamento do plano vinculado\n";
    }
    msg += "Deseja realmente excluir este usuário?";
    decisao = confirm(msg);
    if (decisao){
        window.location = "usuarioDelete.do?id="+id+"&temPlano="+temPlano;
    }
}

function excluirTipoUsuario(id){
    decisao = confirm("Deseja realmente excluir este tipo de usuário?");
    if (decisao){
        window.location = "tipoUsuarioDelete.do?id="+id;
    }
}

function excluirCategoria(id){
    decisao = confirm("Esta categoria será desassociada dos produtos\nDeseja realmente excluir esta categoria?");
    if (decisao){
        window.location = "categoriaDelete.do?id="+id;
    }
}

function excluirFornecedor(id){
    decisao = confirm("Deseja realmente excluir este fornecedor?");
    if (decisao){
        window.location = "fornecedorDelete.do?id="+id;
    }
}

function excluirFuncionario(id){
    decisao = confirm("Deseja realmente excluir este funcionário?");
    if (decisao){
        window.location = "funcionarioDelete.do?id="+id;
    }
}

function excluirRegistroCaixa(id){
    decisao = confirm("Deseja realmente excluir registro do caixa?");
    if (decisao){
        window.location = "registroCaixaDelete.do?id="+id;
    }
}

function excluirTipoUsuario(id){
    decisao = confirm("Deseja realmente excluir este tipo de usuário?");
    if (decisao){
        window.location = "tipoUsuarioDelete.do?id="+id;
    }
}

function excluirDispositivo(id){
    decisao = confirm("Deseja realmente excluir esta catraca?");
    if (decisao){
        window.location = "dispositivoDelete.do?id="+id;
    }
}

function excluirPerfilAcesso(id){
    decisao = confirm("Deseja realmente excluir este perfil de acesso?");
    if (decisao){
        window.location = "perfilAcessoDelete.do?id="+id;
    }
}

function excluirModalidade(id){
    decisao = confirm("Deseja realmente excluir esta modalidade?\nExcluir pode alterar planos com estas modalidade.");
    if (decisao){
        window.location = "modalidadeDelete.do?id="+id;
    }
}

function excluirPlano(id){
    decisao = confirm("Deseja realmente excluir este Plano?\nExcluir pode afetar planos de alunos vinculados a este.");
    if (decisao){
        window.location = "planoDelete.do?id="+id;
    }
}

function excluirLiberar(id){
    decisao = confirm("Deseja realmente excluir esta liberação?");
    if (decisao){
        window.location = "liberarDelete.do?id="+id;
    }
}

function excluirFicha(idUsuario,id){
    decisao = confirm("Deseja realmente excluir esta ficha?");
    if (decisao){
        window.location = "fichaDelete.do?id="+id+"&idUsuario="+idUsuario;
    }
}

function excluirExercicio(id){
    decisao = confirm("Deseja realmente excluir este exercício?");
    if (decisao){
        window.location = "exercicioDelete.do?id="+id;
    }
}

function excluirAvaliacaoFisica(idUsuario,id){
    decisao = confirm("Deseja realmente excluir esta avaliação?");
    if (decisao){
        window.location = "avaliacaoFisicaDelete.do?id="+id+"&idUsuario="+idUsuario;
    }
}

function excluirProduto(id){
    decisao = confirm("Deseja realmente excluir este produto?");
    if (decisao){
        window.location = "produtoDelete.do?id="+id;
    }
}

function excluirContaBancaria(id){
    decisao = confirm("Deseja realmente excluir esta conta bancária?");
    if (decisao){
        window.location = "contaBancariaDelete.do?id="+id;
    }
}

