package org.example.repositorio;

import org.example.contas.ContaAbstrata;
import org.example.exceptions.CIException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class ContaFile implements IRepositorioContaVector {
    private String nomeArquivo;

    public ContaFile(String nomeArquivo){
        this.nomeArquivo=nomeArquivo;
    }

    @Override
    public void inserir(ContaAbstrata conta)  {
        try{
            FileWriter fw=new FileWriter(nomeArquivo,true);
            fw.write(conta.toString() + "\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void remover(String numero) throws CIException {

    }

    @Override
    public ContaAbstrata procurar(String numero) throws CIException {
        return null;
    }

    @Override
    public Vector<ContaAbstrata> listar() {
        return null;
    }

    @Override
    public int tamanho() {
        return 0;
    }
}
