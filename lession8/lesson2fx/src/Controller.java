import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Controller {

    private static final double ICON_WIDTH = 50.0;
    private static final double ICON_HEIGHT = 50.0;

    private static final double ICON_TEXT_MARGIN = 10.0;

    private static final double ICON_CHAT_USERS_LEFT_MARGIN = 20.0;

    private static final Font FONT = new Font("Berlin Sans FB Demi Bold", 14.0);

    private static final String IP_ADDR = "localhost";
    private static final int PORT = 12345;

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    private boolean isAuthorized;
    private String login;

    private Thread mainThread;

    @FXML
    HBox chatPanel;

    @FXML
    VBox authPanel;

    @FXML
    Label authLabel;

    @FXML
    TextField loginField;

    @FXML
    TextField passwordField;

    @FXML
    VBox contactsPane;

    @FXML
    TextField searcher;

    @FXML
    Pane conversationLabelPane;

    @FXML
    ScrollPane dialogScrollPane;

    @FXML
    Pane dialogPane;

    @FXML
    TextField printedMsg;

    @FXML
    Pane sendMsgPane;

    // Ниже кошмар
    @FXML
    private void getOptions() {
//        ObservableList<Node> rootPaneChildren = root.getChildren();
//
//        Pane optionPane = new Pane();
//
//        optionPane.setPrefHeight(200);
//        optionPane.setPrefWidth(300);
//        optionPane.setLayoutX((root.getPrefWidth() - optionPane.getPrefWidth()) / 2);
//        optionPane.setLayoutY((root.getPrefHeight() - optionPane.getPrefHeight()) / 2);
//        optionPane.setStyle("-fx-background-color: #B0A6A6;");
//        optionPane.setId("OptionPane");
//
//        Label optionLabel = new Label("Option");
//
//        optionLabel.setPrefWidth(optionPane.getPrefWidth());
//        optionLabel.setPrefHeight(20);
//        optionLabel.setText("Option");
//        optionLabel.setFont(FONT);
//        optionLabel.setStyle("-fx-background-color: #E4E2E2;\n-fx-font-weight: bold;");
//
//        Label loginLabel = new Label();
//
//        loginLabel.setPrefHeight(20);
//        loginLabel.setPrefWidth(optionPane.getPrefWidth() * 0.75);
//        loginLabel.setLayoutX((optionPane.getPrefWidth() - loginLabel.getPrefWidth()) / 2);
//        loginLabel.setLayoutY(40);
//        loginLabel.setText("Enter you login...");
//        loginLabel.setTextAlignment(TextAlignment.CENTER);
//        loginLabel.setFont(FONT);
//
//        TextField loginField = new TextField();
//
//        loginField.setPrefHeight(20);
//        loginField.setPrefWidth(optionPane.getPrefWidth() * 0.75);
//        loginField.setLayoutX((optionPane.getPrefWidth() - loginField.getPrefWidth()) / 2);
//        loginField.setLayoutY(loginLabel.getLayoutY() + loginField.getPrefHeight());
////        loginField.setText(user.getLogin());
//        loginField.setFont(FONT);
//
//        Label imageLabel = new Label();
//
//        imageLabel.setPrefHeight(20);
//        imageLabel.setPrefWidth(optionPane.getPrefWidth() * 0.75);
//        imageLabel.setLayoutX((optionPane.getPrefWidth() - imageLabel.getPrefWidth()) / 2);
//        imageLabel.setLayoutY(loginField.getLayoutY() + loginField.getPrefHeight() + imageLabel.getPrefHeight());
//        imageLabel.setText("Enter path to your avatar...");
//        imageLabel.setTextAlignment(TextAlignment.CENTER);
//        imageLabel.setFont(FONT);
//
//        TextField imageField = new TextField();
//
//        imageField.setPrefHeight(20);
//        imageField.setPrefWidth(optionPane.getPrefWidth() * 0.75);
//        imageField.setLayoutX((optionPane.getPrefWidth() - imageField.getPrefWidth()) / 2);
//        imageField.setLayoutY(imageLabel.getLayoutY() + imageField.getPrefHeight());
//        imageField.setFont(FONT);
//
//        Button saveButton = new Button();
//
//        saveButton.setPrefHeight(20);
//        saveButton.setPrefWidth(optionPane.getPrefWidth() * 0.4);
//        saveButton.setLayoutX((optionPane.getPrefWidth() - saveButton.getPrefWidth()) / 2);
//        saveButton.setLayoutY(imageField.getLayoutY()+ imageField.getPrefHeight() + saveButton.getPrefHeight());
//        saveButton.setText("Save and exit");
//        saveButton.setTextAlignment(TextAlignment.CENTER);
//        saveButton.setFont(FONT);
//
////        saveButton.setOnMouseClicked(mouseEvent -> {
////            HashSet<Group> userGroups = user.getUserGroups();
////
////            for (Group gr : userGroups)
////                GroupController.deleteUserFromGroup(gr.getLogin(), this.user);
////
////            UserController.updateUser(this.user, loginField.getText(), new Image("images/man.png"));
////
////            String newLogin = loginField.getText();
//////            String newImg = imageField.getText();
////            // С заменой картинки пока накладка
////            if (newLogin != null || !newLogin.equals("")) this.user.setLogin(newLogin);
////            else this.user.setLogin("Guest");
//////            if (newImg != null || !newImg.equals("")) this.user.setImg(new Image(newImg));
//////            else this.user.setImg(null);
////
////            for (Group gr : userGroups)
////                GroupController.updateGroup(gr.getLogin(), this.user);
////
////            for (Node node : rootPaneChildren)
////                if (node.getId() != null && node.getId().equals("OptionPane")) {
////                    rootPaneChildren.remove(node);
////                    break;
////                }
////
////            getChatConversations();
////        });
//
//        ObservableList<Node> optionPaneChildren = optionPane.getChildren();
//
//        optionPaneChildren.add(optionLabel);
//        optionPaneChildren.add(loginLabel);
//        optionPaneChildren.add(loginField);
//        optionPaneChildren.add(imageLabel);
//        optionPaneChildren.add(imageField);
//        optionPaneChildren.add(saveButton);
//
//        rootPaneChildren.add(optionPane);
    }

    @FXML
    private void findContact() {
        ObservableList<Node> contactsPaneChildren = contactsPane.getChildren();
        contactsPaneChildren.remove(0, contactsPaneChildren.size());

//        Conversation conv = findChatConversation(searcher.getText());
//        searcher.clear();
//
//        if (conv == null) return;
//
//        createConversation(chatConversationsPaneChildren, conv);
    }

    private void initDialog(Pane contactPane) {
        if (!sendMsgPane.isVisible()) sendMsgPane.setVisible(true);
        if (!sendMsgPane.isManaged()) sendMsgPane.setManaged(true);

        getSelectedConversation(contactPane);

        try {
            out.writeUTF("/wanttochating:" + ((Label) ((Pane) conversationLabelPane.getChildren().get(0)).getChildren().get(2)).getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getSelectedConversation(Pane selectedConvPane) {
        clearChatPanel();
        createConversationLabelPane(selectedConvPane);
    }

    synchronized private void createConversationChatPane() {
        try {
            StringBuilder sb = new StringBuilder();
            ObservableList<Node> dialogPaneChildren = dialogPane.getChildren();

            while (true) {
                String str = in.readUTF();

                if (str.startsWith("/dialogmsgsnext") || str.endsWith("/dialogmsgsnext")) {
                    sb.append(str.replace("/dialogmsgsnext", ""));
                } else sb.append(str);
                if (str.endsWith("/dialogmsgsend")) break;
            }

            String[] msgs = sb.toString().split(":");
            for (int i = 1; i < msgs.length - 1; i += 3) {
                if (msgs[i].equals(login)) createMsgPane(dialogPaneChildren, msgs[i + 1], msgs[i + 2], "right");
                else createMsgPane(dialogPaneChildren, msgs[i + 1], msgs[i + 2], "left");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createMsgPane(ObservableList<Node> dialogPaneChildren, String base64Img,
                               String reqMsg, String pos) {
        Pane receivedMessagePane = new Pane();
        ObservableList<Node> recievedMessagePaneChildren = receivedMessagePane.getChildren();

        receivedMessagePane.setPrefWidth(dialogScrollPane.getPrefWidth() - 20);

        ImageView convImg = null;
        try {
            convImg = new ImageView(SwingFXUtils.toFXImage(
                    ImageIO.read(new ByteArrayInputStream(Base64.decode(base64Img))), null));
        } catch (IOException e) {
            e.printStackTrace();
        }

        convImg.setFitHeight(40);
        convImg.setFitWidth(40);
        convImg.setLayoutX(pos.equals("left") ? 10 : receivedMessagePane.getPrefWidth() - convImg.getFitWidth());
        convImg.setLayoutY(10);

        VBox msgBox = new VBox();

        msgBox.setPrefWidth(250);
        msgBox.setLayoutX(pos.equals("left") ? convImg.getFitHeight() + 20 : convImg.getLayoutX() - msgBox.getPrefWidth() - 20);
        msgBox.setLayoutY(7);

        Text msg = new Text(reqMsg);

        msg.setWrappingWidth(250);
        msg.setTextAlignment(pos.equals("left") ? TextAlignment.LEFT : TextAlignment.RIGHT);

        msgBox.getChildren().add(msg);

        recievedMessagePaneChildren.add(convImg);
        recievedMessagePaneChildren.add(msgBox);

        Platform.runLater(() -> dialogPaneChildren.add(receivedMessagePane));
    }

    private void createConversationLabelPane(Pane selectedConvPane) {
        ObservableList<Node> conversationLabelPaneChildren = conversationLabelPane.getChildren();
        ObservableList<Node> selectedConvPaneChildren = selectedConvPane.getChildren();

        Pane currConvPane = new Pane();
        ObservableList<Node> currConvPaneChildren = currConvPane.getChildren();

        ImageView img = new ImageView(((ImageView) selectedConvPaneChildren.get(0)).getImage());

        img.setFitHeight(40);
        img.setFitWidth(40);
        img.setLayoutX(15);
        img.setLayoutY((conversationLabelPane.getPrefHeight() - img.getFitHeight()) / 2);

        Label name = new Label(((Label) selectedConvPaneChildren.get(1)).getText());

        name.setPrefHeight(20);
        name.setPrefWidth(150);
        name.setLayoutX(img.getLayoutX() + img.getFitWidth() + 15);
        name.setLayoutY(img.getLayoutY() * 0.75);
        name.setFont(FONT);

        Label login = new Label(((Label) selectedConvPaneChildren.get(2)).getText());

        login.setPrefHeight(20);
        login.setPrefWidth(150);
        login.setLayoutX(name.getLayoutX());
        login.setLayoutY(name.getLayoutY() + 15);
        login.setFont(new Font("Berlin Sans FB Demi Bold", 10.0));

        currConvPaneChildren.add(img);
        currConvPaneChildren.add(name);
        currConvPaneChildren.add(login);

        conversationLabelPaneChildren.add(currConvPane);
    }

    private void clearChatPanel() {
        ObservableList<Node> cp1 = conversationLabelPane.getChildren();
        ObservableList<Node> cp2 = dialogPane.getChildren();

        cp1.remove(0, cp1.size());
        cp2.remove(0, cp2.size());
    }

    @FXML
    private void sendMsg() {
        if (conversationLabelPane.getChildren().size() > 0) {
            String dstLogin = ((Label) ((Pane) conversationLabelPane.getChildren().get(0)).getChildren().get(2)).getText();

            try {
                out.writeUTF("/sendto:" + dstLogin + ":" + printedMsg.getText());
                printedMsg.clear();
                printedMsg.requestFocus();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void tryToAuth() {
        if (socket == null || socket.isClosed()) connectToServer();

        try {
            login = loginField.getText();
            String password = DatatypeConverter.printHexBinary(MessageDigest.getInstance("MD5").digest(
                    passwordField.getText().getBytes(StandardCharsets.UTF_8)));

            out.writeUTF("/auth:" + login + ":" + password);

            loginField.clear();
            passwordField.clear();
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
    }

    private void connectToServer() {
        try {
            socket = new Socket(IP_ADDR, PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            mainThread = new Thread(() -> {
                try {
                    while (true) {
                        String str = in.readUTF();

                        if (str.startsWith("/authok")) {
                            setAuthorized(true);
                            break;
                        } else {
                            Platform.runLater(() -> {
                                authLabel.setText(str);
                                authLabel.setVisible(true);
                            });
                            login = null;
                        }
                    }

                    if (isAuthorized) out.writeUTF("/getcontacts");

                    StringBuilder sb = new StringBuilder();
                    boolean isAllContactsReceived = false;

                    while (isAuthorized) {
                        String str = in.readUTF();

                        if (str.startsWith("/getcontactsok") || str.startsWith("/getcontactsnext")) {
                            if (str.startsWith("/getcontactsok"))
                                str = str.replace("/getcontactsok", "");

                            if (str.startsWith("/getcontactsnext") || str.endsWith("/getcontactsnext"))
                                str = str.replace("/getcontactsnext", "");

                            if (str.endsWith("/getcontactsend")) {
                                str = str.replace("/getcontactsend", "");
                                isAllContactsReceived = true;
                            }

                            sb.append(str);
                        }

                        if (isAllContactsReceived) {
                            String[] tokens = sb.toString().split(":");
                            int nContacts = (tokens.length - 1) / 3;
                            String[] logins = new String[nContacts];
                            String[] names = new String[nContacts];
                            Image[] images = new Image[nContacts];

                            getContactsFromTokens(tokens, logins, names, images);
                            Platform.runLater(() -> createContacts(logins, names, images));

                            break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            mainThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getContactsFromTokens(String[] tokens, String[] logins, String[] names,
                                       Image[] images) throws IOException {
        for (int i = 1; i < tokens.length; i += 3) {
            int j = (i - 1) / 3;

            logins[j] = tokens[i];
            names[j] = tokens[i + 1];

            byte[] base64DecodeImage = Base64.decode(tokens[i + 2]);
            images[j] = SwingFXUtils.toFXImage(ImageIO.read(new ByteArrayInputStream(base64DecodeImage)), null);
        }
    }

    @FXML
    private void createContacts(String[] logins, String[] names, Image[] images) {
        ObservableList<Node> contactsPaneChildren = contactsPane.getChildren();

        for (int i = 0; i < logins.length; i++) {
            Pane contactPane = new Pane();
            ObservableList<Node> contactPaneChildren = contactPane.getChildren();

            contactPane.setPrefHeight(90);
            contactPane.setOnMouseMoved(mouseEvent -> contactPane.setStyle("-fx-background-color: #0A1C27;"));
            contactPane.setOnMouseReleased(mouseEvent -> {
                if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                    initDialog(contactPane);
                    createConversationChatPane();

                    dialogScrollPane.setVvalue(1.0);
                    contactPane.layout();
                    contactPane.applyCss();
                } else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                    for (Node e : contactPaneChildren) {
                        e.setVisible(false);
                        e.setManaged(false);
                    }

                    VBox blockBox = new VBox();
                    ObservableList<Node> blockBoxChildren = blockBox.getChildren();

                    Button blockButton = new Button("Block user");

                    blockButton.setMaxHeight(Double.MAX_VALUE);
                    blockButton.setOnMouseReleased(blockMouseEvent -> sendMsg("/blockuser:" +
                            ((Label) contactPane.getChildren().get(2)).getText()));

                    Button unblockButton = new Button("Unblock user");

                    unblockButton.setMaxHeight(Double.MAX_VALUE);
                    unblockButton.setOnMouseReleased(unblockMouseEvent -> sendMsg("/unblockuser:" +
                            ((Label) contactPane.getChildren().get(2)).getText()));

                    blockBoxChildren.add(blockButton);
                    blockBoxChildren.add(unblockButton);

                    contactPaneChildren.add(blockBox);
                }
            });
            contactPane.setOnMouseExited(mouserEvent -> {
                contactPane.setStyle("-fx-background-color: #0A0C27;");

                int contactPaneChildrenSize = contactPaneChildren.size();
                if (contactPaneChildrenSize > 3) {
                    contactPaneChildren.remove(3, contactPaneChildrenSize);
                    for (Node e : contactPaneChildren) {
                        e.setVisible(true);
                        e.setManaged(true);
                    }
                }
            });
            contactPane.layout();
            contactPane.applyCss();

            ImageView contactImage = createImageView(images[i]);

            Label contactName = createLabel(names[i],
                    contactImage.getLayoutX() + contactImage.getFitWidth() + ICON_TEXT_MARGIN,
                    contactImage.getLayoutY(),
                    TextAlignment.CENTER,
                    FONT,
                    "-fx-font-weight: bold;");

            Label contactLogin = createLabel(logins[i],
                    contactImage.getLayoutX() + contactImage.getFitWidth() + ICON_TEXT_MARGIN,
                    contactName.getLayoutY() + contactName.getPrefHeight() + 30,
                    TextAlignment.LEFT,
                    new Font("Berlin Sans FB Demi Bold", 10.0),
                    "");

            contactPaneChildren.add(contactImage);
            contactPaneChildren.add(contactName);
            contactPaneChildren.add(contactLogin);

            contactsPaneChildren.add(contactPane);
        }
    }

    private ImageView createImageView(Image icon) {
        ImageView img = new ImageView();

        img.setFitHeight(ICON_HEIGHT);
        img.setFitWidth(ICON_WIDTH);
        img.setLayoutX(ICON_CHAT_USERS_LEFT_MARGIN);
        img.setLayoutY(20);
        img.setPickOnBounds(true);
        img.setPreserveRatio(true);
        img.setImage(icon);

        return img;
    }

    private Label createLabel(String text, double lx, double ly,
                              TextAlignment pos, Font font, String style) {
        Label label = new Label();

        label.setLayoutX(lx);
        label.setLayoutY(ly);
        label.setTextFill(Color.WHITE);
        label.setTextAlignment(pos);
        label.setFont(font);
        label.setText(text);
        label.setStyle(style);

        return label;
    }

    private void setAuthorized(boolean isAuthorized) {
        this.isAuthorized = isAuthorized;
        if (!isAuthorized) {
            authPanel.setVisible(true);
            authPanel.setManaged(true);
            chatPanel.setVisible(false);
            chatPanel.setManaged(false);
        } else {
            authPanel.setVisible(false);
            authPanel.setManaged(false);
            chatPanel.setVisible(true);
            chatPanel.setManaged(true);
        }
    }

    public boolean isAuthorized() {
        return isAuthorized;
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        mainThread.stop();
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}