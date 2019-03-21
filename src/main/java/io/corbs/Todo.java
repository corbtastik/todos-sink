package io.corbs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Todo {
    private Integer id;
    private String title = "";
    private Boolean completed = false;
    private Set<String> hashtags = Collections.emptySet();
}
