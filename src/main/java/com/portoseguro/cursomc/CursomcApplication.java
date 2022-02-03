package com.portoseguro.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.portoseguro.cursomc.domain.Categoria;
import com.portoseguro.cursomc.domain.Cidade;
import com.portoseguro.cursomc.domain.Cliente;
import com.portoseguro.cursomc.domain.Endereco;
import com.portoseguro.cursomc.domain.Estado;
import com.portoseguro.cursomc.domain.Produto;
import com.portoseguro.cursomc.domain.enums.TipoCliente;
import com.portoseguro.cursomc.repositories.CategoriaRepository;
import com.portoseguro.cursomc.repositories.CidadeRepository;
import com.portoseguro.cursomc.repositories.ClienteRepository;
import com.portoseguro.cursomc.repositories.EnderecoRepository;
import com.portoseguro.cursomc.repositories.EstadoRepository;
import com.portoseguro.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");	
		
		Produto p1 = new Produto(null,"Computador",2000.00);
		Produto p2 = new Produto(null,"Impressora",800.00);
		Produto p3 = new Produto(null,"mouse",80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null,"Minas gerais");
		Estado est2 = new Estado(null,"São paulo");
		
		Cidade cid1 = new Cidade(null,"Uberlândia", est1);
		Cidade cid2 = new Cidade(null,"São paulo", est2);
		Cidade cid3 = new Cidade(null,"Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(cid1));
		est1.getCidades().addAll(Arrays.asList(cid2,cid3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3));
		
		Cliente cli1 = new Cliente(null,"Maria Silva","Maria@gmail.com","23434234",TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("3333-3333","2222-2222"));

		Endereco e1 = new Endereco(null,"Rua Flores","214","Casa","Jardim","4243444", cli1, cid1);
		Endereco e2 = new Endereco(null,"Avenida Matos","110","Apartamento","Centro","4255554", cli1, cid2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
	}
}








