package com.jagcoder.organisation_service.service.impl;

import com.jagcoder.organisation_service.dto.OrganizationDto;
import com.jagcoder.organisation_service.entity.Organization;
import com.jagcoder.organisation_service.mapper.OrganizationMapper;
import com.jagcoder.organisation_service.repository.OrganizationRepository;
import com.jagcoder.organisation_service.service.OrganizationService;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private OrganizationRepository organizationRepository;

    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {

        // convert OrganizationDto into Organization jpa entity
        Organization organization = OrganizationMapper.mapToOrganization(organizationDto);

        Organization savedOrganization = organizationRepository.save(organization);

        return OrganizationMapper.mapToOrganizationDto(savedOrganization);
    }

    @Override
    public OrganizationDto getOrganizationByCode(String organizationCode) {
        Organization organization = organizationRepository.findByOrganizationCode(organizationCode);
        return OrganizationMapper.mapToOrganizationDto(organization);
    }
}