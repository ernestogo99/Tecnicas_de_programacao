package org.example.utils.myString;

import java.time.Duration;
import java.time.OffsetDateTime;

public class myStringBuilder {

    public static void main(String[] args) {
        var stringStart= OffsetDateTime.now();

        String stringConcat="";
        String str="java";
        System.out.println(str.replace("a","o"));

        var stringEnd= OffsetDateTime.now();
        System.out.println("String time: " + Duration.between(stringStart,stringEnd).toSeconds());


        var sbStart= OffsetDateTime.now();
        StringBuilder sbContat = new StringBuilder();
        for (int i = 0; i < 200_000; i++) {
            sbContat.append(i);
        }

        var sbEnd= OffsetDateTime.now();
        System.out.println("StringBuilder time: " + Duration.between(sbStart,sbEnd).toSeconds());

        var sbBufferStart= OffsetDateTime.now();
        StringBuffer sbBuffer = new StringBuffer();
        for (int i = 0; i < 200_000; i++) {
            sbBuffer.append(i);
        }

        var sbBufferEnd= OffsetDateTime.now();
        System.out.println("StringBuffer time: " + Duration.between(sbBufferStart,sbBufferEnd).toSeconds());
    }
}
