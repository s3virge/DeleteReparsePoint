import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class DeleteReparsePoint {

    public DeleteReparsePoint() {
    }

    public static void main(String[] args) {

        String folderPath = "/home/s3virge/www";
//        folderPath = "/mnt/2BFF77C153FC98B1/OneDrive";

        DeleteReparsePoint delRepPoint = new DeleteReparsePoint();

        File[] listOfFiles = delRepPoint.getListOfFiles(folderPath);

        delRepPoint.executeCommandForEach(listOfFiles, "fsutil reparsepoint delete ");

//        String output = delRepPoint.executeCommand("fsutil reparsepoint delete \"" + listOfFiles[1] + "\"");
//      System.out.println(output);

        //заходим в папку
        //получаем список файлов в этой папке
        //выполняем консольную команду
        //если в списке есть еще папки
        //то сохраняем список папок
        //переходим в следующую папку

    }

    private final void executeCommandForEach(File[] files, String command) {
        for (File currentFile : files) {
            executeCommand(command + "\"" + currentFile.getAbsolutePath() + "\"");
        }
    }

    private String executeCommand(String command) {

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

    private File[] getListOfFiles(String folderPath) {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                System.out.println("File " + files[i].getName());
            }
            else if (files[i].isDirectory()) {
                System.out.println("Directory " + files[i].getName());
            }
        }

        return files;
    }

    //@return true if current path has folder
    private boolean isFolder() {
        return false;
    }

    //go to next folder in the list
    private void goToFolder(int folderIndex) {}
}
