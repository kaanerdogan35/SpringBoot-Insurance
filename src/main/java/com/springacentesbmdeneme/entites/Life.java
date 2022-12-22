package com.springacentesbmdeneme.entites;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="life")
public class Life {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	@Column(name="security_deposit")
	private double security_deposit;
	@ManyToOne
	@JoinColumn(
			name="proposal_id",referencedColumnName="id"
    )
	private Proposal proposal;
	@OneToOne
	@JoinColumn(name="person_id",referencedColumnName="id")
	private Person person;
	@ManyToOne
	@JoinColumn(name = "career_id", referencedColumnName = "id")
    private Career career;
	@ManyToOne
	@JoinColumn(name = "disease_id", referencedColumnName = "id")
    private Disease disease;
	@ManyToOne
	@JoinColumn(name = "personage_id", referencedColumnName = "id")
    private PersonAge person_age;
}
