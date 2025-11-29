package se331.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se331.rest.entity.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}