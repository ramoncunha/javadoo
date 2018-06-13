package br.com.doo.modelo;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class NotaFiscal {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private Integer numeroNota;
	
	@Enumerated(EnumType.STRING)
	private ModeloNF modelo;
	
	@Enumerated(EnumType.STRING)
	private NaturezaNF natureza;
	
	private Calendar dataOperacao;
	private Calendar dataEmissao;
	
	@ManyToOne
	private Pessoa emitente;
	@ManyToOne
	private Pessoa destinatario;
	
	
	
	
	
	
	
	
}
