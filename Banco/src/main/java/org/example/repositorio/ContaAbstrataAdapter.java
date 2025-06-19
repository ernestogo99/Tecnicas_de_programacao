package org.example.repositorio;

import com.google.gson.*;
import org.example.contas.ContaAbstrata;
import org.example.contas.Poupanca;
import org.example.contas.ContaEspecial;
import org.example.contas.ContaImposto;

import java.lang.reflect.Type;

public class ContaAbstrataAdapter implements JsonSerializer<ContaAbstrata>, JsonDeserializer<ContaAbstrata> {

    @Override
    public JsonElement serialize(ContaAbstrata conta, Type type, JsonSerializationContext context) {
        JsonObject objetoJson = context.serialize(conta).getAsJsonObject();
        objetoJson.addProperty("tipo", conta.getClass().getSimpleName());
        return objetoJson;
    }

    @Override
    public ContaAbstrata deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject objetoJson = json.getAsJsonObject();
        String tipo = objetoJson.get("tipo").getAsString();

        Class<? extends ContaAbstrata> classe;

        switch (tipo) {
            case "Poupanca":
                classe = Poupanca.class;
                break;
            case "ContaEspecial":
                classe = ContaEspecial.class;
                break;
            case "ContaImposto":
                classe = ContaImposto.class;
                break;
            default:
                throw new JsonParseException("Tipo de conta desconhecido: " + tipo);
        }

        return context.deserialize(json, classe);
    }
}
