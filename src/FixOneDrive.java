import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;


public class FixOneDrive  extends Application {

    private String path = null;

    public static void main(String[] args) {

        Application.launch(args);

        String folderPath = "/home/s3virge/www";
        folderPath = "/mnt/2BFF77C153FC98B1/OneDrive";
        folderPath = "D:\\OneDrive";

//        DeleteReparsePoint deleteReparsePoint = new DeleteReparsePoint();
//        deleteReparsePoint.execute(new File(folderPath));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        final DirectoryChooser directoryChooser = new DirectoryChooser();
        configuringDirectoryChooser(directoryChooser);

        TextArea textArea = new TextArea();
        textArea.setMinHeight(70);

        Label label = new Label("delete reparse point from OneDrive folder using fsutil");
        Button btnBrows = new Button("Open DirectoryChooser and select a directory");
        Button btnExecute = new Button("Execute");

        btnBrows.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                File dir = directoryChooser.showDialog(primaryStage);
                if (dir != null) {
                    path = dir.getAbsolutePath();
                    textArea.setText(path);
                }
                else {
                    textArea.setText(null);
                }
            }
        });

        btnExecute.setOnAction(event -> {
                    DeleteReparsePoint delRepPoint = new DeleteReparsePoint();
                    delRepPoint.execute(new File(path), textArea);
                });

        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setSpacing(5);

        root.getChildren().addAll(label, textArea, btnBrows, btnExecute);

        Scene scene = new Scene(root, 400, 200);

        primaryStage.setTitle("Fix OneDrive Folder");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void configuringDirectoryChooser(DirectoryChooser directoryChooser) {
        // Set title for DirectoryChooser
        directoryChooser.setTitle("Select Some Directories");

        // Set Initial Directory
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
    }

}
