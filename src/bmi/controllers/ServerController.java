package bmi.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerController {

    @FXML Label connectLabel, weightLabel, heightLabel;
    @FXML Button start, connect;

    public void connectBth(ActionEvent actionEvent) throws  IOException {
        try{
            ServerSocket ss = new ServerSocket(5161);


            Socket s = ss.accept();

            DataOutputStream sent_client =new DataOutputStream(s.getOutputStream());
            sent_client.writeUTF("Connect server");
            sent_client.flush();

            DataInputStream recieve1 = new DataInputStream(s.getInputStream());
            String  str1=(String)recieve1.readUTF();
            connectLabel.setText(str1);
            DataInputStream recieve2 = new DataInputStream(s.getInputStream());
            String  str2=(String)recieve2.readUTF();
            weightLabel.setText(str2);
            DataInputStream recieve3 = new DataInputStream(s.getInputStream());
            String  str3=(String)recieve3.readUTF();
            heightLabel.setText(str3);

            float w = Float.parseFloat(str2);
            float h = Float.parseFloat(str3);

            float bmi = w/(h*h);

            System.out.println(bmi);

            if ( bmi <18.5 ){

                DataOutputStream sent_clientUn =new DataOutputStream(s.getOutputStream());
                sent_clientUn.writeUTF("Underweight");
                sent_clientUn.flush();
                String sum = String.valueOf(bmi);
                DataOutputStream sum_clientUn =new DataOutputStream(s.getOutputStream());
                sum_clientUn.writeUTF(sum);
                sum_clientUn.flush();
                System.out.println("Underweight");

            }
            if ( bmi >= 18.5 && bmi < 25){
                DataOutputStream sent_clientNor =new DataOutputStream(s.getOutputStream());
                sent_clientNor.writeUTF("Normal weight");
                sent_clientNor.flush();
                String sum = String.valueOf(bmi);
                DataOutputStream sum_clientNor =new DataOutputStream(s.getOutputStream());
                sum_clientNor.writeUTF(sum);
                sum_clientNor.flush();
                System.out.println("Normal weight");
            }
            if ( bmi >= 25 && bmi < 30){
                DataOutputStream sent_clientOver =new DataOutputStream(s.getOutputStream());
                sent_clientOver.writeUTF("Overweight");
                sent_clientOver.flush();
                String sum = String.valueOf(bmi);
                DataOutputStream sum_clientOver =new DataOutputStream(s.getOutputStream());
                sum_clientOver.writeUTF(sum);
                sum_clientOver.flush();
                System.out.println("Overweight");
            }
            if ( bmi >= 30) {
                DataOutputStream sent_clientObe =new DataOutputStream(s.getOutputStream());
                sent_clientObe.writeUTF("Obesity");
                sent_clientObe.flush();
                String sum = String.valueOf(bmi);
                DataOutputStream sum_clientObe =new DataOutputStream(s.getOutputStream());
                sum_clientObe.writeUTF(sum);
                sum_clientObe.flush();
                System.out.println("Obesity");

            }
            ss.close();
    }catch(Exception e){System.out.println(e);}

    }


}
