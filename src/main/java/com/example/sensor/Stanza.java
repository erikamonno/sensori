package com.example.sensor;

import lombok.Data;

import java.util.Objects;

@Data
public class Stanza {

    private Boolean isOccupato;
    private Integer piano;
    private Character stanza;

    public Stanza(String codiceStanza) {
        if(codiceStanza.charAt(0)=='+') {
            this.isOccupato = true;
        }else {
            this.isOccupato = false;
        }
        this.piano = (int) codiceStanza.charAt(1);
        this.stanza = codiceStanza.charAt(2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stanza stanza1 = (Stanza) o;
        return piano.equals(stanza1.piano) && stanza.equals(stanza1.stanza);
    }

    @Override
    public int hashCode() {
        return Objects.hash(piano, stanza);
    }
}
