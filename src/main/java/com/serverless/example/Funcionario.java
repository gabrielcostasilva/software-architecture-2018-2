package com.serverless.example;

public class Funcionario {
    private Long id;
    private String nome;
    private int idade;
    private double salario;

    public Funcionario () {}

    public Funcionario (Long id, String nome, int idade, double salario) {
        this.setId(id);
        this.setIdade(idade);
        this.setNome(nome);
        this.setSalario(salario);
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * @param idade the idade to set
     */
    public void setIdade(int idade) {
        this.idade = idade;
    }
    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    /**
     * @param salario the salario to set
     */
    public void setSalario(double salario) {
        this.salario = salario;
    }
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }
    /**
     * @return the idade
     */
    public int getIdade() {
        return idade;
    }
    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }
    /**
     * @return the salario
     */
    public double getSalario() {
        return salario;
    }


}