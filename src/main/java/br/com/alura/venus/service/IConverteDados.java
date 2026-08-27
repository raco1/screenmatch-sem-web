package br.com.alura.venus.service;


public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}
