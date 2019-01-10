package SEAMToOpenAPI;

import com.sun.istack.internal.NotNull;

/**
 * A pair of Nodes connected through an Edge.
 * @author Natalia Nessler
 */
public class Pair {

    private Node source = null;
    private Node target = null;


    public Pair(@NotNull Node source, @NotNull Node target) {
        if (source == null) {
            throw new IllegalArgumentException("The Edge must have a source.");
        }
        if (target == null) {
            throw new IllegalArgumentException("The Edge must have a target.");
        }
        this.source = source;
        this.target = target;
    }

    public Node source() {
        return source;
    }

    public Node target() {
        return target;
    }

    public void print() {
        System.out.println("Source:");
        source.print();
        System.out.println("Target:");
        target.print();
    }
}
