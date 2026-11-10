package org.example.utils.optinal;

import java.util.Optional;

public class MyOptional {
    public static void main(String[] args) {
        Optional<MyOptionalUser> user=Optional.of(new MyOptionalUser("Ernesto", 30, SexEnum.MAN));
        user.ifPresent(u -> System.out.println("Usuário encontrado: " + u.name() + ", Idade: " + u.age() + ", Sexo: " + u.sex().getValue()));
        System.out.println(user.get());

        user.ifPresentOrElse(
                u -> System.out.println("Usuário encontrado: " + u.name() + ", Idade: " + u.age() + ", Sexo: " + u.sex().getValue()),
                () -> System.out.println("Usuário não encontrado")
        );

        System.out.println(user.orElse(new MyOptionalUser("Usuário padrão", 0, SexEnum.MAN)));
        System.out.println(user.orElseThrow(() -> new RuntimeException("Usuário não encontrado")));
        var userName = user.map(u -> u.name().toUpperCase())
                .orElse("Nome não disponível");
        System.out.println("Nome em maiúsculas: " + userName);
        Optional<String> optional=Optional.of("java");
        String result= optional.orElse("default");
        System.out.println("Resultado: " + result);

    }
}
