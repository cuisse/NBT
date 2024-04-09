package io.github.cuisse.nbt.tags;

import io.github.cuisse.nbt.Tag;
import io.github.cuisse.nbt.TagType;

import java.util.Arrays;

/**
 * A {@code Tag} that contains a {@code byte[]} value.
 *
 * @author Brayan Roman
 * @since  1.0.0
 */
public class ByteArrayTag implements Tag {

    public static final ByteArrayTag DEFAULT = new ByteArrayTag(new byte[0]);

    public static ByteArrayTag create(byte[] value) {
        return value.length == 0 ? DEFAULT : new ByteArrayTag(value);
    }

    private final byte[] value;
    private volatile int hashcode;

    private ByteArrayTag(byte[] value) {
        this.value = value;
    }

    @Override
    public byte[] value() {
        return value; // TODO: Use clone to prevent modification?
    }

    @Override
    public TagType type() {
        return TagType.TAG_Byte_Array;
    }

    @Override
    public int compareTo(Tag other) {
        if (other instanceof ByteArrayTag tag) {
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
        return other instanceof ByteArrayTag tag && Arrays.equals(value, tag.value);
    }

    @Override
    public String toString() {
        return "TAG_Byte_Array(" + value.length + "): " + Arrays.toString(value);
    }

}
