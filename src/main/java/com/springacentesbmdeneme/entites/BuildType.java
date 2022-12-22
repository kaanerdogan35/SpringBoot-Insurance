package com.springacentesbmdeneme.entites;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name="buildtype")
public class BuildType{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private Long id;
	@Column(name="name")
	private String name;
	@Column(name ="price_multiplier")
	private double price_multiplier;
	@OneToMany(
            mappedBy = "buildtype",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.LAZY
    )
    private List<Dask> dask;
}