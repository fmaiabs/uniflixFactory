package br.unifor.uniflix.controller;

import org.json.JSONObject;

import br.unifor.uniflix.model.Filme;

public class filmeFactory {

	Filme filme = new Filme();
	public filmeFactory(JSONObject novofilme) {
		
		
	    this.filme.setTitulo(novofilme.getString("title"));
	    this.filme.setSinopse(novofilme.getString("overview"));
	    this.filme.setAdulto(novofilme.getBoolean("adult"));
	    this.filme.setNota(novofilme.getDouble("vote_average"));
	    //filmes.add(filme);
	    
	}


}

