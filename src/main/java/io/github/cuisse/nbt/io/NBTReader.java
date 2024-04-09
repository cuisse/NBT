package io.github.cuisse.nbt.io;

import io.github.cuisse.nbt.NamedTag;
import io.github.cuisse.nbt.Tag;
import io.github.cuisse.nbt.TagType;

/**
 * An interface for reading Tags.
 *
 * @author Brayan Roman
 * @since  1.0.0
 */
public interface NBTReader {

    /**
     * Reads {@link NamedTag} from this input stream.
     * @return The Tag read.
     */
    NamedTag read(NBTInput input);

    /**
     * Reads {@link Tag} from this input stream at the specified depth.
     * @return The Tag read.
     */
    Tag readTag(NBTInput input, int depth);

    /**
     * Reads the specified Tag's runtime from this input stream at the specified depth.
     * @return The Tag read.
     */
    Tag readTag(NBTInput input, TagType type, int depth);

}
