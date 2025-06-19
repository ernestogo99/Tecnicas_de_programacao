# GSON 

Este é um projeto dedicado ao estudo
da biblioteca Gson do java.

# Json

Um arquivo JSON (JavaScript Object Notation) é um formato de armazenamento e troca de dados que usa texto simples para representar estruturas de dados como objetos, listas e valores primitivos.

ex:

```json
{
  "name": "Ernesto",
  "email": "e@gmail.com",
  "age": 23,
  "isDeveloper": true
}
```

# Serialização

É a etapa de transformar um objeto 
java em um json.

passos:
1) importar a biblioteca gson
2) criar um objeto json
3) transformar o objeto em json usando o método toJson()

exemplo:
```java
   private static void serializeUser(){
        User user= new User("e@gmail.com","ernestogo",23,true);
        // criando um json desse objeto
        Gson gson= new Gson();
        String json=gson.toJson(user);
        System.out.println(json);
    }

```


# Desserialização

É a etapa contrária a serialização,
ou seja, vamos converter um json em um objeto java

passos:
1) obter o objeto json em formato string(é obtido nesse formato ao usar o toJson)
2) criar uma instância da classe gson
3) usar o método fromJson(), passando o objeto a ser convertido e a classe a qual queremos que ele se torne



```java
  private static void deserializerUser(){
        String userJson="{'name':'ernestogo','email':'e@gmail.com','age':23,'isDeveloper':true}";
        Gson json= new Gson();
        User user= json.fromJson(userJson,User.class);
        System.out.println(user);
    }
```


para tratar listas e listas de objetos, deveremos
usar o tokentype

```java

    Type foundListType= new TypeToken<ArrayList<User>>(){}.getType();

        List<User> users1= json.fromJson(items,foundListType);
```


# Lidando com valores nulos

Basta inicializar o construtor personalizado do json

```java 


Gson gson = new GsonBuilder().serializeNulls().create();

```


# Ignorando campos

Podemos escolher quais campos farão parte da serialização e da desserialização
basta utilizar o expose e o construtor personalizado
Gsonbuilder().excludeFiledsWhithoutExposeAnnotation().create()

```java
  @Expose()
    private String name;
    @Expose(serialize = true,deserialize = false)
    private String email;
    @Expose()
    private int age;
    @Expose()
    private boolean isDeveloper;
    @Expose()
    private UserAdress userAdress;

```