package com.example.reactor.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@ToString(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ChildResourceDTO extends ResourceDTO {

    private String createdAt;
    private String name;
    private String avatar;
    private String id;


}
