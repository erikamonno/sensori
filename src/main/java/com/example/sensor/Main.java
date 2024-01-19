package com.example.sensor;

import com.example.sensor.model.dto.RilevazioneDto;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[] array = new int[]{2, 1, 5, 3};
        array = ordinaArray2(array);
        for(int i = 0; i<array.length; i++) {
            System.out.println(array[i]);
        }

        HotelBooking hotelBooking = new HotelBooking(new String[]{"+0A", "+9Z","+4F","-9Z","+3G","+9Z"});
        System.out.println("Il numero di stanze occupate è " + hotelBooking.contaStanzeOccupate());

    }


    private int getNumEccezioni(List<RilevazioneDto> listaRilevazioni) {
        int numEccezioni = 0;
        for (RilevazioneDto rilevazione : listaRilevazioni) {
            if(rilevazione.getEccezione() != null) {
                numEccezioni ++;
            }
        }
        return numEccezioni;
    }


    private int[] ordinaArray1(int[] array) {
        for(int i = 0; i<array.length; i++) {
            for(int j = 0; j<array.length; j++) {
                if(i!=j && array[i]>array[j]) {
                    int a = array[i];
                    array[i] = array[j];
                    array[j] = a;
                }
            }
        }
        return array;
    }



    private static int[] ordinaArray2(int[] array) {
        for(int i = 0; i<array.length; i++) {
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int a = array[j];
                    array[j] = array[j+1];
                    array[j+1] = a;
                }
            }
        }
        return array;
    }

    private long getNumEccezion(List<RilevazioneDto> listaRilevazioni) {
        return listaRilevazioni.stream()
                .filter(rilevazioneDto -> rilevazioneDto.getEccezione() != null)
                .count();
    }
}
