package se331.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import se331.rest.dao.OrganizationDao;
import se331.rest.entity.Organization;
import se331.rest.service.OrganizationService;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    OrganizationDao organizationDao;

    @Override
    public Integer getOrganizationSize() {
        return organizationDao.getOrganizationSize();
    }

    @Override
    public Page<Organization> getOrganizations(Integer pageSize, Integer page) {
        return organizationDao.getOrganizations(pageSize, page);
    }

    @Override
    public Organization getOrganization(Long id) {
        return organizationDao.getOrganization(id);
    }

    @Override
    public Organization save(Organization organization) {
        return organizationDao.save(organization);
    }

    @Override
    public Organization update(Long id, Organization organization) {
        return organizationDao.update(id, organization);
    }

    @Override
    public void delete(Long id) {
        organizationDao.delete(id);
    }
}