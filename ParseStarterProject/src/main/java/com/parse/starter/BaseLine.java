package com.parse.starter;

/**
 * Created by nWoody on 7/27/2017.
 */

public class BaseLine {

    public BaseLine() {
    }

   public double  getBmiScore( double bmi) {
       if(bmi>=18.5 || bmi <=24.9){
           return .01 * bmi;
       }
       else {
           return .02 *bmi;
       }
    }
    public double getAgeScore(int age){
       if(age>=20 && age<=35) {
           return .005 * age;
       }
       else if (age < 20 || (age>40 && age<=51)){
           return .01 *  age;
       }
       else if(age>35 && age<=40){

           return .008 * age;
       }
       else if (age>51){

           return .006*age;
       }
       else{
           return -1;
       }

    }
    public double getBaseLineScore(int age,double bmi){
        return getAgeScore(age) + getBmiScore(bmi);
    }


}
