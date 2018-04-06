package com.ericwyn.markmobi.tool;

/**
 * Created by Ericwyn on 18-4-6.
 */
public class ConvertConfig {
    private static ConfigGet configGet = new ConfigGet("markmobi.cfg",true);
    public static final String KINDLEGEN_PATH
            = configGet.getValue("KINDLEGEN_PATH","KindleGen_path");
//    public static final String
}
