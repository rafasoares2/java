package com.acc.bank.constantes;

public enum Mensagem {

	
	SEMSALDO("Saldo insuficiente"),
	MESMACONTA("Impossivel transferir para a mesma conta"),
	DADOSN("Dados incorretos! Tente novamente"),
	VNEGATIVO("Não é permitido valor negativo"),
	TRSCSUCESSO("Transação realizada com sucesso"),
	
	CLTNENC("Cliente não encontrado"),
	CLTATLZD("Dados atualizados com sucesso"),
	
	CNTN("Conta não encontrada");
	
	
	
	
	private String descricao;
	
	Mensagem(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
