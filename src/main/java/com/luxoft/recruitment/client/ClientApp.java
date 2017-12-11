package com.luxoft.recruitment.client;

import com.luxoft.recruitment.cstr.Context;
import com.luxoft.recruitment.cstr.SuperApiController;
import com.luxoft.recruitment.cstr.http.HttpStatus;
import com.luxoft.recruitment.cstr.http.Request;
import com.luxoft.recruitment.cstr.http.Response;

import java.util.Scanner;
import java.util.regex.Pattern;

public class ClientApp {

    private static SuperApiController controller = Context.getInstance().getSuperApiController();

    private static final Pattern PATTERN = Pattern.compile("^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");

    public static boolean validate(final String ip) {
        return PATTERN.matcher(ip).matches();
    }

    public static void main(String[] args){

        Scanner input = new Scanner(System.in);

        while(true){
            String command = input.nextLine();

            args = command.split(" ");
            if("get".equalsIgnoreCase(args[0]) && "-ip".equalsIgnoreCase(args[1]) && validate(args[2])){
                Request request = new Request(args[2]);
                Response response = controller.healthCheck(request);
                System.out.println(HttpStatus.OK.equals(response.getCode()) ? "Success!" : "Access denied!");
            }
            else {
                System.out.println("Bad Command Request.");
            }
        }

    }
}
