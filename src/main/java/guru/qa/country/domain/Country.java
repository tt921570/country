package guru.qa.country.domain;

import java.time.LocalDateTime;

public record Country(
        String fullName,
        String code,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
