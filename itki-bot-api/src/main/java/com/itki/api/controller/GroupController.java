package com.itki.api.controller;

import com.itki.api.dto.GroupRequestDto;
import com.itki.api.dto.GroupResponseDto;
import com.itki.api.mapper.GroupDtoMapper;
import com.itki.api.model.Group;
import com.itki.api.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupController {
  private final GroupDtoMapper groupDtoMapper;
  private final GroupService groupService;

  @PostMapping
  public GroupResponseDto save(@RequestBody GroupRequestDto groupRequestDto) {
    Group group = groupDtoMapper.toModel(groupRequestDto);
    groupService.save(group);
    return groupDtoMapper.toResponseDto(group);
  }

  @GetMapping
  public List<GroupResponseDto> getAll() {
    return groupService.findAll()
        .stream()
        .map(groupDtoMapper::toResponseDto)
        .collect(Collectors.toList());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
    groupService.deleteById(id);
    return ResponseEntity.ok().build();
  }
}
