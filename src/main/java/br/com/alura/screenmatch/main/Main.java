package br.com.alura.screenmatch.main;

import br.com.alura.screenmatch.model.Dados;
import br.com.alura.screenmatch.model.Episode;
import br.com.alura.screenmatch.model.SeasonDetail;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.repository.SerieRepository;
import br.com.alura.screenmatch.service.Api;
import br.com.alura.screenmatch.service.ConverterDados;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final ConverterDados conversor = new ConverterDados();
    private final Scanner scanner = new Scanner(System.in);
    private final String API_KEY = "&apikey=6585022c";
    private final Api api = new Api();
    private SerieRepository repository;
    private List<Serie> series = new ArrayList<>();

    public Main(SerieRepository repository) {
        this.repository = repository;
    }

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
                    getSeries();
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
        Serie serie = new Serie(dados);
        //repository.deleteAll();
        repository.save(serie);
        System.out.println("\nSérie " + serie.getTitulo() + ": ");
        System.out.println(serie);
    }

    private Dados getDadosSerie() {
        System.out.println("Digite o nome da série para busca");
        String getEncode = scanner.nextLine();
        String encoded = URLEncoder.encode(getEncode, StandardCharsets.UTF_8);
        var json = api.getApi(ENDERECO + encoded + API_KEY);
        return conversor.obterDados(json, Dados.class);
    }

    private void getEpisodioPorSerie() {
        getSeries();
        System.out.println("Escolha uma série pelo nome: ");
        var nomeSerie = scanner.nextLine();

        Optional<Serie> serie =  series.stream()
                .filter(s -> s.getTitulo().toLowerCase().contains(nomeSerie.toLowerCase()))
                .findFirst();

        if(serie.isPresent()){
            var serieEncontrada = serie.get();
            List<SeasonDetail> temporadas = new ArrayList<>();

            for (int i = 1; i <= serieEncontrada.getTotal_temporadas(); i++) {
                String encoded = URLEncoder.encode(serieEncontrada.getTitulo(), StandardCharsets.UTF_8);
                var json = api.getApi(ENDERECO + encoded + "&season=" + i + API_KEY);
                SeasonDetail dadosTemporada = conversor.obterDados(json, SeasonDetail.class);
                temporadas.add(dadosTemporada);
            }
            temporadas.forEach(System.out::println);

            List<Episode> episodios = temporadas.stream()
                    .flatMap(d -> d.episodios().stream()
                            .map(e -> new Episode(d.temporada(), e)))
                    .collect(Collectors.toList());

            serieEncontrada.setEpisodios(episodios);
            System.out.println(serieEncontrada + "serieEncontrada");
            repository.save(serieEncontrada);
        }else{
            System.out.println("Série não encontrada.");
        }
    }

    private void getSeries() {
        System.out.println("\nSéries buscadas nessa sessão: ");
        series = repository.findAll();
        series.stream()
                .sorted(Comparator.comparing(Serie::getCategorias))
                .forEach(System.out::println);
    }
};