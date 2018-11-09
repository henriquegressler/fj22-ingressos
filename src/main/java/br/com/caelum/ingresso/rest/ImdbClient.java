package br.com.caelum.ingresso.rest;

import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.caelum.ingresso.model.DetalhesDoFilme;
import br.com.caelum.ingresso.model.Filme;

@Component
public class ImdbClient {
	private static final Logger logger = Logger.getLogger(ImdbClient.class);
	
	public Optional<DetalhesDoFilme> request(Filme filme){
		RestTemplate cliente = new RestTemplate();
		String titulo = filme.getNome().replace(" ", "+");
		String url = String.format("https://omdb-fj22.herokuapp.com/movie?title=%s", titulo);
		
		try{
			DetalhesDoFilme detalhesDoFilme = cliente.getForObject(url, DetalhesDoFilme.class);
			return Optional.of(detalhesDoFilme);
		}catch(RestClientException e){
			logger.error(e.getMessage(), e);
			return Optional.empty();
		}
	}
}
