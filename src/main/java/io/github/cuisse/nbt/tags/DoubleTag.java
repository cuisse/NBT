package io.github.cuisse.nbt.tags;

import io.github.cuisse.nbt.Tag;
import io.github.cuisse.nbt.TagType;

/**
 * A {@link Tag} that contains a {@code double} value.
 *
 * @author Brayan Roman
 * @since  1.0.0
 */
public class DoubleTag implements Tag {

    public static final DoubleTag DEFAULT = new DoubleTag(0d);

    /**
     * Creates a new {@link DoubleTag} with the specified value.
     *
     * @param value the value of the tag.
     * @return the new tag.
     */
    public static DoubleTag create(double value) {
        return value == 0d ? DEFAULT : new DoubleTag(value);
    }

    private final double value;

    private DoubleTag(double value) {
        this.value = value;
    }

    @Override
    public Double value() {
        return value;
    }

    public double primitiveValue() {
        return value;
    }

    @Override
    public TagType type() {
        return TagType.TAG_Double;
    }

    @Override
    public int compareTo(Tag other) {
        if (other instanceof DoubleTag tag) {
            return Double.compare(value, tag.value);
        } else {
            throw new IllegalArgumentException("Cannot compare " + getClass().getSimpleName() + " to " + other.getClass().getSimpleName());
        }
    }

    @Override
    public int hashCode() {
        return Double.hashCode(value);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof DoubleTag tag && value == tag.value;
    }

    @Override
    public String toString() {
        return "DoubleTag(" + value + ')';
    }

}
