import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class DeleteReparsePoint {

    public static void main(String[] args) {

        String folderPath = "/home/s3virge/www";
//        folderPath = "/mnt/2BFF77C153FC98B1/OneDrive";

        execute(new File(folderPath));
    }



    public static void execute(File node){

        System.out.println(node.getAbsoluteFile());
        executeCommand("fsutil reparsepoint delete \"" + node.getAbsoluteFile() + "\"");

        if(node.isDirectory()) {

            String[] subNote = node.list();

            for(String filename : subNote){
                execute(new File(node, filename));
            }
        }

    }

    public static String executeCommand(String command) {

        StringBuffer output = new StringBuffer();

        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine())!= null) {
                output.append(line + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return output.toString();
    }

}
