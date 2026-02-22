package github.com.torloejborg.flows.ui.forms.render;

import java.util.function.Supplier;

public record FormField(
        String key,
        Supplier<Object> valueSupplier
) {}

