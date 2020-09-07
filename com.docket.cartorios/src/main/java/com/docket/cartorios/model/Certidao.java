package com.docket.cartorios.model;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "certidaos")
public class Certidao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nome;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cartorio_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Cartorio cartorio;

	
	public Certidao() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Certidao(Long id, String nome, Cartorio cartorio) {
		super();
		this.id = id;
		this.nome = nome;
		this.cartorio = cartorio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Cartorio getCartorio() {
		return cartorio;
	}

	public void setCartorio(Cartorio cartorio) {
		this.cartorio = cartorio;
	}
	
	
	

}
