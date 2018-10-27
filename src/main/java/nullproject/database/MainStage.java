package nullproject.database;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainStage {
    //static Controller controller = new Controller();
    // TwoPlayers tp;
    private Pane pane;
    private Scene scene;

    private Button login;
    private Button registation;
    private Button info;
    private Button exit;
    private DatabaseHandler db;
    private Image menu;
    private ImageView enterAcc;
    private TextField nickname;
    private TextField pass;
    private static MainStage scene_one = null;
    private MainStage(){
    }

    public static MainStage getInstance(){
        if(scene_one == null){
            scene_one.scene_one = new MainStage();
        }
        return scene_one.scene_one;
    }


    public void start(Stage pStage)  {
        pane = new Pane();//створення контейнера

        scene = new Scene(pane,960,540);//створення сцени
        scene.setFill(Color.BLACK);//заливка сцени
        menu = new Image(getClass().getResourceAsStream("../../scene/auth/enterAcc.png"));

        db = new DatabaseHandler();



        enterAcc = new ImageView();
        enterAcc.setImage(menu);
        enterAcc.fitWidthProperty().bind(scene.widthProperty());
        enterAcc.fitHeightProperty().bind(scene.heightProperty());

        nickname = new TextField();
        pass = new TextField();
        nickname.setText("");
        pass.setText("");

        nickname.setLayoutX(364);
        nickname.setLayoutY(236);

        pass.setLayoutX(364);
        pass.setLayoutY(326);

        nickname.setPrefWidth(250);
        pass.setPrefWidth(250);


        login = new Button("", new ImageView(new Image(getClass().getResourceAsStream("../../scene/auth/loginButton.png"),
                269 ,59, false, false)));
        login.setStyle("-fx-background-color: transparent;");
        login.setPrefSize(269, 59);
        login.setLayoutX(346);
        login.setLayoutY(358);
        login.setOnAction(event -> {
            if((nickname.getText().equals("")) || (nickname.getText().equals(" ")) ||
               (pass.getText().equals(""))  || (pass.getText().equals(" "))){
                    System.out.println("Error");
            } else {
                if(db.getUser(nickname.getText(), pass.getText())){

                } else{
                    System.out.println("неправильні дані");
                }
                System.out.println("OK!");
            }
        });

        registation = new Button("", new ImageView(new Image(getClass().getResourceAsStream("../../scene/auth/regButton.png"),
                269 ,59, false, false)));
        registation.setStyle("-fx-background-color: transparent;");
        registation.setPrefSize(269, 59);
        registation.setLayoutX(346);
        registation.setLayoutY(400);
        registation.setOnAction(event -> {
            login.setVisible(false);
            registation.setVisible(true);
            Registration.getInstance().start(pStage);
        });


        registation.setOnMouseExited(event -> { // обробка натиску на кнопку
            registation.setPrefSize(269, 59);
        });
        registation.setOnMouseMoved(event -> { // обробка натиску на кнопку

            registation = new Button("", new ImageView(new Image(getClass().getResourceAsStream("../../scene/auth/regButton.png"),
                    300 ,80, false, false)));
        });
        pane.getChildren().addAll(enterAcc, nickname, pass, login, registation);

        pStage.setResizable(false);
        pStage.setScene(scene);



    }

}
