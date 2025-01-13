package com.jsf.entities;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the us_ro database table.
 * 
 */
@Entity
@Table(name="us_ro")
@NamedQuery(name="UsRo.findAll", query="SELECT u FROM UsRo u")
public class UsRo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_us_ro")
	private int idUsRo;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="roles_id_role")
	private Role role;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	public UsRo() {
	}

	public int getIdUsRo() {
		return this.idUsRo;
	}

	public void setIdUsRo(int idUsRo) {
		this.idUsRo = idUsRo;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}