package br.com.holelocations.enums;

public enum HoleStatus {
        ABERTO("ABERTO"),
        FECHADO("FECHADO"),
        CANCELADO("CANCELADO");

        private final String status;

        HoleStatus(String status) {
                this.status = status;
        }

        public String getStatus() {
                return status;
        }

        @Override
        public String toString() {
                return status;
        }
}
