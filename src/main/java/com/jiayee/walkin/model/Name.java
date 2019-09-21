package com.jiayee.walkin.model;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Builder
@Value
class Name {

  private static final String NAME_PART_SEP = " ";

  @NonNull
  private final String firstName;

  private final String middleName;

  private final String lastName;

  Name(
      String firstName,
      String middleName,
      String lastName
  ) {
    this.firstName = firstName;
    this.middleName = Optional.ofNullable(middleName).orElse("");
    this.lastName = Optional.ofNullable(lastName).orElse("");
  }

  String serialize() {
    return Stream
        .of(firstName, middleName, lastName)
        .filter(s -> !s.isEmpty())
        .collect(
            Collectors.joining(NAME_PART_SEP)
        );
  }

  static Name deserialize(String name) {
    String[] parts = name.split(NAME_PART_SEP);
    switch (parts.length) {
      case 1:
        return new Name(
            parts[0],
            null,
            null
        );
      case 2:
        return new Name(
            parts[0],
            null,
            parts[1]
        );
      case 3:
        return new Name(
            parts[0],
            parts[1],
            parts[2]
        );
      default:
        return null;
    }
  }

}
