package io.github.cuisse.nbt.tags;

import io.github.cuisse.nbt.Tag;
import io.github.cuisse.nbt.TagType;

/**
 * A {@link Tag} that contains a {@code float} value.
 *
 * @author Brayan Roman
 * @since  1.0.0
 */
public class FloatTag implements Tag {

    public static final FloatTag DEFAULT = new FloatTag(0);

    /**
     * Creates a new {@link FloatTag} with the specified value.
     *
     * @param value the value of the tag.
     * @return the new tag.
     */
    public static FloatTag create(float value) {
        return value == 0f ? DEFAULT : new FloatTag(value);
    }

    private final float value;

    private FloatTag(float value) {
        this.value = value;
    }

    @Override
    public Float value() {
        return value;
    }

    public float primitiveValue() {
        return value;
    }

    @Override
    public TagType type() {
        return TagType.TAG_Float;
    }

    @Override
    public int compareTo(Tag other) {
        if (other instanceof FloatTag tag) {
            return Float.compare(value, tag.value);
        } else {
            throw new IllegalArgumentException("Cannot compare " + getClass().getSimpleName() + " to " + other.getClass().getSimpleName());
        }
    }

    @Override
    public int hashCode() {
        return Float.hashCode(value);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof FloatTag tag && value == tag.value;
    }

    @Override
    public String toString() {
        return "FloatTag(" + value + ')';
    }

}
