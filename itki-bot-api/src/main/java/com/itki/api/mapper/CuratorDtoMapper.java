package com.itki.api.mapper;

import com.itki.api.dto.CuratorRequestDto;
import com.itki.api.dto.CuratorResponseDto;
import com.itki.api.model.Curator;
import org.springframework.stereotype.Component;

@Component
public class CuratorDtoMapper {
  public Curator toModel(CuratorRequestDto curatorRequestDto) {
    Curator curator = new Curator();
    curator.setName(curatorRequestDto.getName());
    curator.setLastName(curatorRequestDto.getLastName());
    curator.setAdditionalName(curatorRequestDto.getAdditionalName());
    curator.setDepartment(curatorRequestDto.getDepartment());
    curator.setPosition(curatorRequestDto.getPosition());
    return curator;
  }

  public CuratorResponseDto toResponseDto(Curator curator) {
    CuratorResponseDto curatorResponseDto = new CuratorResponseDto();
    curatorResponseDto.setId(curator.getId());
    curatorResponseDto.setName(curator.getName());
    curatorResponseDto.setLastName(curator.getLastName());
    curatorResponseDto.setAdditionalName(curator.getAdditionalName());
    curatorResponseDto.setDepartment(curator.getDepartment());
    curatorResponseDto.setPosition(curator.getPosition());
    return curatorResponseDto;
  }
}
