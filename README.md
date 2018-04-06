# Markmobi
一款将 Markdown 文档转换为 mobi 文件的工具

## Markdown 文档要求
 - 文档的资源文件夹（如图片存放文件夹等），需要与markdown 放在同一目录下，且使用文档中需要使用相对路径进行引用
 - 文档的命名不可包含空格
 
## 转换过程
 - markdown --> HTML
 - HTML ------> Mobi

## 依赖
### 1 , Pandoc
 - 安装方法
 
       apt-get install pandoc
    
### 2 , KindleGen
 - linux 版本在`kindleGen_linux`文件夹下
 
 
## 配置
 - `markmobi.cfg` 文件，需要设置 Kindlegen 的绝对路径

       KINDLEGEN_PATH = /media/ericwyn/kindlegen ;



## 运行示例
 - 转换成功

        check KindleGen successful
        Version : V2.9 
        check Pandoc successful
        version : 1.16.0.2
        build success
        /media/Java并发_convert.mobi
        
        Process finished with exit code 0
        
 - 转换失败
 
        check KindleGen successful
        Version : V2.9 
        check Pandoc successful
        version : 1.16.0.2
        build fail
        
        ----------------------- log -----------------------
        
        
        *************************************************************
         Amazon kindlegen(Linux) V2.9 build 1028-0897292 
         A command line e-book compiler 
         Copyright Amazon.com and its Affiliates 2014 
        *************************************************************
        
        Error(kindlegen):E30005: Could not find file  题解_convert.mobi
        
        
        Process finished with exit code 0