package br.com.doonfe.modelo;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class NotaFiscal {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable=false)
	private Integer numeroNota;
	
	@Enumerated(EnumType.STRING)
	private ModeloNF modelo;
	
	@Enumerated(EnumType.STRING)
	private NaturezaNF natureza;
	
	@Column(nullable=false)
	private Calendar dataOperacao;
	
	@Column(nullable=false)
	private Calendar dataEmissao;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Pessoa emitente;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Pessoa destinatario;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="notaFiscal", fetch=FetchType.EAGER)
	private List<Itens> itens = new LinkedList<>();
	
	@Column(nullable=true, length=2048)
	private String informacoesComplementares;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNumeroNota() {
		return numeroNota;
	}
	public void setNumeroNota(Integer numeroNota) {
		this.numeroNota = numeroNota;
	}
	public ModeloNF getModelo() {
		return modelo;
	}
	public void setModelo(ModeloNF modelo) {
		this.modelo = modelo;
	}
	public NaturezaNF getNatureza() {
		return natureza;
	}
	public void setNatureza(NaturezaNF natureza) {
		this.natureza = natureza;
	}
	public Calendar getDataOperacao() {
		return dataOperacao;
	}
	public void setDataOperacao(Calendar dataOperacao) {
		this.dataOperacao = dataOperacao;
	}
	public Calendar getDataEmissao() {
		return dataEmissao;
	}
	public void setDataEmissao(Calendar dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	public Pessoa getEmitente() {
		return emitente;
	}
	public void setEmitente(Pessoa emitente) {
		this.emitente = emitente;
	}
	public Pessoa getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(Pessoa destinatario) {
		this.destinatario = destinatario;
	}
	public List<Itens> getItens() {
		return itens;
	}
	public void setItens(List<Itens> itens) {
		this.itens = itens;
	}
	public String getInformacoesComplementares() {
		return informacoesComplementares;
	}
	public void setInformacoesComplementares(String informacoesComplementares) {
		this.informacoesComplementares = informacoesComplementares;
	}
	public void addItem(Itens item) {
		itens.add(item);
	}
}
