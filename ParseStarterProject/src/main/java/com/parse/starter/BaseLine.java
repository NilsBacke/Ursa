package com.parse.starter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by nWoody on 7/27/2017.
 */

public class BaseLine {
  public String TAG = BaseLine.class.getSimpleName();

    public BaseLine() {
    }

   public double  getBmiScore( double bmi) {
       if(bmi>=18.5 && bmi <=24.9){
           String firstLog=".01 log";
           Log.d("BMIONE",firstLog);
           return .01 * bmi;

       }
       else {
           String logBmi= ".02 log ";
            String lognum=String.valueOf(.02*bmi);
           String bmiString=String.valueOf(bmi);
           Log.d("BMITWO",bmiString);
           Log.d("BMI",lognum);
           return .02 *bmi;
       }
    }
    public double getAgeScore(double  age){
        //peak physical age
       if(age>=20 && age<=35) {
           Log.d("TWENTY TO THIRTYFIVE","AGE TWENTY TO THIRTYFIVE");
           return .005 * age;
       }

       else if (age < 20 || (age>40 && age<=51)){
           Log.d("LESS THAN 20 OR OTHER","LESS THAN 20");
           return .01 *  age;
       }
       else if(age>35 && age<=40){
           Log.d("GREATER THAN 35","Greater than 35");
           return .02 * age;
       }
       else if (age>51){
           Log.d("Greater than 51","Greater than 51");
           return .005*age;
       }
       else{
           return -1;
       }

    }
    public double getBaseLineScore(double age,double bmi){
        return (getAgeScore(age) + getBmiScore(bmi));
    }


}
