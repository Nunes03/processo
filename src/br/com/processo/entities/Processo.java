package br.com.processo.entities;

public class Processo {

	private Integer id;
	private Double tempo;
	private String nome;
	private Integer prioridade;
	private Integer tipoProcesso;
	private Integer idOrdenacao;
	private boolean jaCalculado;
	private Double tempoInicial;
	private Double tempoFinal;

	public Processo() {

	}

	public Processo(Integer id, Double tempo) {
		this.id = id;
		this.tempo = tempo;
	}

	public Processo(Integer id, Double tempo, String nome, Integer prioridade, Integer tipoProcesso) {
		this.id = id;
		this.tempo = tempo;
		this.nome = nome;
		this.prioridade = prioridade;
		this.tipoProcesso = tipoProcesso;
	}

	public Processo(Double tempo, String nome, Integer prioridade) {
		this.tempo = tempo;
		this.nome = nome;
		this.prioridade = prioridade;
	}
	
	public Processo(Double tempo, Integer tipoProcesso, Integer prioridade) {
		this.tempo = tempo;
		this.tipoProcesso = tipoProcesso;
		this.prioridade = prioridade;
	}
	public Processo(Double tempo, Integer tipoProcesso) {
		this.tempo = tempo;
		this.tipoProcesso = tipoProcesso;
	}

	public Processo(Integer id, Double tempo, String nome, Integer prioridade, Integer tipoProcesso, Integer idOrdenacao, Double tempoInicial, Double tempoFinal) {
		this.id = id;
		this.tempo = tempo;
		this.nome = nome;
		this.prioridade = prioridade;
		this.tempoInicial = tempoInicial;
		this.tipoProcesso = tipoProcesso;
		this.idOrdenacao = idOrdenacao;
		this.tempoFinal = tempoFinal;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public Double getTempo() {
		return tempo;
	}

	public void setTempo(Double tempo) {
		this.tempo = tempo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
	}

	public Double getTempoInicial() {
		return tempoInicial;
	}

	public void setTempoInicial(Double tempoInicial) {
		this.tempoInicial = tempoInicial;
	}

	public Double getTempoFinal() {
		return tempoFinal;
	}

	public void setTempoFinal(Double tempoFinal) {
		this.tempoFinal = tempoFinal;
	}

	public Integer getTipoProcesso() {
		return tipoProcesso;
	}

	public void setTipoProcesso(Integer tipoProcesso) {
		this.tipoProcesso = tipoProcesso;
	}

	public Integer getIdOrdenacao() {
		return idOrdenacao;
	}

	public void setIdOrdenacao(Integer idOrdenacao) {
		this.idOrdenacao = idOrdenacao;
	}

	public boolean isJaCalculado() {
		return jaCalculado;
	}

	public void setJaCalculado(boolean jaCalculado) {
		this.jaCalculado = jaCalculado;
	}
}