package nullproject.database;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;


public class Registration {

    private ImageView regImg;
    private Image menu;

    private TextField nickname;
    private TextField pass;
    private TextField email;
    private TextField codTF;

    private Button accpet;
    private Button accpetCod;

    private SendEmail sendEmail;
    private DatabaseHandler db;

    private static Registration ourInstance = new Registration();

    public static Registration getInstance() {
        return ourInstance;
    }

    private Registration() {
    }


    public void start(Stage stage) {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 960, 540);
        scene.setFill(Color.BLACK);//заливка сцени
        menu = new Image(getClass().getResourceAsStream("../../scene/auth/regImage.png"));

        if (db == null) db = new DatabaseHandler();


        regImg = new ImageView();
        regImg.setImage(menu);
        regImg.fitWidthProperty().bind(scene.widthProperty());
        regImg.fitHeightProperty().bind(scene.heightProperty());


        nickname = new TextField();
        nickname.setText("");

        pass = new TextField();
        pass.setText("");

        email = new TextField();
        email.setText("");

        accpet = new Button("OK");

        codTF = new TextField("Введіть код, як ми надіслали вам на пошту");
        accpetCod = new Button("OK");

        nickname.setLayoutX(364);
        nickname.setLayoutY(185);

        pass.setLayoutX(364);
        pass.setLayoutY(266);

        email.setLayoutX(364);
        email.setLayoutY(346);

        accpet.setLayoutX(420);
        accpet.setLayoutY(400);

        codTF.setLayoutX(365);
        codTF.setLayoutY(410);
        codTF.setVisible(false);
        codTF.setOnMouseClicked(event -> {
            if (codTF.getText().equals("Введіть код, який ми надіслали вам на пошту")) {
                codTF.setText("");
            }
        });


        accpetCod.setLayoutX(420);
        accpetCod.setLayoutY(460);
        accpetCod.setVisible(false);

        codTF.setPrefHeight(30);
        codTF.setPrefWidth(250);
        nickname.setPrefWidth(250);
        pass.setPrefWidth(250);
        email.setPrefWidth(250);
        accpet.setPrefWidth(140);
        accpet.setPrefHeight(40);

        accpetCod.setPrefWidth(140);
        accpetCod.setPrefHeight(40);

        accpet.setOnAction(event -> {
            if ((!db.getUser(email.getText(), pass.getText())) && email.getText().contains("@") && !(email.getText().equals("") || pass.getText().equals("")) && !(email.getText().contains(" ") || pass.getText().contains(" ")) && !(nickname.getText().equals("") || nickname.getText().contains(" "))) {
                sendEmail = new SendEmail(email.getText());
                accpet.setVisible(false);
                accpetCod.setVisible(true);
                codTF.setVisible(true);
            } else if (pass.getText().equals("") || pass.getText().contains(" ")) {
                pass.setText("Введіть коректний пароль");
                pass.setOnMouseClicked(e -> {
                    if (pass.getText().equals("Введіть коректний пароль")) {
                        pass.setText("");
                    }
                });
            } else if (email.getText().equals("") || email.getText().contains(" ") || !email.getText().contains("@")) {
                email.setText("Таку пошту зареєстровати неможливо");
                email.setOnMouseClicked(e -> {
                    if (email.getText().equals("Таку пошту зареєстровати неможливо")) {
                        email.setText("");
                    }
                });
            } else if (nickname.getText().equals("") || nickname.getText().contains(" ")) {
                nickname.setText("Такий нікнейм недопустимий");
                nickname.setOnMouseClicked(e -> {
                    if (nickname.getText().equals("Такий нікнейм недопустимий")) {
                        nickname.setText("");
                    }
                });
            }
        });

        codTF.setOnMouseClicked(e -> {
            if (codTF.getText().equals("Введіть код, як ми надіслали вам на пошту")) {
                codTF.setText("");
            }
        });

        accpetCod.setOnAction(event -> {
            if (codTF.getText().equals(sendEmail.requestCod)) {
                if (!db.getUser(email.getText(), pass.getText())) db.addUser(email.getText(), pass.getText(), 0, "");
            } else {
                codTF.setText("Коди не співпадають!");
                codTF.setOnMouseClicked(e -> {
                    if (codTF.getText().equals("Коди не співпадають!")) {
                        codTF.setText("");
                    }
                });
            }
        });

        stage.setScene(scene);
        pane.getChildren().addAll(regImg, nickname, pass, email, accpet, accpetCod, codTF);
        stage.setResizable(false);
        stage.show();

    }
}
