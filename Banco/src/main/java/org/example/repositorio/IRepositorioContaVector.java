package org.example.repositorio;

import java.util.Vector;

import org.example.contas.ContaAbstrata;

public interface IRepositorioContaVector {
	public void inserir(ContaAbstrata conta);
	public void remover(String numero);
	public ContaAbstrata procurar(String numero);
	public Vector<ContaAbstrata> listar();
	public int tamanho();
}
