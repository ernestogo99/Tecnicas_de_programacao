package org.example.repositorio;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.contas.ContaAbstrata;
import org.example.exceptions.CIException;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Vector;

public class ContaFile implements IRepositorioContaVector {
    private String nomeArquivo;
    private final Gson json;
    private final Type typeList=new TypeToken<Vector<ContaAbstrata>>(){}.getType();

    public ContaFile(String nomeArquivo){
        this.nomeArquivo=nomeArquivo;
        this.json=new GsonBuilder().setPrettyPrinting().registerTypeAdapter(ContaAbstrata.class,new ContaAbstrataAdapter()).create();
    }

    @Override
    public void inserir(ContaAbstrata conta)   {
        Vector<ContaAbstrata> contas =this.listar();
        contas.add(conta);
        this.Save(contas);
    }

    @Override
    public void remover(String numero) throws CIException {
        boolean removed=false;
        Vector<ContaAbstrata> contas=this.listar();
        for(ContaAbstrata conta:contas){
            if(conta.numero().equals(numero)){
                contas.remove(conta);
                removed=true;
                break;
            }
        }
        if (!removed) {
            throw new CIException("Conta com número " + numero + " não encontrada.");
        }

        this.Save(contas);
    }

    @Override
    public ContaAbstrata procurar(String numero) throws CIException {
        Vector<ContaAbstrata> contas=this.listar();
        for (ContaAbstrata conta:contas){
            if(conta.numero().equals(numero)){
                return  conta;
            }
        }
        throw new CIException("Conta com número " + numero + " não encontrada.");
    }

    @Override
    public Vector<ContaAbstrata> listar() {
        BufferedReader reader=null;
        try{
            FileReader fileReader=new FileReader(this.nomeArquivo);
            reader=new BufferedReader(fileReader);
            Vector<ContaAbstrata> contas=json.fromJson(reader,this.typeList);
            if (contas !=null){
                return  contas;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            try{
                if(reader!=null){
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();

            }

        }
        return new Vector<ContaAbstrata>();
    }


    public void Save(Vector<ContaAbstrata> contas){
        BufferedWriter writer=null;
        try{
            FileWriter fileWriter=new FileWriter(this.nomeArquivo);
            writer= new BufferedWriter(fileWriter);
            json.toJson(contas,this.typeList,writer);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                if (writer!=null){
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int tamanho() {
        Vector<ContaAbstrata> contas=listar();
        return  contas.size();
    }


}
