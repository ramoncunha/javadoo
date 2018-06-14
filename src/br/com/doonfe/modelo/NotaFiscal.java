package br.com.doonfe.modelo;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
	
	@ManyToMany
	private List<Itens> itens;
	
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
}
