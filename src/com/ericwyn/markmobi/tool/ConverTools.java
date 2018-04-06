package com.ericwyn.markmobi.tool;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * 转换工具
 *
 * Created by Ericwyn on 18-4-6.
 */
public class ConverTools {
    private static Runtime run = Runtime.getRuntime();
    public static void main(String[] args) {
        if(checkKindleGen() && checkPandoc()){
            BuildInfo convert = convert("/media/ericwyn/Work/notes/Leetcode.md");
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

    public static boolean checkKindleGen(){
        try {
            Process p = run.exec(""+ConvertConfig.KINDLEGEN_PATH);// 启动另一个进程来执行命令
            BufferedInputStream in = new BufferedInputStream(p.getInputStream());
            BufferedReader inBr = new BufferedReader(new InputStreamReader(in));
            String lineStr;
            String outputTemp="";
            while ((lineStr = inBr.readLine()) != null){
                outputTemp += lineStr+"\n";
            }
            if(outputTemp.contains("Amazon kindlegen")){
                int amazon_kindlegen = outputTemp.indexOf("Amazon kindlegen");
                int index = outputTemp.indexOf("V");
                System.out.println("check KindleGen successful");
                System.out.println("Version : "+outputTemp.substring(index,index+5));
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public static boolean checkPandoc(){
        try {
            Process p = run.exec("pandoc -v");// 启动另一个进程来执行命令
            BufferedInputStream in = new BufferedInputStream(p.getInputStream());
            BufferedReader inBr = new BufferedReader(new InputStreamReader(in));
            String lineStr;
            String outputTemp="";
            while ((lineStr = inBr.readLine()) != null){
                outputTemp += lineStr+"\n";
            }
            if(outputTemp.contains("This is free software; see the source for copying conditions.")){
                String[] split = outputTemp.split("\n");
                String[] split2 = split[0].split(" ");
                System.out.println("check Pandoc successful");
                System.out.println("version : "+split2[1]);
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 转换要求
     *      1，markdown 的资源文件夹需与markdown 在同一路径下
     *      2，markdown 文档名称不包含空格
     *
     * @param mdFilePath the markdown document file path
     * @return return the mobiFile
     */
    public static BuildInfo convert(String mdFilePath){
        BuildInfo info = new BuildInfo();
        //返回与当前 Java 应用程序相关的运行时对象
        try {
            File file = new File(mdFilePath);
            String htmlNameTemp = file.getAbsolutePath().replace(".md",".html");
            String outPutFileName = file.getName().replace(".md","_convert.mobi");
            Process proc = null;
            try {
                File wd = new File("/bin");
                proc = run.exec("/bin/bash", null,  wd);
            } catch (IOException e) {
                e.printStackTrace();
            } if (proc != null) {
                BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(proc.getOutputStream())), true);
                out.println("cd "+file.getParentFile().getAbsolutePath());
                out.println("pandoc "+mdFilePath+" -o "+htmlNameTemp+" -s --self-contained");
                out.println(ConvertConfig.KINDLEGEN_PATH+" "+htmlNameTemp+" -o "+outPutFileName);
                out.println("exit");
                String outputTemp="";
                try {
                    String line;
                    while ((line = in.readLine()) != null) {
                        outputTemp+=line+"\n";
                    }
                    proc.waitFor();
                    in.close();
                    out.close();
                    proc.destroy();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (outputTemp.contains("Error(kindlegen)")){
                    info.setBuildSuccess(false);
                    info.setMsg(outputTemp);
                    info.setPath(null);
                }else {
                    if (outputTemp.contains("Mobi file built with WARNINGS!")){
                        info.setBuildSuccess(true);
                        info.setMsg(outputTemp);
                        info.setPath(new File(file.getParentFile(),outPutFileName).getAbsolutePath());
                    }
                }
            }
            return info;
        }catch (Exception e){
            e.printStackTrace();
            return info;
        }
    }
}
