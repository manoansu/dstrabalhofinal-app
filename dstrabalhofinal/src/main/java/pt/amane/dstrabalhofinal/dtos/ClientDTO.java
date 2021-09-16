package pt.amane.dstrabalhofinal.dtos;

import java.io.Serializable;
import java.time.Instant;

import pt.amane.dstrabalhofinal.entities.Client;

public class ClientDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String cpf;
	private Double income;
	private Instant birthDate;
	private Integer children;

	public ClientDTO() {
	}

	public ClientDTO(Long id, String name, String cpf, Double income, Instant birthDate, Integer childen) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.income = income;
		this.birthDate = birthDate;
		this.children = childen;
	}

	public ClientDTO(Client client) {
		id = client.getId();
		name = client.getName();
		cpf = client.getCpf();
		income = client.getIncome();
		birthDate = client.getBirthDate();
		children = client.getChildren();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCpf() {
		return cpf;
	}

	public Double getIncome() {
		return income;
	}

	public Instant getBirthDate() {
		return birthDate;
	}

	public Integer getChildren() {
		return children;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public void setBirthDate(Instant birthDate) {
		this.birthDate = birthDate;
	}

	public void setChildren(Integer children) {
		this.children = children;
	}

}
