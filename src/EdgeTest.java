import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class EdgeTest {

    @Test
    void constructor() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> new Edge(null, null, 0, 0, "", "", "", "", "", ""), "Should throw an exception"),
                () -> assertThrows(IllegalArgumentException.class, () -> new Edge(null, 0, null, 0, "", "", "", "", "", ""), "Should throw an exception"),
                () -> assertThrows(IllegalArgumentException.class, () -> new Edge(null, 0, 0, null, "", "", "", "", "", ""), "Should throw an exception"));
    }

    @Test
    //@DisplayName("Should return false if no list")
    void hasList() {
        Edge edge1 = new Edge(null, 1, 1, 2, "", "", "", "", "", "");
        Edge edge2 = new Edge(null, 2, 2, 1, "", "", "", "", "", "");
        Edges edges = new Edges();
        edges.add(edge2);
        assertAll(
                () -> assertFalse(edge1.hasList(), "Should return false if no list"),
                () -> assertTrue(edge2.hasList(), "Should return true if has list"));

    }

    @Test
    void hasId() {
        Edge edge1 = new Edge(null, 1, 1, 2, "", "", "", "", "", "");
        assertTrue(edge1.hasId(), "Should return true if has an id");
    }

    @Test
    void hasSource() {
        Edge edge1 = new Edge(null, 1, 1, 2, "", "", "", "", "", "");
        assertTrue(edge1.hasSource(), "Should return true if has a source");
    }

    @Test
    void hasTarget() {
        Edge edge1 = new Edge(null, 1, 1, 2, "", "", "", "", "", "");
        assertTrue(edge1.hasTarget(), "Should return true if has a target");
    }

    @Test
    void hasKind() {
        Edge edge1 = new Edge(null, 1, 1, 2, "", "", "", "", "", "");
        Edge edge2 = new Edge(null, 1, 2, 1, "kind", "", "", "", "", "");
        assertAll(
                () -> assertFalse(edge1.hasKind(), "Should return false if has no kind"),
                () -> assertTrue(edge2.hasKind(), "Should return true if has a kind"));
    }

    @Test
    void hasName() {
        Edge edge1 = new Edge(null, 1, 1, 2, "", "", "", "", "", "");
        Edge edge2 = new Edge(null, 1, 2, 1, "", "name", "", "", "", "");
        assertAll(
                () -> assertFalse(edge1.hasName(), "Should return false if has no name"),
                () -> assertTrue(edge2.hasName(), "Should return true if has a name"));
    }

    @Test
    void hasStereotype() {
        Edge edge1 = new Edge(null, 1, 1, 2, "", "", "", "", "", "");
        Edge edge2 = new Edge(null, 1, 2, 1, "", "", "stereotype", "", "", "");
        assertAll(
                () -> assertFalse(edge1.hasStereotype(), "Should return false if has no stereotype"),
                () -> assertTrue(edge2.hasStereotype(), "Should return true if has a stereotype"));
    }

    @Test
    void hasAlignment() {
        Edge edge1 = new Edge(null, 1, 1, 2, "", "", "", "", "", "");
        Edge edge2 = new Edge(null, 1, 2, 1, "", "", "", "alignment", "", "");
        assertAll(
                () -> assertFalse(edge1.hasAlignment(), "Should return false if has no alignment"),
                () -> assertTrue(edge2.hasAlignment(), "Should return true if has an alignment"));
    }

    @Test
    void hasCard() {
        Edge edge1 = new Edge(null, 1, 1, 2, "", "", "", "", "", "");
        Edge edge2 = new Edge(null, 1, 2, 1, "", "", "", "", "card", "");
        assertAll(
                () -> assertFalse(edge1.hasCard(), "Should return false if has no card"),
                () -> assertTrue(edge2.hasCard(), "Should return true if has a card"));
    }

    @Test
    void hasRole() {
        Edge edge1 = new Edge(null, 1, 1, 2, "", "", "", "", "", "");
        Edge edge2 = new Edge(null, 1, 2, 1, "", "", "", "", "", "role");
        assertAll(
                () -> assertFalse(edge1.hasRole(), "Should return false if has no role"),
                () -> assertTrue(edge2.hasRole(), "Should return true if has a role"));
    }

    @Test
    void belongsTo() {
        Edge edge1 = new Edge(null, 1, 1, 2, "", "", "", "", "", "");
        Edge edge2 = new Edge(null, 2, 2, 1, "", "", "", "", "", "");
        Edges edges = new Edges();
        edges.add(edge2);
        assertAll(
                () -> assertFalse(edge1.belongsTo(edges), "Should return false if doesn't belong to this list"),
                () -> assertTrue(edge2.belongsTo(edges), "Should return true if belongs to this list"));
    }

    @Test
    void list() {
        Edge edge1 = new Edge(null, 1, 1, 2, "", "", "", "", "", "");
        Edge edge2 = new Edge(null, 2, 2, 1, "", "", "", "", "", "");
        Edges edges = new Edges();
        edges.add(edge2);
        assertAll(
                () -> assertThrows(NullPointerException.class, () -> edge1.list(), "Should throw an exception"),
                () -> assertTrue(edge2.belongsTo(edge2.list()), "The edge should belong to its list"));
    }

    @Test
    void id() {
        Edge edge1 = new Edge(null, 1, 1, 2, "", "", "", "", "", "");
        assertTrue(edge1.id() == 1, "The edge id should be 1, but was " + edge1.id());
    }

    @Test
    void source() {
        Edge edge1 = new Edge(null, 1, 1, 2, "", "", "", "", "", "");
        assertTrue(edge1.source() == 1, "The edge source should be 1, but was " + edge1.source());
    }

    @Test
    void target() {
        Edge edge1 = new Edge(null, 1, 1, 2, "", "", "", "", "", "");
        assertTrue(edge1.target() == 2, "The edge target should be 2, but was " + edge1.target());
    }

    @Test
    void kind() {
        Edge edge1 = new Edge(null, 1, 1, 2, "", "", "", "", "", "");
        Edge edge2 = new Edge(null, 2, 2, 1, "kind", "", "", "", "", "");
        assertAll(
                () -> assertTrue(edge1.kind().equals(""), "The edge kind should be empty, but was \"" + edge1.kind() + "\""),
                () -> assertTrue(edge2.kind().equals("kind"), "The edge kind should be \"kind\", but was \"" + edge2.kind() + "\""));
    }

    @Test
    void name() {
        Edge edge1 = new Edge(null, 1, 1, 2, "", "", "", "", "", "");
        Edge edge2 = new Edge(null, 2, 2, 1, "", "name", "", "", "", "");
        assertAll(
                () -> assertTrue(edge1.name().equals(""), "The edge name should be empty, but was \"" + edge1.name() + "\""),
                () -> assertTrue(edge2.name().equals("name"), "The edge name should be \"name\", but was \"" + edge2.name() + "\""));
    }

    @Test
    void stereotype() {
        Edge edge1 = new Edge(null, 1, 1, 2, "", "", "", "", "", "");
        Edge edge2 = new Edge(null, 2, 2, 1, "", "", "stereotype", "", "", "");
        assertAll(
                () -> assertTrue(edge1.stereotype().equals(""), "The edge stereotype should be empty, but was \"" + edge1.stereotype() + "\""),
                () -> assertTrue(edge2.stereotype().equals("stereotype"), "The edge stereotype should be \"stereotype\", but was \"" + edge2.stereotype() + "\""));
    }

    @Test
    void alignment() {
        Edge edge1 = new Edge(null, 1, 1, 2, "", "", "", "", "", "");
        Edge edge2 = new Edge(null, 2, 2, 1, "", "", "", "alignment", "", "");
        assertAll(
                () -> assertTrue(edge1.alignment().equals(""), "The edge alignment should be empty, but was \"" + edge1.alignment() + "\""),
                () -> assertTrue(edge2.alignment().equals("alignment"), "The edge alignment should be \"alignment\", but was \"" + edge2.alignment() + "\""));
    }

    @Test
    void card() {
        Edge edge1 = new Edge(null, 1, 1, 2, "", "", "", "", "", "");
        Edge edge2 = new Edge(null, 2, 2, 1, "", "", "", "", "card", "");
        assertAll(
                () -> assertTrue(edge1.card().equals(""), "The edge card should be empty, but was \"" + edge1.card() + "\""),
                () -> assertTrue(edge2.card().equals("card"), "The edge card should be \"card\", but was \"" + edge2.card() + "\""));
    }

    @Test
    void role() {
        Edge edge1 = new Edge(null, 1, 1, 2, "", "", "", "", "", "");
        Edge edge2 = new Edge(null, 2, 2, 1, "", "", "", "", "", "role");
        assertAll(
                () -> assertTrue(edge1.role().equals(""), "The edge role should be empty, but was \"" + edge1.role() + "\""),
                () -> assertTrue(edge2.role().equals("role"), "The edge role should be \"card\", but was \"" + edge2.role() + "\""));
    }

    /*@Test
    void nodes() {
        Edge edge1 = new Edge(null, 1, 1, 2, "", "", "", "", "", "");
        Edge edge2 = new Edge(null, 2, 2, 1, "", "", "", "", "", "");
        assertAll(
                () -> assertTrue(edge1.role().equals(""), "The edge role should be empty, but was \"" + edge1.role() + "\""),
                () -> assertTrue(edge2.role().equals("role"), "The edge role should be \"card\", but was \"" + edge2.role() + "\""));
    }*/

    @Test
    void addList() {
    }

    @Test
    void removeList() {
    }

    @Test
    void print() {
    }

    @Test
    void unfoldBelow() {
    }

    @Test
    void unfoldAbove() {
    }

}