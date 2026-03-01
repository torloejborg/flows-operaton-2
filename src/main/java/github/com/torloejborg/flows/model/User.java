package github.com.torloejborg.flows.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;


@Data
@RequiredArgsConstructor
public class User {
    private final String name;
    private final List<String> roles;
}
