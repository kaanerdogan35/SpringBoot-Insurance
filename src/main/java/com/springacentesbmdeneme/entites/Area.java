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
@Table(name="area")
public class Area {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	@Column (name="title")
	private String title;
	@Column(name="min")
	private int min;
	@Column(name="max")
	private int max;
	@Column(name="price_multiplier")
	private double price_multiplier;
	@OneToMany(
            mappedBy = "build_age",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.LAZY
    )
    private List<Dask> dask;
}