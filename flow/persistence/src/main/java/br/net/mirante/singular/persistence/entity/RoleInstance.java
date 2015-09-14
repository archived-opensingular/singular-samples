package br.net.mirante.singular.persistence.entity;

import br.net.mirante.singular.flow.core.MUser;
import br.net.mirante.singular.flow.core.entity.IEntityRole;
import br.net.mirante.singular.persistence.entity.util.ActorWrapper;
import br.net.mirante.singular.persistence.util.Constants;

import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the TB_INSTANCIA_PAPEL database table.
 * 
 */
@Entity
@Table(name="TB_INSTANCIA_PAPEL", schema = Constants.SCHEMA)
public class RoleInstance implements IEntityRole {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CO_INSTANCIA_PAPEL")
	private Long cod;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DT_CRIACAO")
	private Date createDate;

	//uni-directional many-to-one association to Actor
	@ManyToOne
	@JoinColumn(name="CO_ATOR")
	private Actor actor;

	//uni-directional many-to-one association to Actor
	@ManyToOne
	@JoinColumn(name="CO_ATOR_ALOCADOR")
	private Actor allocatorActor;

	//uni-directional many-to-one association to ProcessInstance
	@ManyToOne
	@JoinColumn(name="CO_INSTANCIA_PROCESSO")
	private ProcessInstance processInstance;

	//uni-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="CO_PAPEL")
	private Role role;

	public RoleInstance() {
	}

	@Override
	public Long getCod() {
		return cod;
	}

	public void setCod(Long cod) {
		this.cod = cod;
	}

	@Override
	public Date getCreateDate() {
		return createDate;
	}

	@Override
	public MUser getAllocatorUser() {
		return ActorWrapper.wrap(getActor());
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	public Actor getAllocatorActor() {
		return allocatorActor;
	}

	public void setAllocatorActor(Actor allocatorActor) {
		this.allocatorActor = allocatorActor;
	}

	@Override
	public ProcessInstance getProcessInstance() {
		return processInstance;
	}

	public void setProcessInstance(ProcessInstance processInstance) {
		this.processInstance = processInstance;
	}

	@Override
	public Role getRole() {
		return role;
	}

	@Override
	public MUser getUser() {
		return ActorWrapper.wrap(actor);
	}

	public void setRole(Role role) {
		this.role = role;
	}
}