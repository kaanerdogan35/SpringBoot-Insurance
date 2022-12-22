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
@Table(name="vehicle")
public class Vehicle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	@Column(name="chassis_number")
	private String chassis_number;
	@Column(name="plate")
	private String plate;
	@Column(name="modelYear")
	private int modelYear;
	@ManyToOne
	@JoinColumn(name="person_id",referencedColumnName="id",nullable=false)
	private Person person;
	@ManyToOne
	@JoinColumn(name = "vehiclebrand_id", referencedColumnName = "id")
	private VehicleBrand vehicleBrand;
	@ManyToOne
	@JoinColumn(name = "vehicletype_id", referencedColumnName = "id")
	private VehicleType vehicleType;
	@ManyToOne
	@JoinColumn(name="AgeRange_id",referencedColumnName="id")
	private VehicleAgeRange vehicleAgeRange;
	@ManyToMany
	@JoinTable(
            name = "vehicleProposal",
            joinColumns = @JoinColumn(name = "vehicle_id",referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name = "proposal_id",referencedColumnName="id")
    )
	private List<Proposal> proposal;
	
	
	
}
