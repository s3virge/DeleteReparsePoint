import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class DeleteReparsePoint {

    private File[] listOfFiles; //тут будет список всех файлов
    private String command;
    private String folderPath;

    public DeleteReparsePoint(String folderPath) {
        this.folderPath = folderPath;
        this.command = "fsutil reparsepoint delete ";
    }

    public static void main(String[] args) {

        DeleteReparsePoint delRepPoint = new DeleteReparsePoint("/mnt/2BFF77C153FC98B1/OneDrive");

      delRepPoint.getListOfFiles();
      String output = delRepPoint.executeCommand();
//      System.out.println(output);

        //заходим в папку
        //получаем список файлов в этой папке
        //выполняем консольную команду
        //если в списке есть еще папки
        //то сохраняем список папок
        //переходим в следующую папку

    }

    private String executeCommand() {

        StringBuffer output = new StringBuffer();

        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine())!= null) {
                output.append(line + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return output.toString();

    }

    private void getListOfFiles() {
        File folder = new File(folderPath);
        listOfFiles = folder.listFiles();

//        for (int i = 0; i < listOfFiles.length; i++) {
//            if (listOfFiles[i].isFile()) {
//                System.out.println("File " + listOfFiles[i].getName());
//            }
//            else if (listOfFiles[i].isDirectory()) {
//                System.out.println("Directory " + listOfFiles[i].getName());
//            }
//        }
    }

    //@return true if current path has folder
    private boolean isFolder() {
        return false;
    }

    //go to next folder in the list
    private void goToFolder(int folderIndex) {}
}
