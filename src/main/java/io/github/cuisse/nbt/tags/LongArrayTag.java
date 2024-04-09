package io.github.cuisse.nbt.tags;

import io.github.cuisse.nbt.Tag;
import io.github.cuisse.nbt.TagType;

import java.util.Arrays;

/**
 * A {@link Tag} that contains an array of longs.
 *
 * @author Brayan Roman
 * @since  1.0.0
 */
public class LongArrayTag implements Tag {

    public static final LongArrayTag DEFAULT = new LongArrayTag(new long[0]);

    public static LongArrayTag create(long[] value) {
        return value.length == 0 ? DEFAULT : new LongArrayTag(value);
    }

    private final long[] value;
    private volatile int hashcode;

    private LongArrayTag(long[] value) {
        this.value = value;
    }

    @Override
    public long[] value() {
        return value; // TODO: Use clone to prevent modification?
    }

    @Override
    public TagType type() {
        return TagType.TAG_Long_Array;
    }

    @Override
    public int compareTo(Tag other) {
        if (other instanceof LongArrayTag tag) {
            return Arrays.compare(value, tag.value);
        } else {
            throw new IllegalArgumentException("Cannot compare LongArrayTag to " + other.getClass().getSimpleName());
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
    public boolean equals(Object obj) {
        return obj instanceof LongArrayTag tag && Arrays.equals(value, tag.value);
    }

    @Override
    public String toString() {
        return "TAG_Long_Array(" + value.length + "): " + Arrays.toString(value);
    }

}
