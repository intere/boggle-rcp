package com.intere.rcp.boggle.core.model;

/**
 * Model that keeps track of an index of a walk.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class Position {
    private int i;
    private int j;

    /**
     * Constructor that sets the i and j indexes.
     * 
     * @param i
     * @param j
     */
    public Position(int i, int j) {
        setI(i);
        setJ(j);
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }
    
    @Override
    public String toString() {
        return i + ", " + j;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + i;
        result = prime * result + j;
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Position other = (Position) obj;
        if (i != other.i) {
            return false;
        }
        if (j != other.j) {
            return false;
        }
        return true;
    }
}
