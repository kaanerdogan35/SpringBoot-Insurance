package com.springacentesbmdeneme.entites;


import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;


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
@Table(name="proposal")
public class Proposal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column (name="order_date",columnDefinition="DATE")
	private Date order_date;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name="accepted_date",columnDefinition="DATE")
	private Date accepted_date;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name="end_date",columnDefinition="DATE")
	private Date end_date;
	@Column(name="price")
	private double price;
	@Column(name="status")
	private String status;
	@ManyToMany(mappedBy="proposal")
	private List<Vehicle> vehicle;
	@ManyToMany(mappedBy="daskproposal",cascade = CascadeType.REMOVE)
	private List<Dask> dask;
	@OneToMany(
            mappedBy = "proposal",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.LAZY
    )
	private List<Life> life;
}
