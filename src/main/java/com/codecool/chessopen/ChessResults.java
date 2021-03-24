package com.codecool.chessopen;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessResults {

    public List<String> getCompetitorsNamesFromFile(String fileName) {
        List<String> chessContestants = new ArrayList<>();
        Map<String, Integer> results = new HashMap<>();

        try {
            Path path = Paths.get(fileName);
            chessContestants = Files.readAllLines(path, StandardCharsets.UTF_8);

            for (String contestantInput : chessContestants) {
                String[] singleContestantData = contestantInput.split(",");
                String name = singleContestantData[0];
                Integer sumOfPoint = 0;
                for (int i = 1; i < singleContestantData.length; i++) {
                    sumOfPoint += Integer.parseInt(singleContestantData[i]);
                }
                results.put(name, sumOfPoint);
            }
            chessContestants = results.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey).collect(Collectors.toList());
            Collections.reverse(chessContestants);

        } catch (IOException e) {
            System.out.println("File not found!");
        }
        return chessContestants;
    }
}