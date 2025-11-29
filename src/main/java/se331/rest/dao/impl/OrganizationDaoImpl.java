package se331.rest.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import se331.rest.dao.OrganizationDao;
import se331.rest.entity.Organization;
import se331.rest.repository.OrganizationRepository;

import java.util.Optional;

@Repository
public class OrganizationDaoImpl implements OrganizationDao {
    @Autowired
    OrganizationRepository organizationRepository;

    @Override
    public Integer getOrganizationSize() {
        return Math.toIntExact(organizationRepository.count());
    }

    @Override
    public Page<Organization> getOrganizations(Integer pageSize, Integer page) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        return organizationRepository.findAll(pageable);
    }

    @Override
    public Organization getOrganization(Long id) {
        return organizationRepository.findById(id).orElse(null);
    }

    @Override
    public Organization save(Organization organization) {
        return organizationRepository.save(organization);
    }

    @Override
    public Organization update(Long id, Organization organization) {
        Optional<Organization> existing = organizationRepository.findById(id);
        if (existing.isPresent()) {
            Organization oldOrg = existing.get();
            if (organization.getName() != null) oldOrg.setName(organization.getName());
            if (organization.getAddress() != null) oldOrg.setAddress(organization.getAddress());
            if (organization.getContactNumber() != null) oldOrg.setContactNumber(organization.getContactNumber());
            return organizationRepository.save(oldOrg);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        organizationRepository.deleteById(id);
    }
}