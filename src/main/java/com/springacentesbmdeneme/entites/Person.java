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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name="person")
public class Person {
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name="id")
		private Long id;
		@Column(name="email")
		private String email;
		@Column (name="first_name")
		private String first_name;
		@Column(name="last_name")
		private String last_name;
		@Column(name="tc")
		private Long tc;
		@DateTimeFormat(pattern="yyyy-MM-dd")
		@Column(name="birth_date",columnDefinition="DATE")
		private Date birth_date;
		@OneToOne(mappedBy = "person",cascade = CascadeType.REMOVE,
	            fetch = FetchType.LAZY)
		 private Life life;
		@OneToMany(
	            mappedBy = "person",
	            cascade = CascadeType.REMOVE,
	            fetch = FetchType.LAZY
	    )
	    private List<Vehicle> vehicles;
		@OneToMany(
	            mappedBy = "person",
	            cascade = CascadeType.REMOVE,
	            fetch = FetchType.LAZY
	    )
	    private List<Dask> dasks;
}
