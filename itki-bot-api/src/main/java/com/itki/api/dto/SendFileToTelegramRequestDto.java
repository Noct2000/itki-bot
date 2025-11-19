package com.itki.api.dto;

import java.util.List;

public record SendFileToTelegramRequestDto(
    List<String> filenames,
    String caption
) {
}
