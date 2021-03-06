package com.tosunsi.example.designpattern.java8.decorator;

import java.util.Objects;
import java.util.function.Function;

/**
 * Example of fluent decorator.
 *
 * Created by Mazlum on 14/01/2017.
 */
public final class FluentDecorator<T> {

  private final T value;
  private final Function<T, T> function;

  private FluentDecorator(final T value, Function<T, T> function) {
    this.value = value;
    this.function = function;
  }

  public static <T> FluentDecorator<T> from(final T value) {
    Objects.requireNonNull(value);
    return new FluentDecorator<>(value, Function.identity());
  }

  public FluentDecorator<T> with(final Function<T, T> otherFunction) {
    return new FluentDecorator<>(this.value, function.andThen(otherFunction));
  }

  public T calculate() {
    return this.function.apply(value);
  }
}
