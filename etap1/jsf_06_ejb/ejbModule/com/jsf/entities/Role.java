package com.jsf.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the roles database table.
 * 
 */
@Entity
@Table(name="roles")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_role")
	private int idRole;

	private String active;

	@Temporal(TemporalType.DATE)
	@Column(name="last_change")
	private Date lastChange;

	@Column(name="role_name")
	private String roleName;

	//bi-directional many-to-one association to UsRo
	@OneToMany(mappedBy="role")
	private List<UsRo> usRos;

	public Role() {
	}

	public int getIdRole() {
		return this.idRole;
	}

	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public Date getLastChange() {
		return this.lastChange;
	}

	public void setLastChange(Date lastChange) {
		this.lastChange = lastChange;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<UsRo> getUsRos() {
		return this.usRos;
	}

	public void setUsRos(List<UsRo> usRos) {
		this.usRos = usRos;
	}

	public UsRo addUsRo(UsRo usRo) {
		getUsRos().add(usRo);
		usRo.setRole(this);

		return usRo;
	}

	public UsRo removeUsRo(UsRo usRo) {
		getUsRos().remove(usRo);
		usRo.setRole(null);

		return usRo;
	}

}