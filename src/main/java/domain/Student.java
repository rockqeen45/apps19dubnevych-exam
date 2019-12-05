package domain;

import json.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {
    private List<Tuple<String, Integer>> exams;

    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        super(name, surname, year);
        this.exams = new LinkedList<>();
        this.exams.addAll(Arrays.asList(exams));
    }

    public JsonObject toJsonObject() {
        JsonObject json = super.toJsonObject();
        List<Json> jsonExams = new LinkedList<>();
        for (Tuple<String, Integer> tuple: exams) {
            jsonExams.add(new JsonObject(
                    new JsonPair("course", new JsonString(tuple.key)),
                    new JsonPair("mark", new JsonNumber(tuple.value)),
                    new JsonPair("passed", new JsonBoolean(tuple.value >= 3))
            ));
        }
        JsonArray examsArr = new JsonArray(jsonExams);

        json.add(new JsonPair("exams", examsArr));
        return json;
    }
}
