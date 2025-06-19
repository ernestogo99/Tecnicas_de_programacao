package org.example.repositorio;

import java.util.Vector;

import org.example.contas.ContaAbstrata;
import org.example.exceptions.CIException;

public interface IRepositorioContaVector {
	public void inserir(ContaAbstrata conta);
	public void remover(String numero) throws CIException;
	public ContaAbstrata procurar(String numero) throws CIException;
	public Vector<ContaAbstrata> listar();
	public int tamanho();
}
