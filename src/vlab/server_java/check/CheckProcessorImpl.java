package vlab.server_java.check;

import org.json.JSONObject;
import rlcp.check.ConditionForChecking;
import rlcp.generate.GeneratingResult;
import rlcp.server.processor.check.CheckProcessor;
import rlcp.server.processor.check.PreCheckProcessor;
import rlcp.server.processor.check.PreCheckProcessor.PreCheckResult;
import rlcp.server.processor.check.PreCheckResultAwareCheckProcessor;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Simple CheckProcessor implementation. Supposed to be changed as needed to provide
 * necessary Check method support.
 */
public class CheckProcessorImpl implements PreCheckResultAwareCheckProcessor<String> {
    @Override
    public CheckingSingleConditionResult checkSingleCondition(ConditionForChecking condition, String instructions, GeneratingResult generatingResult) throws Exception {
        //do check logic here
        BigDecimal points = new BigDecimal("1.0");
        String comment = "";
        try{
            JSONObject etalon_result = new JSONObject(generatingResult.getInstructions());
            int r1 = etalon_result.getInt("r1");
            int r2 = etalon_result.getInt("r2");
            int r3 = etalon_result.getInt("r3");
            int r4 = etalon_result.getInt("r4");
            int i1 = etalon_result.getInt("i1");
            int i2 = etalon_result.getInt("i2");
            int i3 = etalon_result.getInt("i3");
            int i4 = etalon_result.getInt("i4");
            JSONObject answers_json = new JSONObject(instructions);
            int r12 = answers_json.getInt("r1");
            int r22 = answers_json.getInt("r2");
            int r32 = answers_json.getInt("r3");
            int r42 = answers_json.getInt("r4");
            int i12 = answers_json.getInt("i1");
            int i22 = answers_json.getInt("i2");
            int i32 = answers_json.getInt("i3");
            int i42 = answers_json.getInt("i4");
            BigDecimal sub = new BigDecimal("0.125");
            if (r1!=r12){
                points= points.subtract(sub);
                comment+=("Ошибка в вычислении действительной части суммы;\n");
            }
            if (i1!=i12){
                points= points.subtract(sub);
                comment+=("Ошибка в вычислении мнимой части суммы;\n");
            }
            if (r2!=r22){
                points= points.subtract(sub);
                comment+=("Ошибка в вычислении действительной части произведения;\n");
            }
            if (i2!=i22){
                points= points.subtract(sub);
                comment+=("Ошибка в вычислении мнимой части произведения;\n");
            }
            if (r3!=r32){
                points= points.subtract(sub);
                comment+=("Ошибка в вычислении действительной части произведения первого числа с целым числом;\n");
            }
            if (i3!=i32){
                points= points.subtract(sub);
                comment+=("Ошибка в вычислении мнимой части произведения первого числа с целым числом;\n");
            }
            if (r4!=r42){
                points= points.subtract(sub);
                comment+=("Ошибка в вычислении действительной части произведения второго числа с целым числом;\n");
            }
            if (i4!=i42){
                points= points.subtract(sub);
                comment+=("Ошибка в вычислении мнимой части произведения первого второго с целым числом;\n");
            }
            if (comment.equals("")){
                comment="Решение верное!";
            }
        } catch (Exception e){
            points = new BigDecimal(0);
            comment = "Неправильный ввод ответов. Попробуйте снова.";
        }
        return new CheckingSingleConditionResult(points, comment);
    }

    @Override
    public void setPreCheckResult(PreCheckResult<String> preCheckResult) {}
}
