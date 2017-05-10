package ps.java.brainfuck;

import ps.java.brainfuck.data.BrainfuckDataStorage;

/**
 * This is the visual data storage of the {@link BrainfuckDataStorage} interface
 * for the {@link Brainfuck} esoteric programming language web IDE.
 *
 * @author slady@slady.net
 */
public class BrainfuckVisualDataStorage implements BrainfuckDataStorage {
    byte[] storage = new byte[100];
    int pointer, max;

    public void incrementPointer() {
        ++this.pointer;
        if (pointer > max) {
            max = pointer;
        }
    }

    public void decrementPointer() {
        --this.pointer;
    }

    public void increaseValue() {
        ++this.storage[this.pointer];
    }

    public void decreaseValue() {
        --this.storage[this.pointer];
    }

    public int getValue() {
        return this.storage[this.pointer];
    }

    public void setValue(int value) {
        this.storage[this.pointer] = (byte) value;
    }

    public int getMax() {
        return max;
    }

    public int getValueAt(final int location) {
        return this.storage[location];
    }

    public int getPointer() {
        return pointer;
    }

}
