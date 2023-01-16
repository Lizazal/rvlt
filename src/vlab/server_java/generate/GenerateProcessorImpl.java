package vlab.server_java.generate;
import org.json.JSONObject;
import rlcp.generate.GeneratingResult;
import rlcp.server.processor.generate.GenerateProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Simple GenerateProcessor implementation. Supposed to be changed as needed to
 * provide necessary Generate method support.
 */
public class GenerateProcessorImpl implements GenerateProcessor {
    static Random rnd = new Random();
    @Override
    public GeneratingResult generate(String condition) {
        int difficulty_level = rnd.nextInt(2);
        JSONObject answers = new JSONObject();
        JSONObject variant = new JSONObject();
        int r1 = rnd.nextInt(31)-15;
        int r2 = rnd.nextInt(31)-15;
        int i1 = rnd.nextInt(31)-15;
        int i2 = rnd.nextInt(31)-15;
        int n = rnd.nextInt(31)-15;

        int res1 = r1+r2;
        int res2 = i1+i2;
        answers.put("r1", res1);
        answers.put("i1", res2);
        res1 = r1*r2-i1*i2;
        res2=r1*i2+r2*i1;
        answers.put("r2", res1);
        answers.put("i2", res2);
        res1= n * r1;
        res2 = i1 * n;
        answers.put("r3", res1);
        answers.put("i3", res2);
        n = rnd.nextInt(31)-15;
        res1= n * r2;
        res2 = i2 * n;
        answers.put("r4", res1);
        answers.put("i4", res2);
        if (i1<0 && i2<0) {
            variant.put("task1", "("+r1+"+i*("+i1+ ")) + (" + r2 +"+i*("+i2+")) = ");
            variant.put("task2", "("+r1+"+i*("+i1+ ")) * (" + r2 +"+i*("+i2+")) = ");
            variant.put("task3", n + " * ("+r1+"+i*("+i1+ ")) = ");
            variant.put("task4", n + " * ("+r2+"+i*("+i2+ ")) = ");
        } else if (i1<0) {
            variant.put("task1", "("+r1+"+i*("+i1+ ")) + (" + r2 +"+i*"+i2+") = ");
            variant.put("task2", "("+r1+"+i*("+i1+ ")) * (" + r2 +"+i*"+i2+") = ");
            variant.put("task3", n + " * ("+r1+"+i*("+i1+ ")) = ");
            variant.put("task4", n + " * ("+r2+"+i*"+i2+ ") = ");
        } else if (i2<0) {
            variant.put("task1", "("+r1+"+i*"+i1+ ") + (" + r2 +"+i*("+i2+")) = ");
            variant.put("task2", "("+r1+"+i*"+i1+ ") * (" + r2 +"+i*("+i2+")) = ");
            variant.put("task3", n + " * ("+r1+"+i*"+i1+ ") = ");
            variant.put("task4", n + " * ("+r2+"+i*("+i2+ ")) = ");
        } else {
            variant.put("task1", "("+r1+"+i*"+i1+ ") + (" + r2 +"+i*"+i2+") = ");
            variant.put("task2", "("+r1+"+i*"+i1+ ") * (" + r2 +"+i*"+i2+") = ");
            variant.put("task3", n + " * ("+r1+"+i*"+i1+ ") = ");
            variant.put("task4", n + " * ("+r2+"+i*"+i2+ ") = ");
        }

        String text = "Решите выражения с комплексными числами; i обозначает мнимую единицу";
        String instructions = answers.toString();
        String code = variant.toString();
        return new GeneratingResult(text, code, instructions);
    }
}
