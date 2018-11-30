package com.serverless.example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

public class Function {

    @FunctionName ("funcaocriarfuncionario")
    public Funcionario criar (@HttpTrigger (name = "restcriarfuncionario", methods = {HttpMethod.POST}, route = "funcionario") Funcionario f) {
        f.setId(new Long(1));

        // Conjunto operações business

        return f;
    }

    @FunctionName ("funcaolerfuncionario")
    public List<Funcionario> ler(@HttpTrigger (name = "restlerfuncionario", methods = {HttpMethod.GET}, route = "funcionario") String x) {

        return Stream.of(
            new Funcionario(null, "John Connor", 14, 10.0),
            new Funcionario(null, "Sara Connor", 27, 300.0),
            new Funcionario(null, "Arnold Schwasdkjlfhuyadtu", 38, 1000.0)
        ).collect(Collectors.toList());
    }

    @FunctionName ("funcaoalterarfuncionario")
    public Funcionario alterar (
        @HttpTrigger (
            name = "restalterarfuncionario",
            methods = {HttpMethod.PUT},
            route = "funcionario"
        )
        Funcionario f) {
        f.setIdade(f.getIdade() + 10);

        return f;
    }

    @FunctionName ("funcaoapagarfuncionario")
    public int apagar (
        @HttpTrigger (
            name = "restapagarfuncionario",
            methods = {HttpMethod.DELETE},
            route = "funcionario/{id}"
        )
        @BindingName ("id") Long id) {
        System.out.println(String.format("Id: %d", id));

        return 200;
   }
}
