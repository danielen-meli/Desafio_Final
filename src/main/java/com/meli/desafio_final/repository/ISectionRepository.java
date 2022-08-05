package com.meli.desafio_final.repository;

import com.meli.desafio_final.model.Section;
import org.springframework.data.repository.CrudRepository;

public interface ISectionRepository extends CrudRepository<Section, Long>  {
}
