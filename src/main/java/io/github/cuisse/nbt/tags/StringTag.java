package io.github.cuisse.nbt.tags;

import io.github.cuisse.nbt.Tag;
import io.github.cuisse.nbt.TagType;

/**
 * A {@link Tag} that contains a {@link String} value.
 *
 * @author Brayan Roman
 * @since  1.0.0
 */
public class StringTag implements Tag {

    public static final StringTag DEFAULT = new StringTag("");

    private final String value;

    /**
     * Creates a new {@link StringTag} with the specified value.
     *
     * @param value the value of the tag.
     * @return the new tag.
     * @throws NullPointerException if the value is {@code null}.
     */
    public static StringTag create(String value) {
        return new StringTag(value);
    }

    private StringTag(String value) {
        if (value == null) {
            throw new NullPointerException("value == null");
        }
        this.value = value;
    }

    @Override
    public String value() {
        return value;
    }

    @Override
    public TagType type() {
        return TagType.TAG_String;
    }

    @Override
    public int compareTo(Tag other) {
        if (other instanceof StringTag tag) {
            return value.compareTo(tag.value);
        } else {
            throw new IllegalArgumentException("Cannot compare " + getClass().getSimpleName() + " to " + other.getClass().getSimpleName());
        }
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof StringTag tag && value.equals(tag.value);
    }

    @Override
    public String toString() {
        return "StringTag(\"" + value + "\")";
    }

}
