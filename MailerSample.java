package examples;

import model.Mailer;

public class MailerSample {
    public static void main(String args[]) {
        Mailer.send(mailer-> mailer.from("abc@gmail.com")
                                    .to("cde@yahoo.com")
                                    .subject("Code Example")
                                    .body("...."));

    }
}
