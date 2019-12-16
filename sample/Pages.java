package sample;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;

public class Pages extends Stage {
    Appointment a;
    public void mainPage(Stage primaryStage){
        a=new Appointment();
        GridPane grid=new GridPane();//Grid is the child of Stage for Alignment
        grid.setAlignment(Pos.CENTER);//Centering every component
        grid.setHgap(10);
        grid.setVgap(20);//Gap between each x and y
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label name=new Label("AcaLink");//Label
        name.setFont(Font.font("Vladimir Script",70));//Font for the label
        TextField userName=new TextField();//User name
        userName.setPromptText("User Name");
        EventHandler<MouseEvent> event=new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent mouseEvent) {
                userName.setPromptText("");
                userName.requestFocus();
            }
        };
        userName.addEventFilter(MouseEvent.MOUSE_CLICKED,event );
        PasswordField pass=new PasswordField();// Password
        pass.setPromptText("Password");
        EventHandler<MouseEvent> event1=new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent mouseEvent) {
                pass.setPromptText("");
                pass.requestFocus();
            }
        };
        pass.addEventFilter(MouseEvent.MOUSE_CLICKED,event1);
        grid.add(name,0,0);//Adding label and the text Field.
        grid.add(userName,0,2);
        grid.add(pass,0,3);

        Label error=new Label("Error 1204:User Name or Password Incorrect!");
        error.getStylesheets().add(Main.class.getResource("style1.css").toExternalForm());

        error.setFont(Font.font("Verdana",10));

        Button signup=new Button("Sign Up");
        signup.setMaxSize(200,200);
        signup.setOnAction(e->select(primaryStage));

        Button signinS=new Button("Login as Student");
        signinS.setMaxSize(200,200);
        signinS.setOnAction(e->
        {
            Student s = new Student();

            try {
                if(s.check(userName.getText(),pass.getText())) {
                    loginStudent(primaryStage);
                    a.setStudentID(userName.getText());
                }
                else
                {
                    grid.add(error,0,4);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        Button signinT=new Button("Login as Teacher");
        signinT.setMaxSize(200,200);
        signinT.setOnAction(e->
        {
            Teacher t = new Teacher();
            try {
                String user=userName.getText();
                if(t.check(user,pass.getText())){
                    System.out.println(user);
                    a.setTeacher_idEmail(user);
                    loginTeacher(primaryStage);
                }
                else
                {
                    grid.add(error,0,4);
                }
            } catch (SQLException ex) {
                System.out.println("Try again!");
            }
        });
        //grid.add(error,0,4);
        grid.add(signinT,0,5);
        grid.add(signinS,0,6);
        grid.add(signup,0,7);

        Scene LoginPage=new Scene(grid,300,450);
        EventHandler<MouseEvent> focusEvent=new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent mouseEvent)    {
                userName.setPromptText("User Name");
                pass.setPromptText("Password");
                grid.requestFocus();
            }
        };
        grid.addEventFilter(MouseEvent.MOUSE_CLICKED,focusEvent);
        LoginPage.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
        primaryStage.setScene(LoginPage);
    }

    public void select(Stage primaryStage){
        GridPane grid=new GridPane();
        grid.setVgap(30);
        Button studentb=new Button("Student");

        Button teacherb=new Button("Teacher");
        Label check=new Label();
        check.setText(" You are a");
        check.setTextFill(Color.web("white"));

        grid.setAlignment(Pos.CENTER);
        check.setFont(Font.font("Amethyst",60));
        grid.add(check,0,0);
        grid.add(studentb,0,1);
        System.out.println(Font.getFamilies());
        studentb.setOnAction(e->studentSignup(primaryStage));
        teacherb.setOnAction(e->teacherPage(primaryStage));
        grid.add(teacherb,0,2);

        studentb.setMinSize(280,100);
        teacherb.setMinSize(280,100);

        Scene selection=new Scene(grid,300,450);
        selection.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
        primaryStage.setScene(selection);

    }
    public void studentSignup(Stage primaryStage){

        GridPane grid=new GridPane();

        grid.setHgap(10);
        grid.setVgap(30);

        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(25, 25, 25, 25));

        HBox names= new HBox(10);
        VBox details=new VBox(10);
        HBox pdetails=new HBox(10);

        TextField fName=new TextField();
        fName.setPromptText("First Name");

        TextField lName=new TextField();
        lName.setPromptText("Last Name");

        names.getChildren().addAll(fName,lName);

        TextField Email=new TextField();
        Email.setPromptText("Email");

        TextField phone=new TextField();
        phone.setPromptText("Phone");

        TextField age=new TextField();
        age.setPromptText("Age");

        ComboBox gender=new ComboBox();
        gender.setPromptText("Gender");
        gender.getItems().addAll("Male","Female");

        PasswordField pass=new PasswordField();
        pass.setPromptText("Password");

        TextField grade=new TextField();
        grade.setPromptText("Grade");

        Button signupB=new Button("Sign Up");
        signupB.setMaxSize(250,200);

        signupB.setOnAction(e-> {
            Student std = new Student(fName.getText(), lName.getText(), phone.getText(), gender.getValue().toString(), age.getText(), grade.getText(), Email.getText(), pass.getText());
            mainPage(primaryStage);
            std.insertuser();
            try {
                std.insertstudent();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        pdetails.getChildren().addAll(age,gender);
        details.getChildren().addAll(Email,pass,phone,grade);

        grid.add(names,0,0);
        grid.add(pdetails,0,1);
        grid.add(details,0,2);
        grid.add(signupB,0,3);


        Scene signup=new Scene(grid,300,450);
        signup.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
        primaryStage.setScene(signup);
    }
    public void teacherPage(Stage primaryStage){

        GridPane grid=new GridPane();
        grid.setHgap(10);
        grid.setVgap(30);
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(25, 25, 25, 25));
        HBox names= new HBox(10);
        VBox details=new VBox(10);
        HBox pdetails=new HBox(10);

        TextField fName=new TextField();
        fName.setPromptText("First Name");

        TextField lName=new TextField();
        lName.setPromptText("Last Name");

        names.getChildren().addAll(fName,lName);

        TextField Email=new TextField();
        Email.setPromptText("Email");

        TextField phone=new TextField();
        phone.setPromptText("Phone");

        TextField age=new TextField();
        age.setPromptText("Age");

        ComboBox gender=new ComboBox();
        gender.setPromptText("Gender");
        gender.getItems().addAll("Male","Female");

        PasswordField pass=new PasswordField();
        pass.setPromptText("Password");

        TextField address=new TextField();
        address.setPromptText("Address");

        TextField qual=new TextField();
        qual.setPromptText("Qualification");

        TextField accountNo=new TextField();
        accountNo.setPromptText("Account Number");


        Button signUp=new Button("Sign Up");
        signUp.setMaxSize(250,200);
        signUp.setOnAction(e-> {
            Teacher tech = new Teacher(fName.getText(), lName.getText(), phone.getText(), gender.getValue().toString(), age.getText(), qual.getText(),accountNo.getText(), Email.getText(), pass.getText());
            mainPage(primaryStage);
            tech.insertuser();
            try {
                tech.insertteacher();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        pdetails.getChildren().addAll(age,gender);
        details.getChildren().addAll(Email,pass,phone,address,qual,accountNo);

        grid.add(names,0,0);
        grid.add(pdetails,0,1);
        grid.add(details,0,2);
        grid.add(signUp,0,3);

        Scene teacher=new Scene(grid,300,450);
        teacher.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
        primaryStage.setScene(teacher);
    }
    public void loginStudent(Stage primaryStage){
        GridPane grid=new GridPane();
        grid.setHgap(10);
        grid.setVgap(30);
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Button courses=new Button("View Courses");
        courses.setMaxSize(200,200);
        courses.setOnAction(e->{

            try {
                viewCourses(primaryStage);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        Button view=new Button("View Appointments");
        view.setMaxSize(200,200);
        view.setOnAction(e->{
            try {
                studentAppointment(primaryStage);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        Button wallet=new Button("Open Wallet");
        wallet.setMaxSize(200,200);
        wallet.setOnAction(e->{
            try {
                getStudentWallet(primaryStage);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        Button select=new Button("Select Course");
        select.setMaxSize(200,200);
        select.setOnAction(e->{
            try {
                selectCourses(primaryStage);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        });
        Button signout=new Button("Sign Out");
        signout.setMaxSize(200,200);
        signout.setOnAction(e->mainPage(primaryStage));

        grid.add(courses,0,1);
        grid.add(wallet,0,2);
        grid.add(view,0,3);
        grid.add(select,0,4);
        grid.add(signout,0,5);

        Scene login=new Scene(grid,300,400);
        login.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
        primaryStage.setScene(login);
    }
    public void loginTeacher(Stage primaryStage){
        GridPane grid=new GridPane();
        grid.setHgap(10);
        grid.setVgap(30);
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Button appoi=new Button("New Appointment");
        appoi.setMaxSize(300,200);
        appoi.setOnAction(e->{
            try {
                viewAppointment(primaryStage);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        });

        Button currAppoi=new Button("Appointments");
        currAppoi.setMaxSize(300,200);
        currAppoi.setOnAction(e->{
            try {
                teacherAppointments(primaryStage);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        Button currBalance=new Button("Current Balance");
        currBalance.setMaxSize(300,200);

        Button signout=new Button("Sign Out");
        signout.setMaxSize(300,200);
        signout.setOnAction(e->mainPage(primaryStage));
        grid.add(appoi,0,1);
        grid.add(currAppoi,0,2);
        grid.add(currBalance,0,3);
        grid.add(signout,0,4);

        Scene login=new Scene(grid,350,400);
        login.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
        primaryStage.setScene(login);
    }
    public void makeAppoi(Stage primaryStage){

        GridPane grid=new GridPane();
        grid.setHgap(10);
        grid.setVgap(30);
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(25, 25, 25, 25));

        HBox dateTime=new HBox(10);

        DatePicker date=new DatePicker();

        TextField time=new TextField();
        time.setPromptText("Time");

        TextField location=new TextField();
        location.setPromptText("Location");

        TextField duration=new TextField();
        duration.setPromptText("Duration");

        Button create=new Button("Create");
        create.setMaxSize(200,300);
        create.setOnAction(e->{
            a.createAppointment(date.getValue(),time.getText(),location.getText(),duration.getText());
            loginTeacher(primaryStage);
        });


        dateTime.getChildren().addAll(date,time);
        grid.add(dateTime,0,0);
        grid.add(location,0,1);
        grid.add(duration,0,2);
        grid.add(create,0,3);

        Scene appoi=new Scene(grid,250,300);
        appoi.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
        primaryStage.setScene(appoi);
    }
    public void viewCourses(Stage primaryStage) throws SQLException {
        CachedRowSet rs=a.getCourses();
        GridPane grid=new GridPane();
        grid.setHgap(0);
        grid.setVgap(0);
        grid.setAlignment(Pos.CENTER);

        int gridNo=0;
        int buttonNo=0;

        Button b[]=new Button[rs.size()*2];
        while(rs.next()) {
            HBox h = new HBox();
            System.out.println(buttonNo);
            b[buttonNo] = new Button(rs.getString("firstName") + " " + rs.getString("lastName") + ": " + rs.getString("Qualification"));
            b[buttonNo].setMinSize(400, 100);
            b[buttonNo].setMaxSize(400, 100);

            h.getChildren().addAll(b[buttonNo]);
            System.out.println("Reacher2");
            grid.add(h, 0, gridNo);
            buttonNo++;
            gridNo++;
        }
        Button back=new Button("Back");
        back.setOnAction(e->loginStudent(primaryStage));
        back.setMinSize(400,100);
        back.setLayoutX(300);
        grid.add(back,0,gridNo);
        gridNo++;
        Scene view=new Scene(grid,400,gridNo*99);
        view.getStylesheets().add(Main.class.getResource("style1.css").toExternalForm());
        primaryStage.setScene(view);
    }
    public void selectCourses(Stage primaryStage) throws SQLException {
        Teacher t=new Teacher();
        CachedRowSet rs=t.getTeachers();
        GridPane grid=new GridPane();
        grid.setHgap(0);
        grid.setVgap(0);
        grid.setAlignment(Pos.CENTER);

        int gridNo=0;
        int buttonNo=0;
        ScrollPane pane=new ScrollPane();
        Button b[]=new Button[rs.size()*2];
        while(rs.next()) {
            String firstName=rs.getString("firstName");
            String lastName=rs.getString("lastName");
            String qual=rs.getString("Qualification");
            VBox h = new VBox();
            System.out.println(buttonNo);
            b[buttonNo] = new Button(firstName + " " + lastName + ": " + qual);
            b[buttonNo].setMinSize(400, 100);
            b[buttonNo].setMaxSize(400, 100);
            b[buttonNo].setOnAction(e->{
                try {
                    a.setTeacherID(firstName,lastName);
                    a.selectCourse();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });
            h.getChildren().addAll(b[buttonNo]);
            grid.add(h, 0, gridNo);
            buttonNo++;
            gridNo++;
        }
        Button back=new Button("Back");
        back.setOnAction(e->loginStudent(primaryStage));
        back.setMinSize(400,100);
        back.setLayoutX(300);
        grid.add(back,0,gridNo);
        gridNo++;
        pane.setContent(grid);
        Scene view=new Scene(pane,410,500);
        view.getStylesheets().add(Main.class.getResource("style1.css").toExternalForm());
        primaryStage.setScene(view);
    }
    public void viewAppointment(Stage primaryStage) throws SQLException {
        CachedRowSet rs=a.viewNewAppointments();
        GridPane grid=new GridPane();
        grid.setHgap(0);
        grid.setVgap(0);
        grid.setAlignment(Pos.CENTER);

        int gridNo=0;
        int buttonNo=0;

        Button b[]=new Button[rs.size()*2];

        while(rs.next()) {
            String firstName,lastName,age,gender,student_id,teacher_id;
            firstName=rs.getString("firstName");
            lastName=rs.getString("lastName");
            age=rs.getString( "age");
            gender=rs.getString("gender");
            student_id=rs.getString("student_id");
            teacher_id=rs.getString("teacher_id");
            VBox h = new VBox();
            b[buttonNo] = new Button(firstName+" "+lastName+" "+age+" "+gender);
            b[buttonNo].setMinSize(400, 100);
            b[buttonNo].setMaxSize(400, 100);
            b[buttonNo].setOnAction(e->{
                a.setAppointment_id(student_id,teacher_id);
                makeAppoi(primaryStage);
            });
            h.getChildren().addAll(b[buttonNo]);
            grid.add(h, 0, gridNo);
            buttonNo++;
            gridNo++;
        }
        Button back=new Button("Back");
        back.setOnAction(e->loginTeacher(primaryStage));
        back.setMinSize(400,100);
        grid.add(back,0,gridNo);
        gridNo++;
        Scene view=new Scene(grid,400,gridNo*99);
        view.getStylesheets().add(Main.class.getResource("style1.css").toExternalForm());
        primaryStage.setScene(view);
    }
    public void teacherAppointments(Stage primaryStage) throws SQLException {
        CachedRowSet rs=a.getAppointments();
        GridPane grid=new GridPane();
        grid.setHgap(0);
        grid.setVgap(0);
        grid.setAlignment(Pos.CENTER);

        int gridNo=0;
        int buttonNo=0;

        ScrollPane pane=new ScrollPane();
        Button b[]=new Button[rs.size()*2];

        while(rs.next()){
        String firstName=rs.getString("firstName");
        String lastName=rs.getString("lastName");
        String date=rs.getString("dateAppointment");
        String time=rs.getString("time");
        String location=rs.getString("location");
        String duration=rs.getString("duration");
        VBox h = new VBox();
        System.out.println(buttonNo);
        b[buttonNo] = new Button(firstName + " " + lastName +" "+ date+ " "+time+" "+location+" "+duration);
        b[buttonNo].setMinSize(400, 100);
        b[buttonNo].setMaxSize(400, 100);

        h.getChildren().addAll(b[buttonNo]);
        grid.add(h, 0, gridNo);
        buttonNo++;
        gridNo++;
    }
    Button back=new Button("Back");
        back.setOnAction(e->loginTeacher(primaryStage));
        back.setMinSize(400,100);
        back.setLayoutX(300);
        grid.add(back,0,gridNo);
    gridNo++;
        pane.setContent(grid);
    Scene view=new Scene(pane,410,500);
        view.getStylesheets().add(Main.class.getResource("style1.css").toExternalForm());
        primaryStage.setScene(view);
    }
    public void studentAppointment(Stage primaryStage) throws SQLException {
        CachedRowSet rs=a.getSAppointment();
        GridPane grid=new GridPane();
        grid.setHgap(0);
        grid.setVgap(0);
        grid.setAlignment(Pos.CENTER);

        int gridNo=0;
        int buttonNo=0;

        ScrollPane pane=new ScrollPane();
        Button b[]=new Button[rs.size()*2];

        while(rs.next()){
            String firstName=rs.getString("firstName");
            String lastName=rs.getString("lastName");
            String date=rs.getString("dateAppointment");
            String time=rs.getString("time");
            String location=rs.getString("location");
            String duration=rs.getString("duration");
            VBox h = new VBox();
            System.out.println(buttonNo);
            b[buttonNo] = new Button(firstName + " " + lastName +" "+ date+ " "+time+" "+location+" "+duration);
            b[buttonNo].setMinSize(400, 100);
            b[buttonNo].setMaxSize(400, 100);

            h.getChildren().addAll(b[buttonNo]);
            grid.add(h, 0, gridNo);
            buttonNo++;
            gridNo++;
        }
        Button back=new Button("Back");
        back.setOnAction(e->loginStudent(primaryStage));
        back.setMinSize(400,100);
        back.setLayoutX(300);
        grid.add(back,0,gridNo);
        gridNo++;
        pane.setContent(grid);
        Scene view=new Scene(pane,410,500);
        view.getStylesheets().add(Main.class.getResource("style1.css").toExternalForm());
        primaryStage.setScene(view);
    }
    public void getStudentWallet(Stage primaryStage) throws SQLException {
        CachedRowSet rs=a.getStudentWallet();
        GridPane grid=new GridPane();
        grid.setHgap(0);
        grid.setVgap(0);
        grid.setAlignment(Pos.CENTER);

        int gridNo=0;
        int buttonNo=0;


        Button b[]=new Button[rs.size()*2];

        while(rs.next()){
            String wallet=rs.getString("wallet");
            VBox h = new VBox();
            System.out.println(buttonNo);
            b[buttonNo] = new Button("Your current balance is: "+wallet+"$");
            b[buttonNo].setMinSize(400, 100);
            b[buttonNo].setMaxSize(400, 100);

            h.getChildren().addAll(b[buttonNo]);
            grid.add(h, 0, gridNo);
            buttonNo++;
            gridNo++;
        }
        Button back=new Button("Back");
        back.setOnAction(e->loginStudent(primaryStage));
        back.setMinSize(400,100);
        back.setLayoutX(300);
        grid.add(back,0,gridNo);
        gridNo++;

        Scene view=new Scene(grid,410,gridNo*100);
        view.getStylesheets().add(Main.class.getResource("style1.css").toExternalForm());
        primaryStage.setScene(view);
    }
}
