package com.portoseguro.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portoseguro.cursomc.domain.Cliente;
import com.portoseguro.cursomc.repositories.ClienteRepository;
import com.portoseguro.cursomc.services.exeptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	public Cliente Buscar(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
			 "Obejeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName())); 
	}
}
