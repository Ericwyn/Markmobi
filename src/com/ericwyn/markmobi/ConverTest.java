package com.ericwyn.markmobi;

import com.ericwyn.markmobi.tool.BuildInfo;
import com.ericwyn.markmobi.tool.ConverTools;

/**
 * Created by Ericwyn on 18-4-6.
 */
public class ConverTest {
    public static void main(String[] args) {
        if(ConverTools.checkKindleGen() && ConverTools.checkPandoc()){
            BuildInfo convert = ConverTools.convert("/media/ericwyn/Work/notes/Leetcode.md");
            if (convert.isBuildSuccess()){
                System.out.println("build success");
                System.out.println(convert.getPath());
            }else {
                System.out.println("build fail");
                System.out.println();
                System.out.println("----------------------- log -----------------------");
                System.out.println();
                System.out.println(convert.getMsg());
            }
        }else {
            System.out.println("check KindleGen and Pandoc error");
        }
    }
}
