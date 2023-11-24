package dev.andzwp.emailcreator.dto;

import lombok.Builder;

@Builder
public record User(String email) {
}
