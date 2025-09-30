package br.edu.ada.cache;

import io.quarkus.cache.CacheKeyGenerator;
import io.quarkus.logging.Log;

import java.lang.reflect.Method;

public class SimplesKeyGeneratorNull implements CacheKeyGenerator {

    @Override
    public Object generate(Method method, Object... methodParams) {
        String nomeDoMetodo = method.getName();

        Object page = methodParams.length > 0 ? methodParams[0] : "null";
        Object pageSize = methodParams.length > 1 ? methodParams[1] : "null";

        String minhaKey = String.format("%s.%s.%s", nomeDoMetodo, page, pageSize);
        Log.info("Gerando minha key customizada: " + minhaKey);
        return minhaKey;

    }
}
