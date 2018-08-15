package main;

import commands.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ParseCode {

    public static void main(String... args){
        Base b = parseCode("(composite\n" +
                           "\t(composite\n" +
                           "\t\tMOVE_UP\n" +
                           "\t\tMOVE_DOWN)\n" +
                           "\t(WHILE GET_TILE_HERE != GREEN\n" +
                           "\t\tMOVE_DOWN))");
        System.out.println(b.toString());
    }

    public static Base parseCode(String string) {
        string = string.replaceAll("\n", " ")
                       .replaceAll("\t", " ")
                       .replaceAll(" \\)", ")")
                       .replaceAll("\\( ", "(")
                       .replaceAll("\\) ", ")")
                       .replaceAll("  ", " ")
                       .toUpperCase();
        if (string.startsWith("(")) {
            List<String> args = splitByArgs(string);
            String func = args.get(0);
            if (func.equals("COMPOSITE")) {
                return new CmdComposite((Command) parseCode(args.get(1)),
                                        (Command) parseCode(args.get(2)));
            } else if (func.equals("IF")) {
                Base arg1 = parseCode(args.get(1));
                Base arg2 = parseCode(args.get(3));
                Command arg3 = (Command) parseCode(args.get(4));
                if (args.get(2).equals("==")) {
                    return new CmdIf(arg1, arg2, arg3);
                } else {
                    return new CmdIfNot(arg1, arg2, arg3);
                }
            } else if (func.equals("WHILE")) {
                Base arg1 = parseCode(args.get(1));
                Base arg2 = parseCode(args.get(3));
                Command arg3 = (Command) parseCode(args.get(4));
                if (args.get(2).equals("==")) {
                    return new CmdWhile(arg1, arg2, arg3);
                } else {
                    return new CmdWhileNot(arg1, arg2, arg3);
                }
            } else {
                System.out.println("returned null");
                return null;
            }
        } else {
            return parseConstant(string);
        }
    }

    private static List<String> splitByArgs(String string) {
        List<String> args = new ArrayList<>();
        string = string.substring(1, string.length() - 1)
                        .replaceAll("\\)", "\\) ");
        int parenCount = 0;
        int start_index = 0;
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c == '(') {
                parenCount += 1;
            }
            if (c == ')') {
                parenCount -= 1;
            }
            if (parenCount == 0 && c == ' ') {
                args.add(string.substring(start_index, i).trim());
                start_index = i;
            }
        }
        args.add(string.substring(start_index, string.length()).trim());
        List<String> filteredArgs = new ArrayList<>();
        for(String s : args){
            if(s.length() != 0){
                filteredArgs.add(s);
            }
        }
        return filteredArgs;
    }

    private static Set<Base> constantBases = new HashSet<>();

    static {
        constantBases.addAll(Value.ALL_VALUES);
        constantBases.add(CmdMove.MOVE_DOWN);
        constantBases.add(CmdMove.MOVE_UP);
        constantBases.add(CmdMove.MOVE_LEFT);
        constantBases.add(CmdMove.MOVE_RIGHT);
        constantBases.add(CmdMove.MOVE_HERE);
        constantBases.add(CmdGetTile.GET_DOWN_TILE);
        constantBases.add(CmdGetTile.GET_UP_TILE);
        constantBases.add(CmdGetTile.GET_LEFT_TILE);
        constantBases.add(CmdGetTile.GET_RIGHT_TILE);
        constantBases.add(CmdGetTile.GET_HERE_TILE);
    }

    private static Base parseConstant(String string) {
        for (Base b : constantBases) {
            if (b.toString().toUpperCase().equals(string)) {
                return b;
            }
        }
        System.out.println("return constant null : " + string);
        return null;
    }

}
