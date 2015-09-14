package br.net.mirante.singular.persistence.entity;

import br.net.mirante.singular.flow.core.entity.IEntityProcessInstance;
import br.net.mirante.singular.flow.core.entity.IEntityVariableInstance;
import br.net.mirante.singular.flow.core.entity.IEntityVariableType;
import br.net.mirante.singular.persistence.util.Constants;

import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TB_VARIAVEL database table.
 * 
 */
@Entity
@Table(name="TB_VARIAVEL", schema = Constants.SCHEMA)
public class Variable implements IEntityVariableInstance {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CO_VARIAVEL")
	private Long cod;

	@Column(name="NO_VARIAVEL")
	private String name;

	@Column(name="VL_VARIAVEL")
	private String value;

	//bi-directional many-to-one association to ProcessInstance
	@ManyToOne
	@JoinColumn(name="CO_INSTANCIA_PROCESSO")
	private ProcessInstance processInstance;

	//uni-directional many-to-one association to VariableType
	@ManyToOne
	@JoinColumn(name="CO_TIPO_VARIAVEL")
	private VariableType type;

	public Variable() {
	}

	@Override
	public Long getCod() {
		return cod;
	}

	public void setCod(Long cod) {
		this.cod = cod;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public ProcessInstance getProcessInstance() {
		return processInstance;
	}

	public void setProcessInstance(ProcessInstance processInstance) {
		this.processInstance = processInstance;
	}

	@Override
	public VariableType getType() {
		return type;
	}

	public void setType(VariableType type) {
		this.type = type;
	}
}