package org.foop.finalproject.theMessageServer.utils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;
import java.util.function.Predicate;



public class Utility {
    static public <T> T findInArrayList(ArrayList<T> arrayList, Predicate<T> filter) {
        return arrayList.stream().filter(filter).findFirst().orElse(null);
    }

    static public String generateRoomId() {
        return generateId(6);
    }

    static public String generateUserId() {
        return generateId(16);
    }

    static public String generatePlayerId() {
        return generateId(16);
    }

    static public String generateId(int length){
        int leftLimit = 48; // numeral '0';
        int rightLimit = 122; // letter 'z';
        int targetStringLength = length;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }



}