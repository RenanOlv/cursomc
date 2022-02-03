package com.portoseguro.cursomc;

import java.text.SimpleDateFormat;
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
import com.portoseguro.cursomc.domain.Pagamento;
import com.portoseguro.cursomc.domain.PagamentoComBoleto;
import com.portoseguro.cursomc.domain.PagamentoComCartao;
import com.portoseguro.cursomc.domain.Pedido;
import com.portoseguro.cursomc.domain.Produto;
import com.portoseguro.cursomc.domain.enums.EstadoPagamento;
import com.portoseguro.cursomc.domain.enums.TipoCliente;
import com.portoseguro.cursomc.repositories.CategoriaRepository;
import com.portoseguro.cursomc.repositories.CidadeRepository;
import com.portoseguro.cursomc.repositories.ClienteRepository;
import com.portoseguro.cursomc.repositories.EnderecoRepository;
import com.portoseguro.cursomc.repositories.EstadoRepository;
import com.portoseguro.cursomc.repositories.PagamentoRepository;
import com.portoseguro.cursomc.repositories.PedidoRepository;
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
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
	
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2,sdf.parse("20/10/2017 00:00"),null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		
	}
}








