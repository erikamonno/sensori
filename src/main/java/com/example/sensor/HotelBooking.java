package com.example.sensor;

import java.util.ArrayList;
import java.util.List;

public class HotelBooking {
    private String[] rooms;
    private Stanza[] stanza;

    public HotelBooking(String[] rooms) {
        this.rooms = rooms;
    }

    public int contaStanzeOccupate() {
        List<String> listaStanzeOccupate = new ArrayList<>();
        for(int i = 0; i< rooms.length; i++) {
           if(rooms[i].contains("+")) {
               listaStanzeOccupate.add(rooms[i]);
           }else{
               listaStanzeOccupate.remove(rooms[i].replace("-", "+"));
           }
        }
        return listaStanzeOccupate.size();
    }

}
