package se331.rest.service;

import org.springframework.data.domain.Page;
import se331.rest.entity.Organization;

public interface OrganizationService {
    Integer getOrganizationSize();
    Page<Organization> getOrganizations(Integer pageSize, Integer page);
    Organization getOrganization(Long id);
    Organization save(Organization organization);
    Organization update(Long id, Organization organization);
    void delete(Long id);
}