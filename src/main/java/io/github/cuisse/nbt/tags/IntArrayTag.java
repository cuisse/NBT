package io.github.cuisse.nbt.tags;

import io.github.cuisse.nbt.Tag;
import io.github.cuisse.nbt.TagType;

import java.util.Arrays;

/**
 * A {@link Tag} that contains an array of {@code int}s.
 *
 * @author Brayan Roman
 * @since  1.0.0
 */
public class IntArrayTag implements Tag {

    public static final IntArrayTag DEFAULT = new IntArrayTag(new int[0]);

    /**
     * Creates a new {@link IntArrayTag} with the specified value.
     *
     * @param value the value of the tag.
     * @return the new tag.
     */
    public static IntArrayTag create(int[] value) {
        return value.length == 0 ? DEFAULT : new IntArrayTag(value);
    }

    private final int[] value;
    private volatile int hashcode;

    private IntArrayTag(int[] value) {
        this.value = value;
    }

    @Override
    public int[] value() {
        return value; // TODO: Use clone to prevent modification?
    }

    @Override
    public TagType type() {
        return TagType.TAG_Int_Array;
    }

    @Override
    public int compareTo(Tag other) {
        if (other instanceof IntArrayTag tag) {
            return Arrays.compare(value, tag.value);
        } else {
            throw new IllegalArgumentException("Cannot compare " + getClass().getSimpleName() + " to " + other.getClass().getSimpleName());
        }
    }

    @Override
    public int hashCode() {
        if (hashcode == 0) {
            hashcode = Tag.hash(
                    Arrays.hashCode(value)
            );
        }
        return hashcode;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof IntArrayTag tag && Arrays.equals(value, tag.value);
    }

    @Override
    public String toString() {
        return "TAG_Int_Array(" + value.length + "): " + Arrays.toString(value);
    }

}
