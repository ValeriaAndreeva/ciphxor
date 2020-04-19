package com.company;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;


public class Launcher {

    @Option(name = "-c", metaVar = "Encoding", usage = "Input File encoding key")
    private String Encoding = "";

    @Option(name = "-d", metaVar = "Decoding", usage = "Input File decoding key")
    private String Decoding = "";

    @Option(name = "-o", metaVar = "OutputName", required = true, usage = "Output Name")
    private String outputName;

    @Argument(required = true, metaVar = "InputName", usage = "Input Name")
    private String inputName;

    public static void main(String[] args) {
        new Launcher().launch(args);
    }

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
            if (outputName == null)
                outputName = inputName + ".txt";
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar ciphxor.jar [-c Coding] [-d Decoding] InputName [-o OutputName]");
            parser.printUsage(System.err);
            return;
        }
        try {
            Ciphxor.recode(inputName, outputName, Encoding, Decoding);
            System.out.println("");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}

