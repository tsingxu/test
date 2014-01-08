package com.tsingxu.test.parser.calculator;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * User: xuhuiqing
 * Date: 14-1-7
 * Time: 下午5:37
 */
public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = null;
        Map<String, Double> var = new HashMap<String, Double>();

        while ((line = scanner.nextLine()) != null) {
            try {
                CommonTokenStream tokenStream = new CommonTokenStream(new CalculatorLexer(new ANTLRInputStream(line)));
                System.out.println(visit(new CalculatorParser(tokenStream).start(), var));
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }

    private static String visit(CalculatorParser.StartContext start, Map<String, Double> var) throws Exception {
        RuleContext first = (RuleContext) start.getChild(0);

        switch (first.getRuleIndex()) {
            case CalculatorParser.RULE_assign:
                CalculatorParser.AssignContext context = (CalculatorParser.AssignContext) first;
                String key = context.NAME().getText();
                double value = visitCal(context.cal(), var);
                var.put(key, value);
                return key + "=" + value;
            case CalculatorParser.RULE_cal:
                return String.valueOf(visitCal((CalculatorParser.CalContext) first, var));
        }

        return "";
    }

    private static double visitCal(CalculatorParser.CalContext cal, Map<String, Double> var) throws Exception {
        double ret = 0D;

        for (int i = 0; i < cal.getChildCount(); i += 2) {
            CalculatorParser.Minus_eContext context = (CalculatorParser.Minus_eContext) cal.getChild(i);
            ret += visitMinus(context, var);
        }


        return ret;
    }

    private static double visitMinus(CalculatorParser.Minus_eContext minus, Map<String, Double> var) throws Exception {
        boolean first = true;
        double ret = 0D;

        for (int i = 0; i < minus.getChildCount(); i += 2) {
            CalculatorParser.Mul_eContext context = (CalculatorParser.Mul_eContext) minus.getChild(i);
            if (first) {
                ret = visitMul(context, var);
                first = false;
            } else {
                ret -= visitMul(context, var);
            }
        }


        return ret;
    }

    private static double visitMul(CalculatorParser.Mul_eContext mul, Map<String, Double> var) throws Exception {
        double ret = 1D;

        for (int i = 0; i < mul.getChildCount(); i += 2) {
            CalculatorParser.Div_eContext context = (CalculatorParser.Div_eContext) mul.getChild(i);
            ret *= visitDiv(context, var);
        }

        return ret;
    }

    private static double visitDiv(CalculatorParser.Div_eContext div, Map<String, Double> var) throws Exception {
        boolean first = true;
        double ret = 0D;

        for (int i = 0; i < div.getChildCount(); i += 2) {
            CalculatorParser.UnitContext context = (CalculatorParser.UnitContext) div.getChild(i);
            if (first) {
                ret = visitUnit(context, var);
                first = false;
            } else {
                ret /= visitUnit(context, var);
            }

        }

        return ret;
    }

    private static double visitUnit(CalculatorParser.UnitContext unit, Map<String, Double> var) throws Exception {
        if (unit.getChildCount() == 3) {
            return visitUnit((CalculatorParser.UnitContext) unit.getChild(1), var);
        }

        final String text = unit.getChild(0).getText();
        switch (((TerminalNode) unit.getChild(0)).getSymbol().getType()) {
            case CalculatorParser.NUMBER:
                return Double.parseDouble(text);
            case CalculatorParser.NAME:
                Double d = var.get(text);
                if (d == null) {
                    throw new Exception("不存在变量:" + text);
                }
                return d;
        }

        return 0;
    }

}
