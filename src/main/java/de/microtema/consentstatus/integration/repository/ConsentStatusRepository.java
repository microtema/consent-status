package de.microtema.consentstatus.integration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ConsentStatusRepository extends JpaRepository<ConsentStatusEntity, String>, PagingAndSortingRepository<ConsentStatusEntity, String> {
}
