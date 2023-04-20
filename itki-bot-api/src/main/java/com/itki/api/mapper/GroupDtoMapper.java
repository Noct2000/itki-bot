package com.itki.api.mapper;

import com.itki.api.dto.GroupRequestDto;
import com.itki.api.dto.GroupResponseDto;
import com.itki.api.model.Group;
import com.itki.api.service.CuratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GroupDtoMapper {
  private final CuratorDtoMapper curatorDtoMapper;
  private final CuratorService curatorService;

  public Group toModel(GroupRequestDto groupRequestDto) {
    Group group = new Group();
    group.setName(groupRequestDto.getName());
    group.setCurator(curatorService.findById(groupRequestDto.getCuratorId()));
    return group;
  }

  public GroupResponseDto toResponseDto(Group group) {
    GroupResponseDto groupResponseDto = new GroupResponseDto();
    groupResponseDto.setId(group.getId());
    groupResponseDto.setName(group.getName());
    groupResponseDto.setCuratorResponseDto(curatorDtoMapper.toResponseDto(group.getCurator()));
    return groupResponseDto;
  }
}
