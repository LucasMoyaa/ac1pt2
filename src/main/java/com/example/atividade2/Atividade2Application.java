package com.example.atividade2;

import com.example.atividade2.model.Diretor;
import com.example.atividade2.model.Filme;
import com.example.atividade2.repository.DiretorRepository;
import com.example.atividade2.repository.FilmeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class Atividade2Application {

    public static void main(String[] args) {
        SpringApplication.run(Atividade2Application.class, args);
    }

    @Bean
    CommandLineRunner init(FilmeRepository filmeRepository, DiretorRepository diretorRepository) {
        return args -> {
            // diretores
            Diretor d1 = new Diretor(null, "Steven Spielberg", null);
            Diretor d2 = new Diretor(null, "Christopher Nolan", null);

            //  filmes
            Filme f1 = new Filme(null, "Jurassic Park", 127, d1);
            Filme f2 = new Filme(null, "E.T.", 115, d1);
            Filme f3 = new Filme(null, "Inception", 148, d2);

            d1.setFilmes(Arrays.asList(f1, f2));
            d2.setFilmes(Arrays.asList(f3));

            diretorRepository.saveAll(Arrays.asList(d1, d2));

            // FilmeRepository
            System.out.println("\n--- Filmes com duração > 120 ---");
            filmeRepository.findByDuracaoGreaterThan(120).forEach(System.out::println);

            System.out.println("\n--- Filmes com duração <= 120 ---");
            filmeRepository.findByDuracaoLessThanEqual(120).forEach(System.out::println);

            System.out.println("\n--- Filmes cujo título começa com 'J' ---");
            filmeRepository.findByTituloStartingWith("J").forEach(System.out::println);

            // DiretorRepository
            System.out.println("\n--- Diretores cujo nome começa com 'C' ---");
            diretorRepository.findByNomeStartingWith("C").forEach(System.out::println);

            System.out.println("\n--- Diretor com filmes (Nolan) ---");
            Diretor diretorComFilmes = diretorRepository.findByIdWithFilmes(d2.getId());
            System.out.println("Diretor: " + diretorComFilmes.getNome());
            diretorComFilmes.getFilmes().forEach(f -> System.out.println(" - " + f.getTitulo()));
        };
    }
}
