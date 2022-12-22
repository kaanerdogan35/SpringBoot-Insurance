package com.springacentesbmdeneme.Service.abstracts;

import java.util.List;

import com.springacentesbmdeneme.entites.Area;

public interface AreaService {

	List<Area> getAll();

	void save(Area area);

}
