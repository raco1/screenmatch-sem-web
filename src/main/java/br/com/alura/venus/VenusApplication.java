package br.com.alura.venus;

import br.com.alura.venus.model.Dados;
import br.com.alura.venus.service.ConsumoApi;
import br.com.alura.venus.service.ConverterDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@SpringBootApplication
public class VenusApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(VenusApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Busque por um filme/série: ");
		String busca = scanner.nextLine();
		String buscaEncoded = URLEncoder.encode(busca, StandardCharsets.UTF_8);

		ConsumoApi api = new ConsumoApi();
		var json = api.obterDados(buscaEncoded);

		ConverterDados conversor = new ConverterDados();
		Dados data = conversor.obterDados(json, Dados.class);
		System.out.println(data);
		scanner.close();
	}
}
