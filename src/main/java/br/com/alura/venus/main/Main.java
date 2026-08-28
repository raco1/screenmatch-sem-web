package br.com.alura.venus.main;

import br.com.alura.venus.model.Dados;
import br.com.alura.venus.model.Episode;
import br.com.alura.venus.model.EpisodeDetail;
import br.com.alura.venus.model.SeasonDetail;
import br.com.alura.venus.service.ConsumoApi;
import br.com.alura.venus.service.ConverterDados;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.Formatter;
import org.springframework.format.datetime.DateFormatter;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);
    private Scanner scanner = new Scanner(System.in);
    private ConsumoApi api = new ConsumoApi();
    private ConverterDados conversor = new ConverterDados();

    public void showMenu() {
        System.out.println("Busque por um série: ");
        String busca = scanner.nextLine();
        String serieEncoded = URLEncoder.encode(busca, StandardCharsets.UTF_8);

        var json = api.obterDadosFilme(serieEncoded);

        Dados data = conversor.obterDados(json, Dados.class);
        System.out.println(data);

        List<SeasonDetail> temporadas = new ArrayList<>();

        for (int i = 1; i <= data.total_temporadas(); i++) {
            json = api.obterDadosTemporada(serieEncoded, i);
            SeasonDetail data_season = conversor.obterDados(json, SeasonDetail.class);
            temporadas.add(data_season);
        }
        //temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

//        List<EpisodeDetail> episodios = temporadas.stream()
//                .flatMap(t -> t.episodios().stream())
//                .collect(Collectors.toList());

//        System.out.println("\nTop 5 episódios da série " + busca + ":");

//        episodios.stream()
//                .filter(e -> !e.rating().equals("N/A"))
//                .sorted(Comparator.comparing(EpisodeDetail::rating).reversed())
//                .limit(5)
//                .forEach(System.out::println);

        List<Episode> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episode(t.temporada(), d)))
                .collect(Collectors.toUnmodifiableList());

        System.out.println("\nTop 5 episódios da série " + busca + ":");
        episodios.stream()
                .filter(e -> !e.getRating().equals("N/A"))
                .sorted(Comparator.comparing(Episode::getRating).reversed())
                .limit(5)
                .forEach(System.out::println);

        System.out.println("Á partir de qual ano você deseja ver os episódios?");
        var ano = scanner.nextInt();
        scanner.nextLine();
        LocalDate dataEp = LocalDate.of(ano, 1, 1);

        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        episodios.stream()
                .filter(e -> e.getData_lancamento() != null && e.getData_lancamento().isAfter(dataEp))
                .forEach(e -> System.out.println(
                        "Nome do Ep: " + e.getTitulo() +
                                ", Temporada: " + e.getTemporada() +
                                ", Episódio: " + e.getNumeroEp() +
                                ", Rating do Ep: " + e.getRating() +
                                ", Data de lançamento: " + e.getData_lancamento().format(formatador)
                ));
        scanner.close();
    }
}
