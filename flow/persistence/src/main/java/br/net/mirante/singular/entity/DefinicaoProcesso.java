package br.net.mirante.singular.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TB_DEFINICAO_PROCESSO database table.
 * 
 */
@Entity
@Table(name="TB_DEFINICAO_PROCESSO")
@NamedQuery(name="DefinicaoProcesso.findAll", query="SELECT d FROM DefinicaoProcesso d")
public class DefinicaoProcesso  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CO_DEFINICAO_PROCESSO")
	private Long cod;

	@Column(name="NO_CLASSE_JAVA")
	private String nomeClasseJava;

	@Column(name="NO_PROCESSO")
	private String nomeProcesso;

	@Column(name="SG_PROCESSO")
	private String siglaProcesso;

	//bi-directional many-to-one association to PermissaoProcesso
	@OneToMany(mappedBy="definicaoProcesso")
	private List<PermissaoProcesso> permissoesTarefas;

	//uni-directional many-to-one association to Categoria
	@ManyToOne
	@JoinColumn(name="CO_CATEGORA")
	private Categoria categoria;

	//bi-directional many-to-one association to DefinicaoTarefa
	@OneToMany(mappedBy="definicaoProcesso")
	private List<DefinicaoTarefa> definicaoTarefas;

	//bi-directional many-to-one association to Papel
	@OneToMany(mappedBy="definicaoProcesso")
	private List<Papel> papeis;

	public DefinicaoProcesso() {
	}

	public Long getCod() {
		return this.cod;
	}

	public void setCod(Long cod) {
		this.cod = cod;
	}

	public String getNomeClasseJava() {
		return this.nomeClasseJava;
	}

	public void setNomeClasseJava(String nomeClasseJava) {
		this.nomeClasseJava = nomeClasseJava;
	}

	public String getNomeProcesso() {
		return this.nomeProcesso;
	}

	public void setNomeProcesso(String nomeProcesso) {
		this.nomeProcesso = nomeProcesso;
	}

	public String getSiglaProcesso() {
		return this.siglaProcesso;
	}

	public void setSiglaProcesso(String siglaProcesso) {
		this.siglaProcesso = siglaProcesso;
	}

	public List<PermissaoProcesso> getPermissoesTarefas() {
		return this.permissoesTarefas;
	}

	public void setPermissoesTarefas(List<PermissaoProcesso> permissoesTarefas) {
		this.permissoesTarefas = permissoesTarefas;
	}

	public PermissaoProcesso addPermissoesTarefa(PermissaoProcesso permissoesTarefa) {
		getPermissoesTarefas().add(permissoesTarefa);
		permissoesTarefa.setDefinicaoProcesso(this);

		return permissoesTarefa;
	}

	public PermissaoProcesso removePermissoesTarefa(PermissaoProcesso permissoesTarefa) {
		getPermissoesTarefas().remove(permissoesTarefa);
		permissoesTarefa.setDefinicaoProcesso(null);

		return permissoesTarefa;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<DefinicaoTarefa> getDefinicaoTarefas() {
		return this.definicaoTarefas;
	}

	public void setDefinicaoTarefas(List<DefinicaoTarefa> definicaoTarefas) {
		this.definicaoTarefas = definicaoTarefas;
	}

	public DefinicaoTarefa addDefinicaoTarefa(DefinicaoTarefa definicaoTarefa) {
		getDefinicaoTarefas().add(definicaoTarefa);
		definicaoTarefa.setDefinicaoProcesso(this);

		return definicaoTarefa;
	}

	public DefinicaoTarefa removeDefinicaoTarefa(DefinicaoTarefa definicaoTarefa) {
		getDefinicaoTarefas().remove(definicaoTarefa);
		definicaoTarefa.setDefinicaoProcesso(null);

		return definicaoTarefa;
	}

	public List<Papel> getPapeis() {
		return this.papeis;
	}

	public void setPapeis(List<Papel> papeis) {
		this.papeis = papeis;
	}

	public Papel addPapei(Papel papei) {
		getPapeis().add(papei);
		papei.setDefinicaoProcesso(this);

		return papei;
	}

	public Papel removePapei(Papel papei) {
		getPapeis().remove(papei);
		papei.setDefinicaoProcesso(null);

		return papei;
	}

}