class Snippet { 
public void sendSMS(){

    for(int t = 1; t == 5; t = t+1) {
        pp();   
    }
}

public void pp() {


    SmsManager smsManager = SmsManager.getDefault();

     phoneNo = "01234567890";
     message ="Sample msg from app";
     Toast.makeText(getApplicationContext(), "testing done", Toast.LENGTH_LONG).show();

     smsManager.sendTextMessage(phoneNo, null, message, null, null);
     Toast.makeText(getApplicationContext(), "dummy Message dumped!", 
                    Toast.LENGTH_LONG).show();


}

}