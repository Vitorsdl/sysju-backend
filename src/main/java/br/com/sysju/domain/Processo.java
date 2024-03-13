package br.com.sysju.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Processo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", nullable = false)
	private long id;
	
	private int numero;
	
	private String tipo;
	
	private String procedimento;
	
	private String vara;
	
	private String resultadoRecurso;
	
	@OneToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente = new Cliente();
	
	@OneToOne
	@JoinColumn(name = "advogado_id")
	private Advogado advogado = new Advogado();
	
	@OneToOne
	@JoinColumn(name = "comarca_id")
	private Comarca comarca = new Comarca();

	public Processo() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getProcedimento() {
		return procedimento;
	}

	public void setProcedimento(String procedimento) {
		this.procedimento = procedimento;
	}

	public String getVara() {
		return vara;
	}

	public void setVara(String vara) {
		this.vara = vara;
	}

	public String getResultadoRecurso() {
		return resultadoRecurso;
	}

	public void setResultadoRecurso(String resultadoRecurso) {
		this.resultadoRecurso = resultadoRecurso;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Advogado getAdvogado() {
		return advogado;
	}

	public void setAdvogado(Advogado advogado) {
		this.advogado = advogado;
	}

	public Comarca getComarca() {
		return comarca;
	}

	public void setComarca(Comarca comarca) {
		this.comarca = comarca;
	}

}
