package br.com.holelocations.enums;

public enum HoleGravity {
    HIGH("HIGH"),
    MEDIUM("MEDIUM"),
    LOW("LOW");

    private final String holeGravity;

    HoleGravity(String holeGravity) {
        this.holeGravity = holeGravity;
    }

    public String desc() {
        return holeGravity;
    }

    @Override
    public String toString() {
        return holeGravity;
    }
}
