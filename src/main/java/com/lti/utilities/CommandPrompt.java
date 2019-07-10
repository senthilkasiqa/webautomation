package com.lti.utilities;




import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandPrompt {

Process p;
ProcessBuilder builder;

public String runCommand(String command) throws InterruptedException, IOException{
    p=Runtime.getRuntime().exec(command);
    BufferedReader r=new BufferedReader(new InputStreamReader(p.getInputStream()));
    String line="";
    String allLine="";
    int i=1;
    while((line=r.readLine()) != null){
        allLine=allLine+""+line+"\n";

        if(line.contains("Console LogLevel: debug") && line.contains("completed")){
            break;
        }
        i++;
    }
    return allLine;
}


}
