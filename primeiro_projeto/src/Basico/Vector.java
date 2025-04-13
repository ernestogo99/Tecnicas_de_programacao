package Basico;

public class Vector {
    public static  void main(String[] args){
       int [] vectorInt={1,2,3,4,5};
       int [] arrayInt= new int[5];
       System.out.println(vectorInt[0]);
       for(int i=0;i<vectorInt.length;i++){
           System.out.println(vectorInt[i]);
       }
       //somente para leitura
       for(int num:vectorInt){
           System.out.println("For each: "+ num);
       }

       for(int i=0;i<arrayInt.length;i++){
           arrayInt[i]=i *2;
       }

       for(int num:arrayInt){
           System.out.println("arrayint "+ num);
       }

    }
}
