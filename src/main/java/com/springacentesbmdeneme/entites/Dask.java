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
@Table(name="dask")
public class Dask {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	@Column (name="adress")
	private String adress;
	@ManyToOne
	@JoinColumn(name = "person_id", referencedColumnName = "id", nullable = false)
	private Person person; 
	@ManyToMany
	@JoinTable(
            name = "daskproposal",
            joinColumns = @JoinColumn(name = "dask_id",referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name = "proposal_id",referencedColumnName="id")
    )
	private List<Proposal> daskproposal;
	@ManyToOne
	@JoinColumn(name = "buildtype_id", referencedColumnName = "id", nullable = false)
	private BuildType buildtype;
	@ManyToOne
	@JoinColumn(name = "city_id", referencedColumnName = "id", nullable = false)
	private City city;
	@ManyToOne
	@JoinColumn(name = "numberoffloor_id", referencedColumnName = "id", nullable = false)
	private NumberofFloors floor;
	@ManyToOne
	@JoinColumn(name = "buildage_id", referencedColumnName = "id", nullable = false)
	private BuildAge build_age;
	@ManyToOne
	@JoinColumn(name = "area_id", referencedColumnName = "id", nullable = false)
	private Area area;
	
}
