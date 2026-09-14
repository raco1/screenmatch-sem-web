package br.com.alura.screenmatch.main;

import br.com.alura.screenmatch.model.Dados;
import br.com.alura.screenmatch.model.SeasonDetail;
import br.com.alura.screenmatch.service.Api;
import br.com.alura.screenmatch.service.ConverterDados;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final ConverterDados conversor = new ConverterDados();
    private final Scanner scanner = new Scanner(System.in);
    private final String API_KEY = "&apikey=6585022c";
    private final Api api = new Api();
    private List<Dados> seriesListadas = new ArrayList<>();

    public void showMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    1 - Buscar séries
                    2 - Buscar episódios
                    3 - Listar séries buscadas
                    0 - Sair
                    """;

            System.out.println("\n" + menu);
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    getSerie();
                    break;
                case 2:
                    getEpisodioPorSerie();
                    break;
                case 3:
                    getSeriesListadas();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
        ;
    }

    private void getSerie() {
        Dados dados = getDadosSerie();
        seriesListadas.add(dados);
        System.out.println(dados);
    }

    private Dados getDadosSerie() {
        System.out.println("Digite o nome da série para busca");
        String getEncode = scanner.nextLine();
        String encoded = URLEncoder.encode(getEncode, StandardCharsets.UTF_8);
        var json = api.getApi(ENDERECO + encoded + API_KEY);
        return conversor.obterDados(json, Dados.class);
    }

    private void getEpisodioPorSerie() {
        Dados dadosSerie = getDadosSerie();
        String encoded = URLEncoder.encode(dadosSerie.titulo(), StandardCharsets.UTF_8);
        List<SeasonDetail> temporadas = new ArrayList<>();

        for (int i = 1; i <= dadosSerie.total_temporadas(); i++) {
            var json = api.getApi(ENDERECO + encoded + "&season=" + i + API_KEY);
            SeasonDetail dadosTemporada = conversor.obterDados(json, SeasonDetail.class);
            temporadas.add(dadosTemporada);
        }
        temporadas.forEach(System.out::println);
    }

    private void getSeriesListadas() {
        System.out.println("\nSéries buscadas nessa sessão: ");
        seriesListadas.forEach(System.out::println);
    }
};