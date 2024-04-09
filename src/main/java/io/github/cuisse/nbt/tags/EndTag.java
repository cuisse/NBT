package io.github.cuisse.nbt.tags;

import io.github.cuisse.nbt.Tag;
import io.github.cuisse.nbt.TagType;

/**
 * EndTags are used to mark the end of a list or compound tag.
 *
 * @author Brayan Roman
 * @since  1.0.0
 */
public class EndTag implements Tag {

    public static final EndTag DEFAULT = new EndTag();

    public static EndTag create() {
        return DEFAULT;
    }

    private EndTag() {}

    @Override
    public Object value() {
        return null;
    }

    @Override
    public TagType type() {
        return TagType.TAG_End;
    }

    @Override
    public int compareTo(Tag other) {
        return 0;
    }

    @Override
    public String toString() {
        return "EndTag()";
    }

}
