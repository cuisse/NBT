package io.github.cuisse.nbt;

/**
 * Thrown when an error occurs while reading or writing NBT data.
 *
 * @author Brayan Roman
 * @since  1.0.0
 */
public class NBTException extends RuntimeException {

    public NBTException(String message) {
        super(message);
    }

    public NBTException(String message, Throwable cause) {
        super(message, cause);
    }

    public NBTException(Throwable cause) {
        super(cause);
    }

}
