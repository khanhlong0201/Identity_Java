package java_learn.identity.core.model;

import java.io.Serializable;

public record ValueResponse<T>(T value) implements Serializable {}
