package com.lviv.iot.dto.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.lviv.iot.controller.ServiceStaffController;
import com.lviv.iot.domain.ServiceStaff;
import com.lviv.iot.dto.ServiceStaffDto;

@Component
public class ServiceStaffDtoAssembler implements RepresentationModelAssembler<ServiceStaff, ServiceStaffDto> {
    @Override
    public ServiceStaffDto toModel(ServiceStaff entity) {
        ServiceStaffDto serviceStaffDto = ServiceStaffDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .lastName(entity.getLastname())
                .addressId(entity.getAddress().getId())
                .build();
        Link selfLink = linkTo(methodOn(ServiceStaffController.class).getServiceStaff(serviceStaffDto.getId())).withSelfRel();
        serviceStaffDto.add(selfLink);
        return serviceStaffDto;
    }

    @Override
    public CollectionModel<ServiceStaffDto> toCollectionModel(Iterable<? extends ServiceStaff> entities) {
        CollectionModel<ServiceStaffDto> serviceStaffDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(ServiceStaffController.class).getAllServiceStaffs()).withSelfRel();
        serviceStaffDtos.add(selfLink);
        return serviceStaffDtos;
    }

    public CollectionModel<ServiceStaffDto> toCollectionModel(Iterable<? extends ServiceStaff> entities, Link link) {
        CollectionModel<ServiceStaffDto> serviceStaffDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        serviceStaffDtos.add(link);
        return serviceStaffDtos;
    }
}