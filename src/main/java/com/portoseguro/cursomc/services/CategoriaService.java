package com.portoseguro.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portoseguro.cursomc.domain.Categoria;
import com.portoseguro.cursomc.repositories.CategoriaRepository;
import com.portoseguro.cursomc.services.exeptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
			 "Obejeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName())); 
	}
}
