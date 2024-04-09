package io.github.cuisse.nbt.io;

import io.github.cuisse.nbt.NamedTag;
import io.github.cuisse.nbt.Tag;

/**
 * An interface for writing Tags.
 *
 * @author Brayan Roman
 * @since  1.0.0
 */
public interface NBTWriter {

    /**
     * Writes {@link NamedTag} to this output stream.
     *
     * @param output The output stream to write to.
     * @param tag    The Tag to write.
     */
    void write(NBTOutput output, NamedTag tag);

    /**
     * Writes {@link Tag} to this output stream.
     * @param output The output stream to write to.
     * @param tag The Tag to write.
     */
    void write(NBTOutput output, Tag tag);

    /**
     * Writes {@link Tag} to this output stream at the specified depth.
     * @param output The output stream to write to.
     * @param tag The Tag to write.
     * @param depth The depth of the Tag.
     */
    void write(NBTOutput output, Tag tag, int depth);

}
