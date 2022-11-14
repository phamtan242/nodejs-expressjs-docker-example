package demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import demo.entities.CountryEntity;

public interface CountryRepository extends JpaRepository<CountryEntity, Integer> {
  // List<CountryEntity> findByPublished(boolean published);
  // List<CountryEntity> findByTitleContaining(String title);
}