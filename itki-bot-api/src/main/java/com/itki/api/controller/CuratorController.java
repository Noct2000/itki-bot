package com.itki.api.controller;

import com.itki.api.dto.CuratorRequestDto;
import com.itki.api.dto.CuratorResponseDto;
import com.itki.api.mapper.CuratorDtoMapper;
import com.itki.api.model.Curator;
import com.itki.api.service.CuratorService;
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
@RequestMapping("/curators")
@RequiredArgsConstructor
public class CuratorController {
  private final CuratorService curatorService;
  private final CuratorDtoMapper curatorDtoMapper;

  @PostMapping
  public CuratorResponseDto save(@RequestBody CuratorRequestDto curatorRequestDto) {
    Curator curator = curatorDtoMapper.toModel(curatorRequestDto);
    curatorService.save(curator);
    return curatorDtoMapper.toResponseDto(curator);
  }

  @GetMapping
  public List<CuratorResponseDto> getAll() {
    return curatorService.findAll()
        .stream()
        .map(curatorDtoMapper::toResponseDto)
        .collect(Collectors.toList());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
    curatorService.deleteById(id);
    return ResponseEntity.ok().build();
  }
}
